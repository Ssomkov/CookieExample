package tests;

import cookie.CookiesHolder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.CoursesPage;
import pages.MainPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by ssomkov on 20.10.2017.
 */
public class AuthorizationTest {

    private WebDriver driver;
    private MainPage mainPage = new MainPage();
    private AuthorizationPage authorizationPage = new AuthorizationPage();
    private CoursesPage coursesPage = new CoursesPage();

    @BeforeMethod(alwaysRun = true)
    @Parameters("url")
    public void setUP(String url) throws MalformedURLException {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setBrowserName("chrome");
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test(enabled = true)
    @Parameters({"login", "password"})
    public void basicAuthorization(String login, String password) throws InterruptedException {
        mainPage.init(driver);
        mainPage.openAuthorizationPage(driver);
        authorizationPage.init(driver);
        authorizationPage.login(login, password);
        coursesPage.init(driver);
        CookiesHolder cookiesHolder = new CookiesHolder();
        cookiesHolder.saveCookies(driver);
        Assert.assertTrue(coursesPage.isPageOpened());
    }

    @Test(enabled = false)
    @Parameters("coursesUrl")
    public void cookieAuthorization(String coursesUrl) throws InterruptedException {
        CookiesHolder cookiesHolder = new CookiesHolder();
        driver.manage().addCookie(cookiesHolder.loadCookies());
        driver.navigate().to(coursesUrl);
        coursesPage.init(driver);
        Assert.assertTrue(coursesPage.isPageOpened());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
