package ru.zankov.wiki.android.scenarios;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.zankov.wiki.android.screenobjects.ArticleSearchScreenObject;
import ru.zankov.wiki.android.screenobjects.WelcomeFlowScreenObject;
import ru.zankov.wiki.android.screenobjects.WikiHomeScreenObject;

public class ArticleSearchScenarioTest extends AndroidTestFoundation {

    @BeforeClass
    public void prepareTestEnvironment() {
        pause();
        WelcomeFlowScreenObject welcomeScreenObject = new WelcomeFlowScreenObject(appDriver);
        welcomeScreenObject.finishWelcomeFlow();
        pause();
    }

    @DataProvider(name = "articleQuerySet")
    public Object[][] provideArticleQueries() {
        return new Object[][]{
                {"Machine learning"},
                {"Data science"}
        };
    }

    @Test
    public void verifySearchFunctionality() {
        WikiHomeScreenObject homeScreenObject = new WikiHomeScreenObject(appDriver);
        homeScreenObject.tapSearchArea();

        pause();

        ArticleSearchScreenObject searchScreenObject = new ArticleSearchScreenObject(appDriver);
        searchScreenObject.submitSearchQuery("Appium");

        Assert.assertTrue(searchScreenObject.hasResults(),
                "Search should return results");
        Assert.assertTrue(searchScreenObject.countFoundItems() > 0,
                "At least one result expected");
    }

    @Test(dataProvider = "articleQuerySet")
    public void verifySearchWithMultipleQueries(String queryText) {
        pause();

        ArticleSearchScreenObject searchScreenObject = new ArticleSearchScreenObject(appDriver);
        searchScreenObject.submitSearchQuery(queryText);

        pause();

        Assert.assertTrue(searchScreenObject.hasResults(),
                "Search should display results for: " + queryText);
    }

    @Test
    public void verifyArticleOpensFromSearch() {
        pause();

        ArticleSearchScreenObject searchScreenObject = new ArticleSearchScreenObject(appDriver);
        searchScreenObject.submitSearchQuery("Appium");

        pause();

        String firstItemTitle = searchScreenObject.getFirstItemTitle();
        Assert.assertFalse(firstItemTitle.isEmpty(), "First result should have a title");

        searchScreenObject.selectFirstItem();
        pause();

        String pageContent = appDriver.getPageSource();
        Assert.assertFalse(pageContent.isEmpty(), "Article page should load successfully");
    }
}
