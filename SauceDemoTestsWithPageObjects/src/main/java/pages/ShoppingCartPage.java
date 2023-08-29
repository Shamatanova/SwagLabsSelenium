package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPage  extends BasePage {
    public ShoppingCartPage(WebDriver webDriver, WebDriverWait driverWait) {
        super(webDriver, driverWait, "cart.html");
    }

    private final By checkoutButtonLocator = By.id("checkout");
    private final By itemsLocator = By.className("inventory_item_name");

    public List<WebElement> findItems()
    {
        return driver.findElements(itemsLocator);
    }

    public void clickCheckoutButton()
    {
        driver.findElement(checkoutButtonLocator).click();
    }

    public String getProductTitleViaIndex(int index) {
        return findItems().get(index).getText();
    }

}
