package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Objects;


public class LoginPage extends BasePage {
    @FindBy(xpath = "//div[@id='loginpopup']/form/input[1]")
    private WebElement usernameElement;

    @FindBy(xpath = "//div[@id='loginpopup']/form/input[2]")
    private WebElement passwordElement;

    @FindBy(xpath = "//div[@id='loginpopup']/form/button[@class='login-button button']")
    private WebElement submitElement;

    @FindBy(xpath = "//div[@class='navsignin']")
    private WebElement loginModalElement;

    @FindBy(xpath = "//div[@class='nav-popup-elm'][3]/a[@id='signout-link']/div[@class='a-default']")
    private WebElement logoutElement;

    @FindBy(id = "CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")
    private WebElement acceptCookie;

    private final By profileLinkLocator = By.xpath("//div[@class='nav-popup-elm border-bottom']/a[@class='a-reset']/div[@class='a-default']");
    private final By currentEmailLocator = By.xpath("//div[@class='main-content-box']/div[@class='columns'][1]/div[@class='col'][1]/div[@class='current-email strong-text']");
    private final By errorLocator = By.xpath("//div[@id='loginpopup']/form/div[@class='login-error']");
    private final By headerPopupLocator = By.xpath("/html/body[@class='cols1111']/div[@class='navbar']/nav[@class='navcon']/div[@class='navdown']/i[@class='fa fa-caret-down']");

    public LoginPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }

    public void setDefaultCookies() {
        Cookie cookie = new Cookie("CookieConsent", "{stamp:%27XVSlLUrv5h+jrCaFjttdFKu0RMxeq3kH3SJqzl7DePNnFGK+N70i0w==%27%2Cnecessary:true%2Cpreferences:false%2Cstatistics:false%2Cmarketing:false%2Cver:1%2Cutc:1652100955062%2Cregion:%27am%27}", "www.hltv.org", "/", new Date("01/01/2100"));
        driver.manage().addCookie(cookie);
    }

    public void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookie)).click();
    }

    public void openLoginModal() {
        wait.until(ExpectedConditions.elementToBeClickable(loginModalElement)).click();
    }

    public void setUsername(String username) throws Exception {
        try {
            usernameElement.clear();
            usernameElement.sendKeys(username);
        } catch (Exception e) {
            throw new Exception("An error occurred while setting username: " + e.getMessage());
        }
    }

    public void setPassword(String password) throws Exception {
        try {
            passwordElement.clear();
            passwordElement.sendKeys(password);
        } catch (Exception e) {
            throw new Exception("An error occurred while setting password: " + e.getMessage());
        }
    }

    public void submitForm() {
        wait.until(ExpectedConditions.elementToBeClickable(submitElement)).click();
    }

    public void openHeaderPopup()  {
        try {
            Thread.sleep(2000);
            WebElement el = this.waitVisibilityAndFindElement(headerPopupLocator);
            wait.until(ExpectedConditions.elementToBeClickable(el)).click();

            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutElement)).click();
    }

    public boolean isAuth() throws Exception {
        try {
            Thread.sleep(2000);

            return driver.manage().getCookieNamed("autologin") != null;
        } catch (Exception e) {
            throw new Exception("An error occurred while checking cookies: " + e.getMessage());
        }
    }

    public void goToProfilePage() {
        this.wait.until(ExpectedConditions.presenceOfElementLocated(profileLinkLocator)).click();
    }

    public WebElement getCurrentEmailElement() throws NoSuchElementException {
        try {
            return this.waitVisibilityAndFindElement(currentEmailLocator);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find current email element: " + e.getMessage());
        }
    }

    public WebElement getError() throws NoSuchElementException {
        try {
            return this.waitVisibilityAndFindElement(errorLocator);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find error element: " + e.getMessage());
        }
    }

    public void login() throws Exception {
        acceptCookies();
        openLoginModal();
        setUsername("asoshnikov");
        setPassword("d_f6P_2@NWmvxaB");
        submitForm();
    }
}

