package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;

    String telaAposLogin = "https://www.saucedemo.com/inventory.html";

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @When("I enter valid username and password")
    public void iEnterValidUsernameAndPassword() {
        loginPage.inserirUsuario("standard_user");
        loginPage.inserirSenha("secret_sauce");
    }

    @And("I click the login button")
    public void iClickTheLoginButton() {
        loginPage.clicarBotao();
    }

    @Then("I should be redirected to the inventory page")
    public void iShouldBeRedirectedToTheInventoryPage() {
        assertEquals(telaAposLogin, driver.getCurrentUrl());
    }
}