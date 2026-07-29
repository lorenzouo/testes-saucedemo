package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void preencherFirstName(String firstName) {
        driver.findElement(By.id("first-name")).sendKeys(firstName);
    }

    public void preencherLastName(String lastName) {
        driver.findElement(By.id("last-name")).sendKeys(lastName);
    }

    public void preencherZipCode(String zipCode) {
        driver.findElement(By.id("postal-code")).sendKeys(zipCode);
    }

    public void clicarContinue() {
        driver.findElement(By.id("continue")).click();
    }

    public void clicarFinish() {
        driver.findElement(By.id("finish")).click();
    }
}