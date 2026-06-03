package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LoginTest {

    String usuario = "standard_user";
    String senha = "secret_sauce";
    String telaAposLogin = "https://www.saucedemo.com/inventory.html";

    String usuarioInvalido = "cool_user";
    String senhaInvalida = "secret_apple";
    String seletorErro = "[data-test='error']";
    String mensagemEsperada = "Epic sadface: Username and password do not match any user in this service";

    @Test
    public void loginSucesso() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        System.out.println("Título: " + driver.getTitle());

        driver.findElement(By.id("user-name")).sendKeys(usuario);
        driver.findElement(By.id("password")).sendKeys(senha);
        driver.findElement(By.id("login-button")).click();

        String urlAtual = driver.getCurrentUrl();

        assertEquals(telaAposLogin,urlAtual);

        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void loginInvalido() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        System.out.println("Título: " + driver.getTitle());

        driver.findElement(By.id("user-name")).sendKeys(usuarioInvalido);
        driver.findElement(By.id("password")).sendKeys(senhaInvalida);
        driver.findElement(By.id("login-button")).click();

        String mensagemAtual = driver.findElement(By.cssSelector(seletorErro)).getText();

        assertEquals(mensagemEsperada, mensagemAtual);

        Thread.sleep(3000);
        driver.quit();
    }


}

