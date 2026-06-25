package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.InventoryPage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class InventorySteps {

    WebDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;
    String telaAposLogin = "https://www.saucedemo.com/inventory.html";
    String telaDetalhesDoProduto = "https://www.saucedemo.com/inventory-item.html?id=4";




    @Given("I am on the inventory page")
    public void iamontheinventorypage() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        loginPage.inserirUsuario("standard_user");
        loginPage.inserirSenha("secret_sauce");
        loginPage.clicarBotao();
        assertEquals(telaAposLogin, driver.getCurrentUrl());
        assertEquals(telaAposLogin, driver.getCurrentUrl());
    }

    @When("I click the Product button")
    public void iClickTheProductButton() {
        inventoryPage.detalhesDoProduto();
    }

    @And("I shold redirected to the details page")
    public void iShouldBeRedirectedToTheDetailsPage() {
        assertEquals(telaDetalhesDoProduto, driver.getCurrentUrl());

    }

    @And("I click the Back button")
    public void iClickTheBackButton() {
        inventoryPage.voltarAoInventory();
    }

    @Then("I should redirected to the inventory page")
    public void iShouldRedirectedToTheInventoryPage() {
        assertEquals(telaAposLogin, driver.getCurrentUrl());
        driver.quit();

    }
}

