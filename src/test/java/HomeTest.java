import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import pages.LoginPage;
import java.time.Duration;
import java.util.Date;

import static org.junit.Assert.assertEquals;
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

//        Cookie cookieConsent = new Cookie("CookieConsent", "{stamp:%27XVSlLUrv5h+jrCaFjttdFKu0RMxeq3kH3SJqzl7DePNnFGK+N70i0w==%27%2Cnecessary:true%2Cpreferences:false%2Cstatistics:false%2Cmarketing:false%2Cver:1%2Cutc:1652100955062%2Cregion:%27am%27}", "www.hltv.org", "/", new Date("01/01/2100"));
//        driver.manage().addCookie(cookieConsent);
    }


    @Test
    public void titleTest() {
        HomePage homePage = new HomePage(driver);
        assertTrue("Page should be open", homePage.isPageOpen());
        assertEquals("Title should be equal", "CS:GO News & Coverage | HLTV.org", homePage.getPageTitle());
    }

    @Test
    public void hoverTest() throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.acceptCookies();

        HomePage homePage = new HomePage(driver);
        assertTrue("Page should be open", homePage.isPageOpen());
        assertTrue("Article should have hover effect",  homePage.moveToArticleAndCheckHover(4));
    }



    @After
    public void close() {
        if (driver != null ) {
            driver.close();
        }
    }
}
