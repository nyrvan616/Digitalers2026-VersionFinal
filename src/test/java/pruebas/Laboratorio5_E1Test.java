package pruebas;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import paginas.PaginaInicio;
import paginas.PaginaLogin;
import utilidades.DatosExcel;


public class Laboratorio5_E1Test {
    WebDriver driver;

    @BeforeMethod
    public void abrirNavegador() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @DataProvider(name = "datos login invalido")
    public Object[][] datosLoginInvalidos() {
        return new Object[][] {
            {"alumno.uno@example.com", "ClaveIncorrecta123!"},
            {"alumno.dos@example.com", "OtraClaveIncorrecta456!"}
        };
    }
    
    @DataProvider(name = "datos login invalido desde planilla")
    public Object[][] datosLoginInvalidosDesdePlanilla() throws IOException {
        String ruta = "src/test/resources/datos-login.xlsx";
        String hoja = "Login";
        return DatosExcel.leerExcel(ruta, hoja);
    }

    @Test(dataProvider = "datos login invalido")
    public void loginConContrasenaIncorrecta(String email, String password) {
        PaginaInicio inicio = new PaginaInicio(driver);
        inicio.irAPaginaInicio();
        inicio.irALogin();

        PaginaLogin login = new PaginaLogin(driver);
        login.iniciarSesion(email, password);

        Assert.assertEquals(login.obtenerErrorLogin(), "Your email or password is incorrect!");
    }
    
    @Test(dataProvider = "datos login invalido desde planilla")
    public void loginConContrasenaIncorrectaDatosPlanilla(String email, String password) {
        PaginaInicio inicio = new PaginaInicio(driver);
        inicio.irAPaginaInicio();
        inicio.irALogin();

        PaginaLogin login = new PaginaLogin(driver);
        login.iniciarSesion(email, password);

        Assert.assertEquals(login.obtenerErrorLogin(), "Your email or password is incorrect!");
    }

    @AfterMethod(alwaysRun = true)
    public void cerrarNavegador() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}