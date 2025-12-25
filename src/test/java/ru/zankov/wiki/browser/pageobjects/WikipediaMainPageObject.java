package ru.zankov.wiki.browser.pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.zankov.wiki.core.base.AbstractPageObject;

public class WikipediaMainPageObject extends AbstractPageObject {

    @FindBy(id = "searchInput")
    private WebElement searchTextField;

    @FindBy(id = "n-randompage")
    private WebElement randomArticleMenuItem;

    @FindBy(id = "vector-main-menu-dropdown-checkbox")
    private WebElement mainMenuToggle;

    @FindBy(linkText = "Contents")
    private WebElement contentsMenuItem;

    @FindBy(className = "mw-logo-icon")
    private WebElement wikiLogo;

    @FindBy(id = "n-currentevents")
    private WebElement currentEventsMenuItem;

    public WikipediaMainPageObject(WebDriver webDriver) {
        super(webDriver);
    }

    public void executeSearch(String queryText) {
        inputText(searchTextField, queryText);
        searchTextField.sendKeys(Keys.ENTER);
    }

    public void navigateToRandomArticle() {
        mainMenuToggle.click();
        pauseExecution(2000);
        performClick(randomArticleMenuItem);
    }

    public void navigateToContents() {
        mainMenuToggle.click();
        pauseExecution(2000);
        performClick(contentsMenuItem);
    }

    public void navigateToCurrentEvents() {
        mainMenuToggle.click();
        pauseExecution(2000);
        performClick(currentEventsMenuItem);
    }

    public boolean isWikiLogoVisible() {
        return wikiLogo.isDisplayed();
    }

    public boolean isSearchFieldVisible() {
        return searchTextField.isDisplayed();
    }
}

