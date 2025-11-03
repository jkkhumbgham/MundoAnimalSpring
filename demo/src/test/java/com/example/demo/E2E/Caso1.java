package com.example.demo.E2E;

import java.time.Duration;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import io.github.bonigarcia.wdm.WebDriverManager;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class Caso1 {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void init() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        //options.addArguments("--headless");

        this.driver = new ChromeDriver(options);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @Test
    public void UserRegisterAndLogin_Success() {
        driver.get("http://localhost:4200/login");

        String errorXpath = "//html//body//app-root//app-login//div//div[2]//div//div[2]";
        String emailXpath ="//*[@id=\\\"email_cliente\\\"]";
        String passwordXpath = "//*[@id=\"password_cliente\"]";
        String logingXpath = "//*[@id=\"pills-tabContent\"]//div//form//button";
        String newUserXpath ="//html//body//app-root//app-usuarios-tabla//main//div//div//div//a";
        String nameUserXpath = "//*[@id=\"nombre\"]";
        String telefonoUserXpath="//*[@id=\"telefono\"]";
        String correoUserXpath="//*[@id=\"email\"]";
        String contrasenaUserXpath="//*[@id=\"contrasena\"]";
        String fotoUserXpath="//*[@id=\"foto\"]";
        String registerXpath="//html//body//app-root//app-usuarios-formulario//main//div//div//form//button";
        String errorContrasenaXpath = "//html//body//app-root//app-usuarios-formulario//main//div//div//form//div[4]//div//small";
        String newMascotaXpath ="//html//body//app-root//app-usuarios-detalle//main//div//div//div[2]//button";
        String mascotaNameXpath ="//*[@id=\"nombre\"]";
        String mascotaEspecieXpath = "//*[@id=\"especie\"]";
        String mascotaEstadoXpath = "//*[@id=\"estado\"]";
        String mascotaRazaXpath = "//*[@id=\"raza\"]";
        String mascotaPesoXpath = "//*[@id=\"peso\"]";
        String mascotaSexoXpath = "//*[@id=\"sexo\"]";
        String mascotaFechanacimientoXpath = "//*[@id=\"fecha\"]";
        String mascotaMicrchipXpath = "//*[@id=\"chip\"]";
        String vacunaBtnXpath="//html//body//app-root//app-mascota-formulario//main//div//div//form//div[5]//div//button";
        String alergiasBtnXpath = "//html//body/app-root//app-mascota-formulario//main//div//div//form//div[6]//div//button";
        String vacunasXpath="//html//body//app-root//app-mascota-formulario//main//div//div//form//div[5]//div//div//input";
        String alergiasXpath = "//html//body//app-root//app-mascota-formulario//main//div//div//form//div[6]//div//div//input";
        String mascotaUltimaVisitaXpath = "//*[@id=\"ultima\"]";
        String mascotaFotoXpath = "//*[@id=\"foto\"]";
        String mascotaObservacionesXpath = "//*[@id=\"observaciones\"]";
        String newMascotaRegisterXpath = "//html//body//app-root//app-mascota-formulario//main//div//div//form//button";
        String logOutXpath="//*[@id=\"navbarNav\"]//button";
        String nombreMascotaXpath = "//html//body//app-root//app-usuarios-detalle//main//div//div//table//tbody//tr[1]//td[2]";
        String errorContrasenaEsperado = "La contraseña es obligatoria.";
        String errorEsperado = "Correo o contraseña incorrectos.";
        
        WebElement nombreMascotaVerificar = driver.findElement(By.xpath(nombreMascotaXpath));
        WebElement logOut = driver.findElement(By.xpath(logOutXpath));
        WebElement registerMascota = driver.findElement(By.xpath(newMascotaRegisterXpath));
        WebElement mascotaUltimaVisita = driver.findElement(By.xpath(mascotaUltimaVisitaXpath));
        WebElement mascotaFoto = driver.findElement(By.xpath(mascotaFotoXpath));
        WebElement mascotaObservaciones = driver.findElement(By.xpath(mascotaObservacionesXpath));
        WebElement mascotaAlergia= driver.findElement(By.xpath(alergiasXpath));
        WebElement mascotaVacuna= driver.findElement(By.xpath(vacunasXpath));
        WebElement btnAlergias = driver.findElement(By.xpath(alergiasBtnXpath));
        WebElement btnVacuna = driver.findElement(By.xpath(vacunaBtnXpath));
        WebElement mascotaMicrochip = driver.findElement(By.xpath(mascotaMicrchipXpath));
        WebElement mascotaFechanacimiento = driver.findElement(By.xpath(mascotaFechanacimientoXpath));
        WebElement mascotaSexo = driver.findElement(By.xpath(mascotaSexoXpath));
        WebElement mascotaRaza = driver.findElement(By.xpath(mascotaRazaXpath));
        WebElement mascotaPeso = driver.findElement(By.xpath(mascotaPesoXpath));
        WebElement mascotaEstado = driver.findElement(By.xpath(mascotaEstadoXpath));
        WebElement mascotaEspecie = driver.findElement(By.xpath(mascotaEspecieXpath));
        WebElement mascotaName = driver.findElement(By.xpath(mascotaNameXpath));
        WebElement error = driver.findElement(By.xpath(errorXpath));
        WebElement email = driver.findElement(By.xpath(emailXpath));
        WebElement password = driver.findElement(By.xpath(passwordXpath));
        WebElement login = driver.findElement(By.xpath(logingXpath));
        WebElement btnUser = driver.findElement(By.xpath(newUserXpath));
        WebElement name = driver.findElement(By.xpath(nameUserXpath));
        WebElement telefono = driver.findElement(By.xpath(telefonoUserXpath));
        WebElement correo = driver.findElement(By.xpath(correoUserXpath));
        WebElement contrasena = driver.findElement(By.xpath(contrasenaUserXpath));
        WebElement foto = driver.findElement(By.xpath(fotoUserXpath));
        WebElement register = driver.findElement(By.xpath(registerXpath));
        WebElement errorContrasena = driver.findElement(By.xpath(errorContrasenaXpath));
        List<WebElement> botones = driver.findElements(By.className("btn-mas"));
        WebElement newMascota = driver.findElement(By.xpath(newMascotaXpath));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(emailXpath)));
        email.sendKeys("veterinariopf@gmail.com");
        password.sendKeys("1234");
        login.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(errorXpath)));
        Assertions.assertThat(error.getText()).equals(errorEsperado);
        email.sendKeys("veterinario@gmail.com");
        password.sendKeys("1234");
        login.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(newUserXpath)));
        btnUser.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(nameUserXpath)));
        name.sendKeys("Juan Perez");
        telefono.sendKeys("3001234567");
        correo.sendKeys("clienteprueba@gmail.com");
        foto.sendKeys("img.jpg");
        register.click();
        Assertions.assertThat(errorContrasena.getText()).equals(errorContrasenaEsperado);
        contrasena.sendKeys("1234");
        register.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("btn-mas")));
        botones.get(51).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(newMascotaXpath)));
        newMascota.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mascotaNameXpath)));
        mascotaName.sendKeys("Mascota 1");
        mascotaEspecie.sendKeys("Perro");
        mascotaRaza.sendKeys("Labrador");
        mascotaSexo.sendKeys("Macho");
        mascotaPeso.sendKeys("0,5");
        mascotaEstado.sendKeys("Saludable");
        mascotaFechanacimiento.sendKeys("2022-01-01");
        mascotaMicrochip.sendKeys("12349292");
        btnVacuna.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(vacunasXpath)));
        mascotaVacuna.sendKeys("Rabia");
        btnAlergias.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(alergiasXpath)));
        mascotaAlergia.sendKeys("Penicilina");
        mascotaUltimaVisita.sendKeys("2022-01-01");
        mascotaFoto.sendKeys("img.jpg");
        mascotaObservaciones.sendKeys("Observaciones");
        registerMascota.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(logOutXpath)));
        logOut.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(emailXpath)));
        email.sendKeys("clienteprueba@gmail.com");
        password.sendKeys("1234");
        register.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(nombreMascotaXpath)));
        Assertions.assertThat(nombreMascotaVerificar.getText()).equals("Mascota 1");
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
