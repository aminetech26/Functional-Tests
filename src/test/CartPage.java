package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By subtotalPrice = By.xpath("//div[@data-test-id='SUBTOTAL']//span/span/span");
    private By quantity = By.id("gh-cart-n");

    // Actions
    public String getSubtotalPrice() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement price = wait.until(ExpectedConditions.visibilityOfElementLocated(subtotalPrice));
        return price.getText();
    }

    public String getQuantity() {
        return driver.findElement(quantity).getText();
    }
}
