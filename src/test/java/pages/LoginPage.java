package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    @FindBy(xpath = "//div[@class='navdown']/i[@class='fa fa-caret-down']")
    private WebElement headerPopupElement;

    @FindBy(xpath = "//div[@class='nav-popup-elm'][3]/a[@id='signout-link']/div[@class='a-default']")
    private WebElement logoutElement;

    @FindBy(id = "CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")
    private WebElement acceptCookie;

    private final By profileLinkLocator = By.xpath("//div[@class='nav-popup-elm border-bottom']/a[@class='a-reset']/div[@class='a-default']");
    private final By currentEmailLocator = By.xpath("//div[@class='main-content-box']/div[@class='columns'][1]/div[@class='col'][1]/div[@class='current-email strong-text']");
    private final By errorLocator = By.xpath("//div[@id='loginpopup']/form/div[@class='login-error']");

    public LoginPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }


    public void acceptCookies() throws InterruptedException {
        try {
            Thread.sleep(2000);

            acceptCookie.click();
        } catch (InterruptedException e) {
            throw new InterruptedException("Accept cookies error: " + e.getMessage());
        }
    }

    public void openLoginModal() throws InterruptedException {
        try {
            loginModalElement.click();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new InterruptedException("An error occurred while opening login modal: " + e.getMessage());
        }
    }

    public void setUsername(String username) throws Exception {
        try {
            usernameElement.clear();
            usernameElement.sendKeys(username);
        } catch (Exception e) {
            throw new Exception("An error occurred while setting username: " + e.getMessage());
        }
    }

    public void setPassword(String password) throws InterruptedException {
        try {
            passwordElement.clear();
            passwordElement.sendKeys(password);
        } catch (Exception e) {
            throw new InterruptedException("An error occurred while setting password: " + e.getMessage());
        }
    }

    public void submitForm() throws InterruptedException {
        try {
            Thread.sleep(1000);

            submitElement.click();

            //captcha
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new InterruptedException("An error occurred while submitting login form: " + e.getMessage());
        }
    }

    public void openHeaderPopup() {
        headerPopupElement.click();
    }

    public void logout() {
        logoutElement.click();
    }

    public boolean isAuth() throws Exception {
        try {
            return !Objects.equals(loginModalElement.getText(), "Sign in");
        } catch (Exception e) {
            throw new Exception("An error occurred while checking auth: " + e.getMessage());
        }
    }

    public void goToProfilePage() throws InterruptedException {
        try {
            WebElement profileLink = this.driver.findElement(profileLinkLocator);

            this.wait.until(ExpectedConditions.presenceOfElementLocated(profileLinkLocator));
            profileLink.click();

            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new InterruptedException("An error occurred while opening profile page: " + e.getMessage());
        }
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

