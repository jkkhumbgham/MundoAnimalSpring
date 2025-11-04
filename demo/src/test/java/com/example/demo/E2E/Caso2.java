package com.example.demo.E2E;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Caso2 {
    private static final String BASE_URL = "http://localhost:4200";
    private static final String ADMIN_EMAIL = "admin@example.com";
    private static final String ADMIN_PASS = "1234";
    private static final String VET_EMAIL = "veterinario@gmail.com";
    private static final String VET_PASS = "1234";
    private static final String MEDICAMENTO_ID = "1";
    
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static MedicamentoStats statsAntes;

    record MedicamentoStats(int unidades, int vendidas, double ganancias) {}

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        // Para CI: descomentar la siguiente línea
        // options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @AfterAll
    static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void login(String email, String password) {
        driver.get(BASE_URL + "/login");
        
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_cliente")));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = driver.findElement(By.id("password_cliente"));
        passwordField.clear();
        passwordField.sendKeys(password);

        driver.findElement(By.cssSelector("button.btn-login")).click();

        // Esperar token y redirección
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return !!window.localStorage.getItem('token');")
                .equals(true));

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("/admin"),
                ExpectedConditions.urlContains("/veterinario"),
                ExpectedConditions.urlContains("/cliente")
        ));
    }

    private void buscarMascotaYSeleccionar(String nombre) {
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchMascota")));
        searchBox.clear();
        searchBox.sendKeys(nombre);
        
        driver.findElement(By.id("btnBuscar")).click();

        WebElement linkMascota = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.detalleMascota")));
        linkMascota.click();
    }

    private void crearTratamientoParaMascota(String optionValue) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnNuevoTratamiento"))).click();

        WebElement selectMed = wait.until(ExpectedConditions.elementToBeClickable(By.id("medicamentoSelect")));
        selectMed.click();
        selectMed.findElement(By.cssSelector("option[value='" + optionValue + "']")).click();

        driver.findElement(By.id("btnGuardarTratamiento")).click();

        WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-success")));
        Assertions.assertTrue(success.getText().contains("guardad"), "El tratamiento debería guardarse correctamente");
    }

    private MedicamentoStats leerStatsDeAdmin(String textoBusqueda) {
        // Navegar a medicamentos si no estamos ahí
        WebElement menuMedicamentos = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnMedicamentos")));
        menuMedicamentos.click();

        // Buscar medicamento
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchMedicamento")));
        search.clear();
        search.sendKeys(textoBusqueda);

        WebElement btnBuscarMed = driver.findElement(By.id("btnBuscarMed"));
        btnBuscarMed.click();

        // Leer métricas
        WebElement unidades = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td.unidades")));
        WebElement vendidas = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td.vendidas")));
        
        double ganancias = 0.0;
        try {
            WebElement precio = driver.findElement(By.cssSelector("td.precioVenta"));
            if (precio != null && !precio.getText().isEmpty()) {
                ganancias = parseMoneda(precio.getText()) * Integer.parseInt(vendidas.getText());
            }
        } catch (NoSuchElementException e) {
            // TODO: Agregar data-testid="precio-venta" en la columna de precio en la tabla de medicamentos
            System.out.println("Columna de precio no encontrada. Ganancias no calculadas.");
        }

        return new MedicamentoStats(
            Integer.parseInt(unidades.getText()),
            Integer.parseInt(vendidas.getText()),
            ganancias
        );
    }

    private double parseMoneda(String valor) {
        // Eliminar símbolos de moneda y separadores de miles, reemplazar coma decimal por punto
        return Double.parseDouble(
            valor.replaceAll("[^0-9,.]", "")
                 .replace(".", "")
                 .replace(",", ".")
        );
    }

    @Test
    @Order(1)
    @DisplayName("Baseline admin: verificar métricas antes del suministro")
    void verificarMetricasIniciales() {
        login(ADMIN_EMAIL, ADMIN_PASS);
        statsAntes = leerStatsDeAdmin("Amoxicilina");
        
        Assertions.assertTrue(statsAntes.unidades() >= 0, 
            "Debe haber un número no negativo de unidades disponibles");
    }

    @Test
    @Order(2)
    @DisplayName("Veterinario: suministrar tratamiento a mascota")
    void crearTratamientoVeterinario() {
        login(VET_EMAIL, VET_PASS);
        buscarMascotaYSeleccionar("Fido");
        crearTratamientoParaMascota(MEDICAMENTO_ID);
    }

    @Test
    @Order(3)
    @DisplayName("Admin: verificar métricas después del suministro")
    void verificarMetricasDespues() {
        login(ADMIN_EMAIL, ADMIN_PASS);
        MedicamentoStats statsDespues = leerStatsDeAdmin("Amoxicilina");

        Assertions.assertEquals(statsAntes.vendidas() + 1, statsDespues.vendidas(),
            "Las unidades vendidas deberían incrementar en 1");
        
        Assertions.assertEquals(statsAntes.unidades() - 1, statsDespues.unidades(),
            "Las unidades disponibles deberían decrementar en 1");

        if (statsAntes.ganancias() > 0 && statsDespues.ganancias() > 0) {
            Assertions.assertTrue(statsDespues.ganancias() > statsAntes.ganancias(),
                "Las ganancias totales deberían aumentar después de la venta");
        }
    }
}
