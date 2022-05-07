package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.enums.EventTypeFilters;
import pages.enums.Filters;
import org.openqa.selenium.interactions.Actions;
import pages.enums.FiltersTimeRange;

import java.util.List;

public class EventsPage extends BasePage {

    @FindBy(xpath = "//h1[@class='event-status-headline']")
    private WebElement eventsTitleElement;

    @FindBys(@FindBy(css = ".tab.text-ellipsis"))
    private List<WebElement> allEventFilterElements;

    @FindBys(@FindBy(className = "filter-column-header"))
    private List<WebElement> typeFilterElements;

    @FindBys(@FindBy(className = "sidebar-single-line-item"))
    private List<WebElement> filterElements;

    @FindBy(css = ".noUi-handle.noUi-handle-lower")
    private WebElement sliderLowerElement;

    @FindBy(css = ".noUi-handle.noUi-handle-upper")
    private WebElement sliderUpperElement;

    public EventsPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {
        return eventsTitleElement.getText().contains("Ongoing events");
    }

    public void setEventTimeRange(FiltersTimeRange filter) throws Exception {
        try {
            allEventFilterElements.get(filter.getValue()).click();
            this.waitForPageToLoad();
        } catch (Exception e) {
            throw new Exception("An error occurred while switching time events filter: " + e.getMessage());
        }
    }

    public void openTypeFilter(Filters filter) throws InterruptedException {
        try {
            WebElement checkedFilter = typeFilterElements.get(filter.getValue());
            if (!checkedFilter.getAttribute("class").contains("open")) {
                checkedFilter.click();
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            throw new InterruptedException("An error occurred while toggling filter types: " + e.getMessage());
        }
    }

    public void setPrizePoolLower(Integer lower) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions
                    .clickAndHold(sliderLowerElement)
                    .moveByOffset(lower, 0)
                    .click()
                    .perform();

            this.waitForPageToLoad();

        } catch (Exception e) {
            throw new Exception("An error occurred while clicking or holding prize pool filter: " + e.getMessage());
        }
    }

    public void setPrizePoolUpper(Integer upper) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions
                    .clickAndHold(sliderUpperElement)
                    .moveByOffset(-upper, 0)
                    .click()
                    .perform();

            this.waitForPageToLoad();

        } catch (Exception e) {
            throw new Exception("An error occurred while clicking or holding prize pool filter: " + e.getMessage());
        }
    }


    public void setEventType(EventTypeFilters type) throws Exception {
        try {
            WebElement checkedFilter = filterElements.get(type.getValue());
            checkedFilter.click();

            this.waitForPageToLoad();
        } catch (Exception e) {
            throw new Exception("An error occurred while setting event type filter: " + e.getMessage());
        }
    }

    public boolean isPrizePoolFilterApplied() {
        return driver.getCurrentUrl().contains("prizeMin") || driver.getCurrentUrl().contains("prizeMax");
    }


    public boolean isEventTimeRangeFilterApplied(FiltersTimeRange filter) {
        return switch (filter) {
            case All -> driver.getCurrentUrl().contains("ALL");
            case Today -> driver.getCurrentUrl().contains("TODAY");
            case Featured -> driver.getCurrentUrl().contains("FEATURED");
        };
    }

    public boolean isEventTypeFilterApplied(EventTypeFilters[] filters) {
        for(EventTypeFilters f : filters) if(!this.eventTypeFilterUrlMapper(f)) return false;
        return true;
    }

    private boolean eventTypeFilterUrlMapper(EventTypeFilters filter) {
        return switch (filter) {
            case All -> true;
            case Major -> driver.getCurrentUrl().contains("MAJOR");
            case InternationalLAN -> driver.getCurrentUrl().contains("INTLLAN");
            case RegionalLAN -> driver.getCurrentUrl().contains("REGIONALLAN");
            case Online -> driver.getCurrentUrl().contains("ONLINE");
            case LocalLan -> driver.getCurrentUrl().contains("LOCALLAN");
            case Other -> driver.getCurrentUrl().contains("OTHER");
        };
    }
}
