package com.example.demo.E2E;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Test;
import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Caso2 {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30)); 
        driver.manage().window().maximize();
    }

    @AfterAll
    static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Order(1)
    @DisplayName("Login y creación de tratamiento usando formulario único")
    void crearTratamientoVeterinario() {
        driver.get("http://localhost:4200/login");

        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_cliente")));
        email.clear();
        email.sendKeys("veterinario@gmail.com");

        WebElement password = driver.findElement(By.id("password_cliente"));
        password.clear();
        password.sendKeys("1234");

        driver.findElement(By.cssSelector("button.btn-login")).click();

        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return !!window.localStorage.getItem('token');")
                .equals(true)
        );

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("/veterinario"),
                ExpectedConditions.urlContains("/cliente"),
                ExpectedConditions.urlContains("/admin")
        ));

        
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchMascota")));
        searchBox.sendKeys("Fido");
        driver.findElement(By.id("btnBuscar")).click();

        WebElement linkMascota = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.detalleMascota")));
        linkMascota.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnNuevoTratamiento"))).click();

        WebElement selectMed = wait.until(ExpectedConditions.elementToBeClickable(By.id("medicamentoSelect")));
        selectMed.click();
        selectMed.findElement(By.cssSelector("option[value='1']")).click();

        driver.findElement(By.id("btnGuardarTratamiento")).click();

        WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-success")));
        Assertions.assertTrue(success.getText().contains("Tratamiento guardado"));
    }

    @Test
    @Order(2)
    @DisplayName("Login de administrador y verificación de medicamento usando navegación interna")
    void verificarDatosAdministrador() {
        driver.get("http://localhost:4200/login");

        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_cliente")));
        email.clear();
        email.sendKeys("admin@example.com");

        WebElement password = driver.findElement(By.id("password_cliente"));
        password.clear();
        password.sendKeys("1234");

        driver.findElement(By.cssSelector("button.btn-login")).click();

        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return !!window.localStorage.getItem('token');")
                .equals(true)
        );

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("/admin"),
                ExpectedConditions.urlContains("/veterinario"),
                ExpectedConditions.urlContains("/cliente")
        ));

        WebElement menuMedicamentos = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnMedicamentos")));
        menuMedicamentos.click();

        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchMedicamento")));
        search.clear();
        search.sendKeys("Amoxicilina");

        WebElement btnBuscarMed = driver.findElement(By.id("btnBuscarMed"));
        btnBuscarMed.click();


        WebElement unidades = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td.unidades")));
        WebElement vendidas = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td.vendidas")));

        int unidadesRestantes = Integer.parseInt(unidades.getText());
        int unidadesVendidas = Integer.parseInt(vendidas.getText());

        Assertions.assertTrue(unidadesRestantes >= 0);
        Assertions.assertTrue(unidadesVendidas > 0);
    }
}
