import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.time.Duration;

import static org.junit.Assert.*;

public class ForumTest {
    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        this.driver.get("https://www.hltv.org/");
    }


    @Test
    public void forumMessageTest() throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();
        assertTrue("The user is logged in", loginPage.isAuth());

        HomePage homePage = new HomePage(driver);
        homePage.goToForumPage();

        ForumPage forumPage = new ForumPage(driver);
        assertTrue("Forum page is opened", forumPage.isPageOpened());

        WebElement thread = forumPage.getForumThreads(0);
        thread.click();

        String threadTitle = forumPage.getThreadTitle();
        assertEquals("Checking the title of the thread", "Counter-Strike", threadTitle);

        WebElement topic = forumPage.getThreadTopic(3);
        topic.click();

        forumPage.setMessage("That's joke");
        forumPage.sendMessage();
        assertFalse("There shouldn't be an error", forumPage.hasError());

        String[] texts = { "asoshnikov", "That's joke" };
        assertTrue("The message is on the page", forumPage.hasTextOnThePage(texts));
    }

    @After
    public void close() {
        if (driver != null ) {
            driver.close();
        }
    }
}
