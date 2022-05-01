import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.Assert.*;

public class LoginTest {
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
    public void loginTest() {
        LoginPage loginPage = new LoginPage(this.driver);

        loginPage.openLoginModal();
        loginPage.setUsername("asoshnikov");
        loginPage.setPassword("d_f6P_2@NWmvxaB");
        loginPage.submitForm();

        loginPage.openHeaderPopup();
        loginPage.goToProfilePage();

        WebElement currentEmail = loginPage.getCurrentEmailElement();

        assertEquals("The email must be specified on the profile page", "hh84jb@inf.elte.hu", currentEmail.getText());
    }


    @Test
    public void logoutTest() {
        LoginPage loginPage = new LoginPage(this.driver);

        loginPage.openLoginModal();
        loginPage.setUsername("asoshnikov");
        loginPage.setPassword("d_f6P_2@NWmvxaB");
        loginPage.submitForm();

        loginPage.openHeaderPopup();
        loginPage.logout();

        assertFalse("The login button should be displayed", loginPage.isAuth());
    }

    @After
    public void close() {
        if (driver != null ) {
            driver.close();
        }
    }
}
