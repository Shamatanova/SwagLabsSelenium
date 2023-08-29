package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static Utils.Constants.VAT;

public class CheckoutOverviewPage extends BasePage {
    public CheckoutOverviewPage(WebDriver webDriver, WebDriverWait driverWait) {
        super(webDriver, driverWait, "checkout-step-two.html");
    }

    private final By itemsLocator = By.className("cart_item");
    private final By itemsPriceLocator = By.className("inventory_item_price");
    private final By totalPriceLocator = By.className("summary_total_label");
    private final By finishButtonLocator = By.id("finish");

    public List<WebElement> findItems()
    {
        return driver.findElements(itemsLocator);
    }

    public String getTotalPrice()
    {
        return driver.findElement(totalPriceLocator).getText();
    }

    public double sumForAllProductsInShoppingCart() {
        double sum = 0;
        var items = findItems();
        for (var product : items) {
            var priceInStrings = product.findElement(itemsPriceLocator).getText();
            var price = Double.parseDouble(priceInStrings.substring(1));
            sum += price;
        }
        return sum;
    }

    public double calculateTax() {
        var price = sumForAllProductsInShoppingCart();
        return price * VAT;
    }

    public void clickFinishButton()
    {
        driver.findElement(finishButtonLocator).click();
    }

    public void checkoutOverview()
    {
        var items = findItems();

        var total = getTotalPrice();
        double totalPrice = sumForAllProductsInShoppingCart() + calculateTax();
        clickFinishButton();
    }
}
