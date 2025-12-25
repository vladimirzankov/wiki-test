package ru.zankov.wiki.browser.scenarios;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zankov.wiki.browser.pageobjects.ArticlePageObject;
import ru.zankov.wiki.browser.pageobjects.WikipediaMainPageObject;

public class ArticleScenarioTest extends WebTestFoundation {

    private WikipediaMainPageObject mainPageObject;
    private ArticlePageObject articlePageObject;

    @BeforeMethod
    public void prepareTestEnvironment() {
        openMainPage();
        mainPageObject = new WikipediaMainPageObject(webDriver);
    }

    @Test
    public void verifyArticlePage() {
        pause();
        mainPageObject.executeSearch("Appium");
        articlePageObject = new ArticlePageObject(webDriver);
        pause();

        Assert.assertTrue(articlePageObject.isHeadingVisible(),
                "Article heading should be visible");
        Assert.assertTrue(articlePageObject.hasCategoryLinks(),
                "Article should include category links section");
    }
}
