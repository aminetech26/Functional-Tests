package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {
    private WebDriver driver;

    // Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locator
    private By addToCartButton = By.id("atcBtn_btn_1");

    // Actions
    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }
}
