package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LoginTest {

    String usuario = "standard_user";
    String senha = "secret_sauce";
    String telaAposLogin = "https://www.saucedemo.com/inventory.html";

    String usuarioInvalido = "cool_user";
    String senhaInvalida = "secret_apple";
    String seletorErro = "[data-test='error']";
    String mensagemUsuarioSenhaInvalidos = "Epic sadface: Username and password do not match any user in this service";
    String mensagemUsuarioSenhaNulos = "Epic sadface: Username is required";

    @Test
    public void login_credenciaisValidas_sucesso() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        System.out.println("Título: " + driver.getTitle());

        loginPage.inserirUsuario(usuario);
        loginPage.inserirSenha(senha);
        loginPage.clicarBotao();

        String urlAtual = driver.getCurrentUrl();

        assertEquals(telaAposLogin,urlAtual);

        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void login_credenciaisInvalidas_erroExibido() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        System.out.println("Título: " + driver.getTitle());

        loginPage.inserirUsuario(usuarioInvalido);
        loginPage.inserirSenha(senhaInvalida);
        loginPage.clicarBotao();

        String mensagemAtual = driver.findElement(By.cssSelector(seletorErro)).getText();

        assertEquals(mensagemUsuarioSenhaInvalidos, mensagemAtual);

        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void login_usuarioValido_senhaInvalida_erroExibido() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        System.out.println("Título: " + driver.getTitle());

        loginPage.inserirUsuario(usuario);
        loginPage.inserirSenha(senhaInvalida);
        loginPage.clicarBotao();

        String mensagemAtual = driver.findElement(By.cssSelector(seletorErro)).getText();

        assertEquals(mensagemUsuarioSenhaInvalidos, mensagemAtual);

        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void login_usuarioInvalido_senhaValida_erroExibido() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        System.out.println("Título: " + driver.getTitle());

        loginPage.inserirUsuario(usuarioInvalido);
        loginPage.inserirSenha(senha);
        loginPage.clicarBotao();

        String mensagemAtual = driver.findElement(By.cssSelector(seletorErro)).getText();

        assertEquals(mensagemUsuarioSenhaInvalidos, mensagemAtual);

        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void login_camposVazios_erroExibido() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        System.out.println("Título: " + driver.getTitle());

        loginPage.inserirUsuario("");
        loginPage.inserirSenha("");
        loginPage.clicarBotao();

        String mensagemAtual = driver.findElement(By.cssSelector(seletorErro)).getText();

        assertEquals(mensagemUsuarioSenhaNulos, mensagemAtual);

        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void login_usuarioVazio_senhaValida_erroExibido() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        System.out.println("Título: " + driver.getTitle());

        loginPage.inserirUsuario("");
        loginPage.inserirSenha(senha);
        loginPage.clicarBotao();

        String mensagemAtual = driver.findElement(By.cssSelector(seletorErro)).getText();

        assertEquals(mensagemUsuarioSenhaNulos, mensagemAtual);

        Thread.sleep(3000);
        driver.quit();
    }

}

