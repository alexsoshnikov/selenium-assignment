package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;

import java.util.List;
import java.util.Objects;

public class HomePage extends BasePage {
    @FindBy(xpath = "//div[@class='navbar']/nav[@class='navcon']/a[@class='navforums no-promode']")
    private WebElement forumLinkElement;

    @FindBy(xpath = "//div[@class='navbar']/nav[@class='navcon']/a[@class='navevents']")
    private WebElement eventsLinkElement;

    @FindBys(@FindBy(css = ".newsline.article"))
    private List<WebElement> articleElements;

    public HomePage(WebDriver driver){
        super(driver);

        PageFactory.initElements(driver, this);
    }

    public void goToForumPage() {
        forumLinkElement.click();
    }
    public void gotToEventsPage() { eventsLinkElement.click();}


    public boolean moveToArticleAndCheckHover(Integer idx) throws Exception {
        try {
            Actions actions = new Actions(driver);
            WebElement selectedArticle = articleElements.get(idx);

            actions.moveToElement(selectedArticle).perform();
            Thread.sleep(2000);

            return Objects.equals(selectedArticle.getCssValue("background-color"), "rgba(69, 81, 95, 1)");
        } catch (Exception e) {
            throw new Exception("An error occurred while moving: " + e.getMessage());
        }
    }
}
