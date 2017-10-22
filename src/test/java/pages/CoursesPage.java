package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.fail;

/**
 * Created by ssomkov on 20.10.2017.
 */
public class CoursesPage extends MainPage {

    @FindBy(xpath = "//img[contains(@class, 'gravatar')]")
    private WebElement avatar;

    public boolean isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOf(avatar));
        } catch (NoSuchElementException e) {
            fail("Элемент не найден");
        }
        return avatar.isDisplayed();
    }
}
