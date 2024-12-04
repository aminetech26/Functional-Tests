package test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestFonctionnel {
    @Test
    public void productPurchaseFunctionalTest(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.ebay.com/");
        driver.manage().window().maximize();
        String mainWindowHandle = driver.getWindowHandle();

        Select sel = new Select(driver.findElement(By.id("gh-cat")));
        sel.selectByValue("267"); //
        WebElement searchField = driver.findElement(By.id("gh-ac"));
        searchField.sendKeys("python in easy steps");
        WebElement searchButton = driver.findElement(By.id("gh-btn"));
        searchButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement bookLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Python in Easy Steps")));
        bookLink.click();
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        WebElement addToCartButton = driver.findElement(By.id("atcBtn_btn_1"));
        addToCartButton.click();
        WebDriverWait secondWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement price = secondWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-test-id='SUBTOTAL']//span/span/span")));
        WebElement quantity = driver.findElement(By.id("gh-cart-n"));
        assertEquals("US $52.43", price.getText());
        assertEquals("1", quantity.getText());
        assertEquals("https://cart.payments.ebay.com/?item=iid%3A193869997416%2Cqty%3A1",driver.getCurrentUrl());
        driver.quit();
    }
    @Test
    public void productPurchaseFunctionalTestWithPomPattern() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.ebay.com/");
        driver.manage().window().maximize();
        String mainWindowHandle = driver.getWindowHandle();

        HomePage homePage = new HomePage(driver);
        homePage.selectCategory("267");
        homePage.enterSearchTerm("python in easy steps");
        homePage.clickSearch();

        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.clickOnBookLink();

        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart();

        CartPage cartPage = new CartPage(driver);
        assertEquals("US $52.43", cartPage.getSubtotalPrice());
        assertEquals("1", cartPage.getQuantity());
        assertEquals("https://cart.payments.ebay.com/?item=iid%3A193869997416%2Cqty%3A1", driver.getCurrentUrl());

        driver.quit();
    }
}
