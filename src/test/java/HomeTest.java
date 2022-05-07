import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import pages.LoginPage;
import java.time.Duration;

import static org.junit.Assert.assertTrue;


public class HomeTest {
    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        this.driver.get("https://www.hltv.org/");
    }

    @Test
    public void hoverTest() throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.acceptCookies();

        HomePage homePage = new HomePage(driver);
        assertTrue("Article should have hover effect",  homePage.moveToArticleAndCheckHover(4));
    }



    @After
    public void close() {
        if (driver != null ) {
            driver.close();
        }
    }
}
