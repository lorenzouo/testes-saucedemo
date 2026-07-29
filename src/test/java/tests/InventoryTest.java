package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryTest {

    WebDriver driver;
    LoginPage loginPage;
    InventoryPage cartPage;
    InventoryPage inventoryPage;

    String usuario = "standard_user";
    String senha = "secret_sauce";
    String telaDetalhesDoProduto = "https://www.saucedemo.com/inventory-item.html?id=4";
    String telaAposLogin = "https://www.saucedemo.com/inventory.html";
    String telaDeLogin = "https://www.saucedemo.com/";
    String telaAbout = "https://saucelabs.com/";

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--password-store=basic");
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        cartPage = new InventoryPage(driver);
        inventoryPage = new InventoryPage(driver);

        loginPage.inserirUsuario(usuario);
        loginPage.inserirSenha(senha);
        loginPage.clicarBotao();
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    public void adicionarProdutos_contadorAtualizado(int quantidade) {
        List<WebElement> botoes = driver.findElements(By.className("btn_inventory"));

        for (int i = 0; i < quantidade; i++) {
            botoes.get(i).click();
        }

        String quantidadeAtual = cartPage.obterQuantidadeCarrinho();

        assertEquals(String.valueOf(quantidade), quantidadeAtual);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    public void removerProdutos_contadorAtualizado(int quantidade) {
        // adiciona quantidade de produtos
        List<WebElement> botoesAdd = driver.findElements(By.className("btn_inventory"));
        for (int i = 0; i < quantidade; i++) {
            botoesAdd.get(i).click();
        }

        // remove todos os produtos
        List<WebElement> botoesRemove = driver.findElements(By.className("btn_secondary"));
        for (int i = 0; i < quantidade; i++) {
            botoesRemove.get(i).click();
        }

        // verifica carrinho vazio
        boolean carrinhoVazio = driver.findElements(By.className("shopping_cart_badge")).isEmpty();
        assertTrue(carrinhoVazio);
    }

    @Test
    public void visualizarDetalhesProdutoEVoltar() {
        inventoryPage.detalhesDoProduto();

        // espera chegar na página do produto
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("inventory-item.html"));

        inventoryPage.voltarAoInventory();

        // espera voltar pro inventário
        wait.until(ExpectedConditions.urlContains("inventory.html"));

        assertEquals(telaAposLogin, driver.getCurrentUrl());
    }

    @Test
    public void realizarLogout() {
        inventoryPage.menu();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));

        inventoryPage.logout();
        wait.until(ExpectedConditions.urlContains("saucedemo.com"));

        assertEquals(telaDeLogin, driver.getCurrentUrl());
    }

    @Test
    public void aboutPage() {
        inventoryPage.menu();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("about_sidebar_link")));

        inventoryPage.about();
        wait.until(ExpectedConditions.urlContains(telaAbout));

        assertEquals(telaAbout, driver.getCurrentUrl());
    }
}