package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void adicionarAoCarrinho() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    }

    public String obterQuantidadeCarrinho() {
        return driver.findElement(By.className("shopping_cart_badge")).getText();
    }

    public void detalhesDoProduto() {
        driver.findElement(By.className("inventory_item_name")).click();
    }

    public void voltarAoInventory() {
        driver.findElement(By.id("back-to-products")).click();
    }

    public void menu() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
    }

    public void logout() {
        driver.findElement(By.id("logout_sidebar_link")).click();
    }

    public void about() {
        driver.findElement(By.id("about_sidebar_link")).click();
    }

    public void continueShopping() {
        driver.findElement(By.id("continue-shopping")).click();
    }
}