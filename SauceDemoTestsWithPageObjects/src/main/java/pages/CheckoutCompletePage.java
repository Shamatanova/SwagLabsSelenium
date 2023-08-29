package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static Utils.Constants.COMPLETE_TEXT;
import static Utils.Constants.HEADER_TEXT;

public class CheckoutCompletePage extends BasePage {
    public CheckoutCompletePage(WebDriver webDriver, WebDriverWait driverWait) {
        super(webDriver, driverWait, "checkout-complete.html");
    }

    private final By titleLocator = By.xpath("//span[@class='title' and text()='Checkout: Complete!']");
    private final By headerLocator = By.xpath(String.format("//h2[@class='complete-header' and text()='%s']", HEADER_TEXT));
    private final By completeTextLocator = By.xpath(String.format("//div[@class='complete-text' and text()='%s']", COMPLETE_TEXT));
    private final By backButtonLocator = By.id("back-to-products");

    public boolean titleIsDisplayed() {
        return driver.findElement(titleLocator).isDisplayed();
    }

    public boolean headerIsDisplayed() {
        return driver.findElement(headerLocator).isDisplayed();
    }

    public boolean completeTextIsDisplayed(){
        return driver.findElement(completeTextLocator).isDisplayed();
    }

    public boolean backButtonIsDisplayed(){
        return driver.findElement(backButtonLocator).isDisplayed();
    }

    public void clickBackButton(){
        driver.findElement(backButtonLocator).click();
    }
}
