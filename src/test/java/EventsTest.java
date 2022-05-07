import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import pages.enums.EventTypeFilters;
import pages.enums.Filters;
import pages.enums.FiltersTimeRange;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class EventsTest {
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
    public void filtersTest() throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.acceptCookies();

        HomePage homePage = new HomePage(driver);
        homePage.gotToEventsPage();

        EventsPage eventsPage = new EventsPage(driver);
        assertTrue("Events page is opened", eventsPage.isPageOpened());

        eventsPage.openTypeFilter(Filters.PrizePool);
        eventsPage.setPrizePoolUpper(40);
        assertTrue("Prize pool filter applied", eventsPage.isPrizePoolFilterApplied());

        eventsPage.setEventTimeRange(FiltersTimeRange.All);
        assertTrue("Events time range filter applied", eventsPage.isEventTimeRangeFilterApplied(FiltersTimeRange.All));


        eventsPage.openTypeFilter(Filters.EventType);
        eventsPage.setEventType(EventTypeFilters.Major);
        eventsPage.setEventType(EventTypeFilters.InternationalLAN);

        EventTypeFilters[] selectedEventTypeFilters = new EventTypeFilters[]{EventTypeFilters.InternationalLAN, EventTypeFilters.Major};
        assertTrue("Events time range filter applied", eventsPage.isEventTypeFilterApplied(selectedEventTypeFilters));
    }

    @After
    public void close() {
        if (driver != null ) {
            driver.close();
        }
    }
}
