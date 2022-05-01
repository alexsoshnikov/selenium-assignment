import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Objects;


public class LoginPage extends BasePage {
    @FindBy(xpath = "//div[@id='loginpopup']/form/input[@class='loginInput'][1]")
    private WebElement usernameElement;

    @FindBy(xpath = "//div[@id='loginpopup']/form/input[@class='loginInput'][2]")
    private WebElement passwordElement;

    @FindBy(xpath = "/html/body[@class='cols1111']/div[@id='overlay']/div[@id='overlay-stack']/div[@id='loginpopup']/div[@class='fixed-overlay-popup-content']/div[@id='loginpopup']/form/button[@class='login-button button']")
    private WebElement submitElement;

    @FindBy(xpath = "//div[@class='navsignin']")
    private WebElement loginModalElement;

    @FindBy(xpath = "//div[@class='navdown']/i[@class='fa fa-caret-down']")
    private WebElement headerPopupElement;

    @FindBy(xpath = "//div[@class='nav-popup-elm'][3]/a[@id='signout-link']/div[@class='a-default']")
    private WebElement logoutElement;

    private final By profileLinkLocator = By.xpath("//div[@class='nav-popup-elm border-bottom']/a[@class='a-reset']/div[@class='a-default']");
    private final By currentEmailLocator = By.xpath("//div[@class='main-content-box']/div[@class='columns'][1]/div[@class='col'][1]/div[@class='current-email strong-text']");

    public LoginPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }

    public void openLoginModal() {
        loginModalElement.click();
    }

    public void setUsername(String username) {
        usernameElement.clear();
        usernameElement.sendKeys(username);
    }

    public void setPassword(String password) {
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    public void submitForm() {
        try {
            Thread.sleep(1000);

            submitElement.click();

            //captcha
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void openHeaderPopup() {
        headerPopupElement.click();
    }

    public void logout() {
        logoutElement.click();
    }

    public boolean isAuth() {
        return !Objects.equals(loginModalElement.getText(), "Sign in");
    }

    public void goToProfilePage() {
        try {
            WebElement profileLink = this.driver.findElement(profileLinkLocator);

            this.wait.until(ExpectedConditions.presenceOfElementLocated(profileLinkLocator));
            profileLink.click();

            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WebElement getCurrentEmailElement() {
        return this.waitVisibilityAndFindElement(currentEmailLocator);
    }
}

