package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Objects;

public class InventoryPage extends BasePage {

    public InventoryPage(WebDriver webDriver, WebDriverWait driverWait) {
        super(webDriver, driverWait, "inventory.html");
    }

    // locators
    private final By shoppingCartLinkLocator = By.className("shopping_cart_link");
    private final By pageTitleLocator = By.xpath("//span[@class='title' and text()='Products']");
    private final By productLocator = By.className("inventory_item");
    private final By productTitleLocator = By.className("inventory_item_name");
    private final By addToCartLocator = By.className("btn_inventory");

    public void clickShoppingCartLink() {
        driver.findElement(shoppingCartLinkLocator).click();
    }

    public void waitForPageTitle() {
        var title = driver.findElement(pageTitleLocator);
        wait.until(ExpectedConditions.visibilityOf(title));
    }

    protected WebElement findProductByTitle(String productTitles) {

        List<WebElement> products = driver.findElements(productLocator);

        for (int i = 0; i < products.size(); i++) {
            var productTitle = products.get(i).findElement(productTitleLocator).getText();
            if (Objects.equals(productTitle, productTitles)) {
                return products.get(i);
            }
        }
        throw new IllegalArgumentException("Product with this name does not exist.");
    }

    public void addProductsInShoppingCartViaTitle(String... productTitles) {

        for (String title : productTitles) {
            WebElement product = findProductByTitle(title);
            product.findElement(addToCartLocator).click();
        }
    }

    public void addProducts() {
        String backpackTitle = "Sauce Labs Backpack";
        String shirtTitle = "Sauce Labs Bike Light";

        addProductsInShoppingCartViaTitle(backpackTitle, shirtTitle);
        clickShoppingCartLink();
    }

    public boolean pageTitleIsDisplayed() {
        return driver.findElement(pageTitleLocator).isDisplayed();
    }

    public boolean productsListIsDisplayed() {
        List<WebElement> products = driver.findElements(productLocator);
        if (products.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean shoppingCartIsEmpty(){
        var text = driver.findElement(shoppingCartLinkLocator).getText();
        if (text.isEmpty())
        {
            return true;
        }
        return false;
    }
}
