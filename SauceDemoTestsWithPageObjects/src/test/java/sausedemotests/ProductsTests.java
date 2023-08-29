package sausedemotests;

import core.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.CheckoutCompletePage;
import pages.CheckoutOverviewPage;
import pages.InventoryPage;

import static Utils.Constants.NUMBER_OF_PRODUCTS;

public class ProductsTests extends BaseTest {
    @BeforeEach
    public void beforeTest() {
        loginPage.navigate();
        loginPage.fillLoginForm("standard_user", "secret_sauce");
        inventoryPage.waitForPageTitle();
    }

    @Test
    public void productAddedToShoppingCart_when_addToCart() {

        String backpackTitle = "Sauce Labs Backpack";
        String shirtTitle = "Sauce Labs Bolt T-Shirt";

        inventoryPage.addProductsInShoppingCartViaTitle(backpackTitle, shirtTitle);
        inventoryPage.clickShoppingCartLink();

        // Assert Items and Totals
        var items = shoppingCartPage.findItems();

        Assertions.assertEquals(2, items.size(), "Items count not as expected");

        Assertions.assertEquals(backpackTitle, shoppingCartPage.getProductTitleViaIndex(0),
                "Item title not as expected");
        Assertions.assertEquals(shirtTitle, shoppingCartPage.getProductTitleViaIndex(1),
                "Item title not as expected");
    }

    @Test
    public void userDetailsAdded_when_checkoutWithValidInformation() {

        // Add Backpack and T-shirt to shopping cart
        inventoryPage.addProducts();
        shoppingCartPage.clickCheckoutButton();

        // fill form
        checkoutYourInformationPage.fillShippingDetails("Fname", "lname", "zip");
        checkoutYourInformationPage.clickTheContinueButton();

        var items = checkoutOverviewPage.findItems();
        Assertions.assertEquals(NUMBER_OF_PRODUCTS, items.size(), "Items count not as expected");

        var total = checkoutOverviewPage.getTotalPrice();
        double totalPrice = checkoutOverviewPage.sumForAllProductsInShoppingCart()
                + checkoutOverviewPage.calculateTax();

        Assertions.assertEquals(NUMBER_OF_PRODUCTS, items.size(), "Items count not as expected");
        Assertions.assertEquals(String.format("Total: $%.2f", totalPrice), total,
                "Items total price not as expected");
    }

    @Test
    public void orderCompleted_when_addProduct_and_checkout_withConfirm() {
        // Add Backpack and T-shirt to shopping cart
        inventoryPage.addProducts();
        // Fill Contact Details
        shoppingCartPage.clickCheckoutButton();
        checkoutYourInformationPage.fillDetails();
        checkoutOverviewPage.checkoutOverview();

        // Complete Order Assert
        Assertions.assertTrue(checkoutCompletePage.titleIsDisplayed(),
                "Page title is different than expected.");
        Assertions.assertTrue(checkoutCompletePage.headerIsDisplayed(),
                "Page header is different than expected.");
        Assertions.assertTrue(checkoutCompletePage.completeTextIsDisplayed(),
                "Complete message description is different than expected.");
        Assertions.assertTrue(checkoutCompletePage.backButtonIsDisplayed(), "Back button is not visible.");

        checkoutCompletePage.clickBackButton();

        // Assert Items removed from Shopping Cart
        Assertions.assertTrue(inventoryPage.pageTitleIsDisplayed(), "Page title is different than expected.");
        Assertions.assertTrue(inventoryPage.productsListIsDisplayed(), "Products are not visible.");
        Assertions.assertTrue(inventoryPage.shoppingCartIsEmpty(), "Shopping Cart is not empty.");
    }
}