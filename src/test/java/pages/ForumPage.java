package pages;

import org.openqa.selenium.*;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ForumPage extends BasePage {
    private final By rulesElement = By.xpath("//div[@class='forum-rules']/div[@class='standard-box padding']/div[@class='newsdsl']/div[@class='newstext-con']/h2[1]");
    private final By topicsLocator = By.xpath("//div[@class='standard-box']/div[@class='forumContainer']");
    private final By threadTitleLocator = By.xpath("//div[@class='forumthreads']/span[@class='standard-headline'][2]");
    private final By replyTextareaLocator = By.xpath("//div[@class='newreply-con']/div[@class='standard-box']/textarea[@class='newreply-textarea']");
    private final By replyBtnLocator = By.xpath("//div[@class='forum no-promode']/div[@class='newreply-con']/button[@class='button newreply-post']");
    private final By errorLocator = By.xpath("//div[@class='forum no-promode']/div[@class='newreply-con']/div[@class='error-message']");

    public ForumPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpened() {
        return this.waitVisibilityAndFindElement(rulesElement).getText().contains("RULES");
    }

    public WebElement getForumThreads(Integer idxOfThread) throws NoSuchElementException {
        try {
            List<WebElement> threads = this.waitVisibilityAndFindElements(topicsLocator);

            return threads.get(idxOfThread);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Thread not found: index error, " + e.getMessage());
        }
    }

    public String getThreadTitle() throws NoSuchElementException {
        try {
            return this.waitVisibilityAndFindElement(threadTitleLocator).getText();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find thread title: " + e.getMessage());
        }
    }

    public WebElement getThreadTopic(Integer idxOfTopic) throws NoSuchElementException {
        try {
            return this.waitVisibilityAndFindElement(By.xpath("//div[@class='forumthreads']/table[@class='table standard-box']/tbody/tr[@class='tablerow'][" + idxOfTopic + "]/td[@class='name']/a"));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find topic " + idxOfTopic + " : " + e.getMessage());
        }
    }

    public void setMessage(String message) throws NoSuchElementException {
        try {
            WebElement textarea = this.waitVisibilityAndFindElement(replyTextareaLocator);

            textarea.clear();
            textarea.sendKeys(message);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Textarea wasn't found: " + e);
        }
    }

    public void sendMessage() throws Exception {
        try {
            WebElement replyBtn = this.waitVisibilityAndFindElement(replyBtnLocator);

            replyBtn.click();

            Thread.sleep(1000);
        } catch (Exception e) {
            throw new Exception("An error occurred while sending the message: " + e.getMessage());
        }
    }

    public boolean hasError() throws NoSuchElementException {
        try {
            WebElement error = this.waitVisibilityAndFindElement(errorLocator);

            return error.getText().contains("An error occurred");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find error element: " + e.getMessage());
        }
    }


    public boolean hasTextOnThePage(String[] texts) throws NoSuchElementException {
        try {
            List<WebElement> textElements = Arrays.stream(texts)
                    .map(val -> this.waitVisibilityAndFindElement(By.xpath("//*[contains(text(),'" + val + "')]")))
                    .toList();

            return textElements.size() > 0;
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Could find element with text on the page " + e.getMessage());
        }
    }
}
