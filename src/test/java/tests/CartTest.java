package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest {

    WebDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    String usuario = "standard_user";
    String senha = "secret_sauce";
    String telaCompraSucesso = "https://www.saucedemo.com/checkout-complete.html";
    String telaInventoryPage = "https://www.saucedemo.com/inventory.html";
    String telaItemPage = "https://www.saucedemo.com/inventory-item.html?id=4";

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        loginPage.inserirUsuario(usuario);
        loginPage.inserirSenha(senha);
        loginPage.clicarBotao();
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void checkout_fluxoCompleto_sucesso() {
        inventoryPage.adicionarAoCarrinho();
        cartPage.irParaCarrinho();
        cartPage.clicarCheckout();
        checkoutPage.preencherFirstName("Lorenzo");
        checkoutPage.preencherLastName("Luciano");
        checkoutPage.preencherZipCode("00000000");
        checkoutPage.clicarContinue();
        checkoutPage.clicarFinish();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains(telaCompraSucesso));
        String urlAtual = driver.getCurrentUrl();

        assertEquals(telaCompraSucesso, urlAtual);
    }

    @Test
    public void continueShopping() {
        inventoryPage.adicionarAoCarrinho();
        cartPage.irParaCarrinho();
        cartPage.continueShopping();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains(telaInventoryPage));
        String urlAtual = driver.getCurrentUrl();
        assertEquals(telaInventoryPage, urlAtual);
    }

    @Test
    public void cartToItemPage() {
        inventoryPage.adicionarAoCarrinho();
        cartPage.irParaCarrinho();
        cartPage.redirecionamentoItem();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains(telaItemPage));
        String urlAtual = driver.getCurrentUrl();
        assertEquals(telaItemPage, urlAtual);
    }
}