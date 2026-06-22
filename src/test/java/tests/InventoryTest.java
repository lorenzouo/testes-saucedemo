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
import pages.InventoryPage;
import pages.LoginPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryTest {

    WebDriver driver;
    LoginPage loginPage;
    InventoryPage cartPage;

    String usuario = "standard_user";
    String senha = "secret_sauce";

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        cartPage = new InventoryPage(driver);

        loginPage.inserirUsuario(usuario);
        loginPage.inserirSenha(senha);
        loginPage.clicarBotao();
    }

    @AfterEach
    public void teardown(){
        driver.quit();
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    public void adicionarProdutos_contadorAtualizado(int quantidade) {
        List<WebElement> botoes = driver.findElements(By.className("btn_inventory"));

        for(int i = 0; i < quantidade; i++) {
            botoes.get(i).click();
        }

        String quantidadeAtual = cartPage.obterQuantidadeCarrinho();

        assertEquals(String.valueOf(quantidade), quantidadeAtual);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    public void removerProdutos_contadorAtualizado(int quantidade) {
        /// adiciona quantidade de produtos
        List<WebElement> botoesAdd = driver.findElements(By.className("btn_inventory"));
        for(int i = 0; i < quantidade; i++) {
            botoesAdd.get(i).click();
        }

        /// remove todos os produtos
        List<WebElement> botoesRemove = driver.findElements(By.className("btn_secondary"));
        for(int i = 0; i < quantidade; i++) {
            botoesRemove.get(i).click();
        }

        /// verifica carrinho vazio
        boolean carrinhoVazio = driver.findElements(By.className("shopping_cart_badge")).isEmpty();
        assertTrue(carrinhoVazio);
    }




}
