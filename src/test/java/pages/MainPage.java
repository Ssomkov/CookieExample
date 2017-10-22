package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.fail;

/**
 * Created by ssomkov on 20.10.2017.
 */
public class MainPage {

    Wait<WebDriver> wait;

    @FindBy(xpath = "//div[contains(@class, 'btn-group margin_top_53')]/a[contains(@class, 'btn btn-primary')]")
    private WebElement memberLoginButton;

    public void init(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 15).withMessage("Элемент не найден");
    }

    private void clickMemberLoginButton() {
        try {
            wait.until(ExpectedConditions.visibilityOf(memberLoginButton));
            memberLoginButton.click();
        } catch (NoSuchElementException e) {
            fail("Элемент memberLoginButton не найден");
        }
    }

    public void openAuthorizationPage(WebDriver driver) {
        this.clickMemberLoginButton();
        String currentWindow = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {
            if (!winHandle.equals(currentWindow)) {
                driver.switchTo().window(winHandle);
            }
        }
    }
}
