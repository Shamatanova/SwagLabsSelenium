package sausedemotests;

import core.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    @Test
    public void userAuthenticated_when_validCredentialsProvided(){
        loginPage.navigate();

        loginPage.fillLoginForm("standard_user", "secret_sauce");
        inventoryPage.waitForPageTitle();

        // Add Assert
        inventoryPage.assertNavigated();
    }
}
