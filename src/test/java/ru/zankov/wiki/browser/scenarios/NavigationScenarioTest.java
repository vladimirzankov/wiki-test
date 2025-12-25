package ru.zankov.wiki.browser.scenarios;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zankov.wiki.browser.pageobjects.WikipediaMainPageObject;

public class NavigationScenarioTest extends WebTestFoundation {

    private WikipediaMainPageObject mainPageObject;

    @BeforeMethod
    public void prepareTestEnvironment() {
        openMainPage();
        mainPageObject = new WikipediaMainPageObject(webDriver);
    }

    @Test
    public void verifyMainPageLoads() {
        Assert.assertTrue(mainPageObject.isWikiLogoVisible(), "Wikipedia logo should be visible");
        Assert.assertTrue(mainPageObject.isSearchFieldVisible(), "Search field should be visible");
        Assert.assertTrue(webDriver.getTitle().contains("Wikipedia"), "Page title should contain Wikipedia");
    }

    @Test
    public void verifyContentsNavigation() {
        mainPageObject.navigateToContents();
        Assert.assertTrue(webDriver.getCurrentUrl().contains("Contents") ||
                        webDriver.getTitle().contains("Contents"),
                "Should navigate to Contents page");
    }

    @Test
    public void verifyCurrentEventsNavigation() {
        mainPageObject.navigateToCurrentEvents();
        Assert.assertTrue(webDriver.getCurrentUrl().contains("Current_events") ||
                        webDriver.getTitle().contains("Current events"),
                "Should navigate to Current Events page");
    }

    @Test
    public void verifyRandomArticleNavigation() {
        String originalUrl = webDriver.getCurrentUrl();
        mainPageObject.navigateToRandomArticle();

        String newUrl = webDriver.getCurrentUrl();
        Assert.assertNotEquals(originalUrl, newUrl, "URL should change after navigation");
        Assert.assertTrue(webDriver.getTitle().length() > 0, "Random article should have a title");
    }
}
