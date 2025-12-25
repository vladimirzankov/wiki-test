package ru.zankov.wiki.android.scenarios;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.zankov.wiki.android.screenobjects.ArticleSearchScreenObject;
import ru.zankov.wiki.android.screenobjects.ArticleViewScreenObject;
import ru.zankov.wiki.android.screenobjects.WelcomeFlowScreenObject;
import ru.zankov.wiki.android.screenobjects.WikiHomeScreenObject;

public class ArticleViewScenarioTest extends AndroidTestFoundation {

    @BeforeClass
    public void prepareTestEnvironment() {
        pause();
        WelcomeFlowScreenObject welcomeScreenObject = new WelcomeFlowScreenObject(appDriver);
        welcomeScreenObject.finishWelcomeFlow();
        pause();
    }

    @Test
    public void verifyArticleContentDisplay() {
        WikiHomeScreenObject homeScreenObject = new WikiHomeScreenObject(appDriver);
        homeScreenObject.tapSearchArea();

        pause();

        ArticleSearchScreenObject searchScreenObject = new ArticleSearchScreenObject(appDriver);
        searchScreenObject.submitSearchQuery("Appium");
        searchScreenObject.selectFirstItem();

        ArticleViewScreenObject articleScreenObject = new ArticleViewScreenObject(appDriver);
        Assert.assertTrue(articleScreenObject.isArticleContentVisible(),
                "Article content should be displayed");
    }

    @Test
    public void verifyBackNavigation() {
        appDriver.navigate().back();

        ArticleViewScreenObject articleScreenObject = new ArticleViewScreenObject(appDriver);
        articleScreenObject.navigateBack();

        pause();

        String pageContent = appDriver.getPageSource();
        Assert.assertTrue(pageContent.contains("search") ||
                        pageContent.contains("Search"),
                "Should return to search screen");
    }
}
