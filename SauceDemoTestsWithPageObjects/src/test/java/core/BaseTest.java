package core;

import enums.BrowserTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.time.Duration;

public class BaseTest {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public LoginPage loginPage;
    public InventoryPage inventoryPage;
    public ShoppingCartPage shoppingCartPage;
    public CheckoutYourInformationPage checkoutYourInformationPage;
    public CheckoutOverviewPage checkoutOverviewPage;
    public CheckoutCompletePage checkoutCompletePage;

    @AfterEach
    public void afterTest(){
        // close driver
        driver.close();
    }

    @BeforeEach
    public void beforeTests(){
        driver = startBrowser(BrowserTypes.CHROME);

        // Configure wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        inventoryPage = new InventoryPage(driver, wait);
        loginPage = new LoginPage(driver, wait);
        shoppingCartPage = new ShoppingCartPage(driver,wait);
        checkoutYourInformationPage = new CheckoutYourInformationPage(driver,wait);
        checkoutOverviewPage = new CheckoutOverviewPage(driver,wait);
        checkoutCompletePage = new CheckoutCompletePage(driver, wait);

        // Navigate to Google.com
        driver.get("https://www.saucedemo.com/");
    }

    protected static WebDriver startBrowser(BrowserTypes browserType) {
        // Setup Browser
        switch (browserType){
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                return new ChromeDriver(chromeOptions);
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                return new FirefoxDriver(firefoxOptions);
            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                return new EdgeDriver(edgeOptions);
            default:
                throw new IllegalArgumentException("The Browser Type is Undefined");
        }
    }
}