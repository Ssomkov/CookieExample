package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.fail;

/**
 * Created by ssomkov on 20.10.2017.
 */
public class AuthorizationPage extends MainPage {

    @FindBy(xpath = "//input[@id='user_email']")
    private WebElement loginField;

    @FindBy(xpath = "//input[@id='user_password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[contains(@class, 'btn btn-primary btn-md login-button')]")
    private WebElement loginButton;

    private void sendLogin(String login) {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginField));
            loginField.sendKeys(login);
        } catch (NoSuchElementException e) {
            fail("Элемент не найден");
        }
    }

    private void sendPassword(String password) {
        try {
            wait.until(ExpectedConditions.visibilityOf(passwordField));
            passwordField.sendKeys(password);
        } catch (NoSuchElementException e) {
            fail("Элемент не найден");
        }
    }

    private void clickMemberLoginButton() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginButton));
            loginButton.click();
        } catch (NoSuchElementException e) {
            fail("Элемент не найден");
        }
    }

    public void login(String login, String password) {
        this.sendLogin(login);
        this.sendPassword(password);
        this.clickMemberLoginButton();
    }
}
