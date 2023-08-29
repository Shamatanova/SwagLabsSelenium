package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver webDriver, WebDriverWait driverWait) {
        super(webDriver, driverWait, "");
    }

    // Locators
    private final By usernameLocator = By.xpath("//input[@data-test='username']");
    private final By passwordLocator = By.xpath("//input[@data-test='password']");
    private final By loginButtonLocator = By.xpath("//input[@data-test='login-button']");

    public void fillLoginForm(String username, String pass) {
        WebElement usernameInput = driver.findElement(usernameLocator);
        usernameInput.sendKeys(username);

        WebElement password = driver.findElement(passwordLocator);
        password.sendKeys(pass);

        WebElement loginButton = driver.findElement(loginButtonLocator);
        loginButton.click();
    }
}