package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import java.util.List;

public class HomePage extends BasePage {
    @FindBy(xpath = "//div[@class='navbar']/nav[@class='navcon']/a[@class='navforums no-promode']")
    private WebElement forumLinkElement;

    @FindBy(xpath = "//div[@class='navbar']/nav[@class='navcon']/a[@class='navevents']")
    private WebElement eventsLinkElement;

    public HomePage(WebDriver driver){
        super(driver);

        PageFactory.initElements(driver, this);
    }

    public void goToForumPage() {
        forumLinkElement.click();
    }
    public void gotToEventsPage() { eventsLinkElement.click();}
}
