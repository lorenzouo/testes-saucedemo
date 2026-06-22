package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void adicionarAoCarrinho(){
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    }

    public String obterQuantidadeCarrinho(){
        return driver.findElement(By.className("shopping_cart_badge")).getText();
    }

    public void removerDoCarrinho(){
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
    }



}