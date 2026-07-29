package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void irParaCarrinho() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    public void clicarCheckout() {
        driver.findElement(By.id("checkout")).click();
    }

    public void continueShopping() {
        driver.findElement(By.id("continue-shopping")).click();
    }

    public void redirecionamentoItem() {
        driver.findElement(By.className("inventory_item_name")).click();
    }
}