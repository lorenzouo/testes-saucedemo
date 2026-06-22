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
    String telaAposLogin = "https://www.saucedemo.com/inventory.html";

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



    @Test
    public void adicionarProduto_carrinhoAtualizado() {
        cartPage.adicionarAoCarrinho();

        String quantidade = cartPage.obterQuantidadeCarrinho();

        assertEquals("1", quantidade);
    }

    @Test
    public void removerProduto_carrinhoAtualizado() {
        cartPage.adicionarAoCarrinho();

        String quantidade = cartPage.obterQuantidadeCarrinho();

        assertEquals("1", quantidade);

        cartPage.removerDoCarrinho();

        boolean carrinhoVazio = driver.findElements(By.className("shopping_cart_badge")).isEmpty();

        assertTrue(carrinhoVazio);
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



}
