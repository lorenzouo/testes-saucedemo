package tests;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;

    String usuario = "standard_user";
    String senha = "secret_sauce";
    String telaAposLogin = "https://www.saucedemo.com/inventory.html";
    String usuarioInvalido = "cool_user";
    String senhaInvalida = "secret_apple";
    String seletorErro = "[data-test='error']";
    String mensagemUsuarioSenhaInvalidos = "Epic sadface: Username and password do not match any user in this service";
    String mensagemUsuarioSenhaNulos = "Epic sadface: Username is required";

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    public void teardown(){
        driver.quit();
    }



    @Test
    public void login_credenciaisValidas_sucesso() throws InterruptedException {
        System.out.println("Título: " + driver.getTitle());

        loginPage.inserirUsuario(usuario);
        loginPage.inserirSenha(senha);
        loginPage.clicarBotao();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains(telaAposLogin));

        String urlAtual = driver.getCurrentUrl();

        assertEquals(telaAposLogin,urlAtual);
    }

    @Test
    public void login_credenciaisInvalidas_erroExibido() throws InterruptedException {
        System.out.println("Título: " + driver.getTitle());

        loginPage.inserirUsuario(usuarioInvalido);
        loginPage.inserirSenha(senhaInvalida);
        loginPage.clicarBotao();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(seletorErro)));

        String mensagemAtual = driver.findElement(By.cssSelector(seletorErro)).getText();

        assertEquals(mensagemUsuarioSenhaInvalidos, mensagemAtual);
    }

    @Test
    public void login_usuarioValido_senhaInvalida_erroExibido() throws InterruptedException {
        System.out.println("Título: " + driver.getTitle());

        loginPage.inserirUsuario(usuario);
        loginPage.inserirSenha(senhaInvalida);
        loginPage.clicarBotao();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(seletorErro)));

        String mensagemAtual = driver.findElement(By.cssSelector(seletorErro)).getText();

        assertEquals(mensagemUsuarioSenhaInvalidos, mensagemAtual);
    }

    @Test
    public void login_usuarioInvalido_senhaValida_erroExibido() throws InterruptedException {
        System.out.println("Título: " + driver.getTitle());

        loginPage.inserirUsuario(usuarioInvalido);
        loginPage.inserirSenha(senha);
        loginPage.clicarBotao();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(seletorErro)));

        String mensagemAtual = driver.findElement(By.cssSelector(seletorErro)).getText();

        assertEquals(mensagemUsuarioSenhaInvalidos, mensagemAtual);
    }

    @Test
    public void login_camposVazios_erroExibido() throws InterruptedException {
        System.out.println("Título: " + driver.getTitle());

        loginPage.inserirUsuario("");
        loginPage.inserirSenha("");
        loginPage.clicarBotao();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(seletorErro)));

        String mensagemAtual = driver.findElement(By.cssSelector(seletorErro)).getText();

        assertEquals(mensagemUsuarioSenhaNulos, mensagemAtual);
    }

    @Test
    public void login_usuarioVazio_senhaValida_erroExibido() throws InterruptedException {
        System.out.println("Título: " + driver.getTitle());

        loginPage.inserirUsuario("");
        loginPage.inserirSenha(senha);
        loginPage.clicarBotao();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(seletorErro)));

        String mensagemAtual = driver.findElement(By.cssSelector(seletorErro)).getText();

        assertEquals(mensagemUsuarioSenhaNulos, mensagemAtual);
    }

}

