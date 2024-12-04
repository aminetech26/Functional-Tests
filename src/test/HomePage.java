package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
    private WebDriver driver;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By categorySelect = By.id("gh-cat");
    private By searchField = By.id("gh-ac");
    private By searchButton = By.id("gh-btn");

    // Actions
    public void selectCategory(String value) {
        Select select = new Select(driver.findElement(categorySelect));
        select.selectByValue(value);
    }

    public void enterSearchTerm(String searchTerm) {
        driver.findElement(searchField).sendKeys(searchTerm);
    }

    public void clickSearch() {
        driver.findElement(searchButton).click();
    }
}
