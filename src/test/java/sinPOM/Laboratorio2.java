package sinPOM;
import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Laboratorio2 {
	
	@Test
	void lab2_E1_localizadores() {
		WebDriver driver = new ChromeDriver();
		
		//Espera implicita: es una espera general en todas las búsquedas de elementos.
		//Espera hasta 10 segundos a que los elementos buscados aparezcan en pantalla.
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //Para este proyecto no será usada.
		
		try {
			driver.get("https://automationexercise.com/login");
			driver.manage().window().maximize();
			
			WebElement enlaceProductos = driver.findElement(By.partialLinkText("Products"));
			WebElement enlaceRegistro = driver.findElement(By.linkText("Signup / Login"));
			WebElement nombre = driver.findElement(By.name("name"));
			WebElement bloqueRegistro = driver.findElement(By.className("signup-form"));
			
			//Mala practica: es una mala práctica seleccionar selectores donde se concatene una susecion de elementos heredados.
			//Al menor cacmbio de front, esto se romperá (habrá que actualziarlo).
			//WebElement botonRegistro = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/button"));
			
			WebElement botonRegistro = driver.findElement(By.xpath("//button[@data-qa=\"signup-button\"]"));
			
			//WebElement email = driver.findElement(By.cssSelector("[data-qa='signup-email']"));
            WebDriverWait waitCorto = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebDriverWait waitMedio = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebDriverWait waitLargo = new WebDriverWait(driver, Duration.ofSeconds(60));
            
            
            WebElement email = waitLargo.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-qa='signup-email']")));
			
			email.sendKeys("example@email.com");
			
			String textoProducto = enlaceProductos.getText();
			String textoEnlaceRegistro = enlaceRegistro.getText();
			
			System.out.println(textoProducto);
			System.out.println(textoEnlaceRegistro);
			System.out.println("Se muestra campo nombre: " + nombre.isDisplayed());
			System.out.println("Se muestra formulario registro: " + bloqueRegistro.isDisplayed());
			System.out.println("Se muestra boton registro: " + botonRegistro.isDisplayed());
			System.out.println("Se muestra campo email: " + email.isDisplayed());
		}
		finally {
			driver.quit();
		}
	}
	
	@Disabled
	@Test
	void lab2_E2_select() {
        WebDriver driver = new ChromeDriver();
		
		try {
            driver.get("https://www.selenium.dev/selenium/web/formPage.html");

            WebElement lista = driver.findElement(By.name("selectomatic"));
            Select opciones = new Select(lista);

            opciones.selectByVisibleText("Four");
            
            System.out.println(opciones.getFirstSelectedOption().getText());
            
        } finally {
            driver.quit();
        }
	}
	
	
	@Disabled
	@Test
	void demoEsperaFija() throws InterruptedException {
	    System.out.println("Antes de la pausa");
	    Thread.sleep(15000); // 15 000 milisegundos: 15 segundos
	    System.out.println("Después de la pausa");
	}
	
	
	//Espera Fija: esperá 15 segundos.
	//Espera implicita: es una espera geenral de todas las búsquedas (esperá hasta 15 segundos que aparezca el elemento).
	//Espera explcita: esperá a que pase algo, hasta un tiempo máximo.
	
}
