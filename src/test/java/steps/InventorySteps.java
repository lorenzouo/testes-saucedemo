package steps;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.InventoryPage;
import pages.LoginPage;
import io.cucumber.java.After;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class InventorySteps {

    WebDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;
    String telaAposLogin = "https://www.saucedemo.com/inventory.html";
    String telaDetalhesDoProduto = "https://www.saucedemo.com/inventory-item.html?id=4";
    String telaDeLogin = "https://www.saucedemo.com/";
    String telaAbout = "https://saucelabs.com/";

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }




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

    }



    @When("I click the menu button")
    public void iClickTheMenuButton() {
        inventoryPage.menu();
    }

    @And("I click the logout button")
    public void iClickTheLogoutButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));

        // força o clique via JavaScript
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("document.getElementById('logout_sidebar_link').click()");
    }
    @Then("I should redirected to the login page")
    public void iShouldBeRedirectedToTheLoginPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("inventory")));

        assertEquals(telaDeLogin, driver.getCurrentUrl());
    }


    @And("I click the about button")
    public void iClickTheAboutButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("about_sidebar_link")));

        // força o clique via JavaScript
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("document.getElementById('about_sidebar_link').click()");
    }
    @Then("I should redirected to the about page")
    public void iShouldBeRedirectedToTheAboutPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("inventory")));

        assertEquals(telaAbout, driver.getCurrentUrl());
    }

}

