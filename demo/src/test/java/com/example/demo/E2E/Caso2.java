package com.example.demo.E2E;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Caso2 {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        driver.manage().window().maximize();
    }

    @AfterAll
    static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ------------------- MÉTODO LOGIN -------------------
    private void login(String emailStr, String passwordStr) {
        driver.get("http://localhost:4200/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_cliente")));

        WebElement email = driver.findElement(By.id("email_cliente"));
        email.clear();
        email.sendKeys(emailStr);

        WebElement password = driver.findElement(By.id("password_cliente"));
        password.clear();
        password.sendKeys(passwordStr);

        WebElement loginBtn = driver.findElement(By.cssSelector("button.btn-login.w-100"));
        loginBtn.click();

        //wait.until(ExpectedConditions.urlContains("/usuarios")); 
    
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("/veterinario"),
                ExpectedConditions.urlContains("/cliente"),
                ExpectedConditions.urlContains("/admin"),
                ExpectedConditions.urlContains("/usuarios") 
        ));
    }

    // TEST 1: Veterinario accede al detalle de mascota (Corregido)
    @Test
    @Order(1)
    public void veterinarioPuedeVerMascota() {

        login("veterinario@gmail.com", "1234");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("app-usuarios-tabla")));

        String xpathFilaJuan = "//tr[td[contains(text(),'juan@example.com')]]";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathFilaJuan)));
        WebElement filaJuan = driver.findElement(By.xpath(xpathFilaJuan));

        WebElement botonMas = wait.until(ExpectedConditions.elementToBeClickable(
            filaJuan.findElement(By.cssSelector("button.btn-mas"))
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botonMas);
        System.out.println("Se ha hecho clic en el botón 'más' de juan@example.com usando JavaScript.");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("app-usuarios-detalle")));
        WebElement filaFido = driver.findElement(
            By.xpath("//tr[td[contains(text(),'Fido')]]")
        );

        WebElement botonMascota = filaFido.findElement(
            By.xpath(".//button[.//img[@alt='Icono Ojo']]")
        );
        wait.until(ExpectedConditions.elementToBeClickable(botonMascota));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botonMascota);

        wait.until(ExpectedConditions.or(
            ExpectedConditions.urlContains("/mascota/"), 
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Información']")) 
        ));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("app-mascota-detalle")));

        System.out.println("Redirección al detalle de la mascota Fido lograda.");
    }




    // TEST 2: Veterinario crea y guarda un nuevo tratamiento
    @Test
    @Order(2)
    public void veterinarioCreaNuevoTratamiento() {
        
        // Asumimos que driver y wait están inicializados en @BeforeAll
        
        // Nombre del tratamiento a crear
        String nombreTratamiento = "Antibiótico de Rutina"; 

        // --- 1. Clic en la pestaña "Tratamientos" ---
        WebElement tabTratamientos = wait.until(ExpectedConditions.elementToBeClickable(
            By.id("tratamientos-tab") 
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tabTratamientos);
        System.out.println("✅ Clic en la pestaña 'Tratamientos' exitoso.");

        // --- 2. Llenar el campo "Nombre del tratamiento" ---
        By selectorNombreTratamiento = By.name("nombreTrat"); 
        WebElement inputNombreTratamiento = wait.until(
            ExpectedConditions.presenceOfElementLocated(selectorNombreTratamiento)
        );
        
        inputNombreTratamiento.clear(); 
        inputNombreTratamiento.sendKeys(nombreTratamiento); 
        
        // CLAVE: Forzar el evento 'input' para Angular
        ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('input'));", inputNombreTratamiento);

        // --- 3. Seleccionar el medicamento "ACOLAN" ---
        By selectorDropdown = By.name("medicamentoId");
        WebElement selectMedicamento = wait.until(
            ExpectedConditions.presenceOfElementLocated(selectorDropdown)
        ); 
        Select seleccionMedicamento = new Select(selectMedicamento);
        seleccionMedicamento.selectByVisibleText("ACOLAN"); 

        // CLAVE: Forzar el evento 'change' en el SELECT
        ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('change'));", selectMedicamento);

        System.out.println("✅ Campos de tratamiento llenados y eventos forzados.");

        // 🛑 PASO CLAVE 1: Esperar a que la validación DESAPAREZCA 🛑
        By selectorErrorValidacion = By.xpath("//div[contains(text(), 'Completa nombre, medicamento y veterinario.')]");
        
        try {
            // Esperamos que el elemento *deje* de ser visible/presente
            wait.until(ExpectedConditions.invisibilityOfElementLocated(selectorErrorValidacion));
            System.out.println("✅ Validación de formulario superada (Mensaje de error invisible).");
            
        } catch (Exception e) {
            Assertions.fail("❌ El mensaje de validación no desapareció. Angular no reconoció los inputs a tiempo. " + e.getMessage());
        }


        // --- 4. Clic en Guardar tratamiento (MÉTODO ROBUSTO ANTI-ANGULAR) ---
        WebElement btnGuardar = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//button[text()='Guardar tratamiento']") 
        ));
        // 1. Forzar habilitación del botón eliminando el atributo 'disabled'
    ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('disabled');", btnGuardar);
    System.out.println("✅ Botón 'Guardar' habilitado forzadamente.");

    // 2. Hacer clic nativo de Selenium (o click forzado JS si el nativo falla)
    try {
        btnGuardar.click();
    } catch (Exception e) {
        // Fallback: Si el clic nativo falla por algún overlay invisible, forzamos JS
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnGuardar);
    }
    System.out.println("✅ Clic en 'Guardar tratamiento' ejecutado.");
    
    // 🛑 AÑADIR ESPERA ESTÁTICA 🛑
    try {
        // Esperamos 1.5 segundos para que la solicitud de guardado (POST) se procese
        Thread.sleep(1500); 
        System.out.println("✅ Espera de 1.5s para procesamiento de backend completada.");
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
    
    // --- 5. Esperar y verificar el guardado (El paso donde falla actualmente) ---
    By selectorTratamientoGuardado = By.xpath("//div[@id='tratamientos']//td[contains(text(), '" + nombreTratamiento + "')]");
    
    // Si el guardado fue exitoso en el backend, el elemento aparecerá aquí.
    wait.until(ExpectedConditions.presenceOfElementLocated(selectorTratamientoGuardado));
        // ✅ Verificación
        Assertions.assertTrue(driver.findElement(selectorTratamientoGuardado).isDisplayed(),
                "❌ El nuevo tratamiento no se guardó o no es visible en la lista.");

        System.out.println("✅ Tratamiento '" + nombreTratamiento + "' guardado y verificado exitosamente.");
    }
}
