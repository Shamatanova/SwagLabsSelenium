package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutYourInformationPage extends BasePage{
    public CheckoutYourInformationPage(WebDriver webDriver, WebDriverWait driverWait) {
        super(webDriver, driverWait, "checkout-step-one.html");
    }

    private final By continueButtonLocation = By.id("continue");
    private final By firstNameLocation = By.id("first-name");
    private final By lastNameLocation = By.id("last-name");
    private final By zipCodeLocation = By.id("postal-code");

    public void clickTheContinueButton()
    {
        driver.findElement(continueButtonLocation).click();
    }

    public void fillShippingDetails(String firstName, String lastName, String zip) {
        driver.findElement(firstNameLocation).sendKeys(firstName);
        driver.findElement(lastNameLocation).sendKeys(lastName);
        driver.findElement(zipCodeLocation).sendKeys(zip);
    }

    public void fillDetails()
    {
        fillShippingDetails("Fname", "lname", "zip");
        clickTheContinueButton();
    }
}
