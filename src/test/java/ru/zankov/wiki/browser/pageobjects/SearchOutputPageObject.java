package ru.zankov.wiki.browser.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.zankov.wiki.core.base.AbstractPageObject;

import java.util.List;

public class SearchOutputPageObject extends AbstractPageObject {

    @FindBy(css = ".mw-search-result-heading a")
    private List<WebElement> foundItems;

    @FindBy(css = ".searchresults")
    private WebElement resultsContainer;

    public SearchOutputPageObject(WebDriver webDriver) {
        super(webDriver);
    }

    public int countFoundItems() {
        awaitElementPresence(resultsContainer);
        return foundItems.size();
    }

    public void selectFirstResult() {
        awaitElementPresence(resultsContainer);
        if (!foundItems.isEmpty()) {
            performClick(foundItems.get(0));
        }
    }

    public boolean hasResults() {
        try {
            awaitElementPresence(resultsContainer);
            return !foundItems.isEmpty();
        } catch (Exception ex) {
            return false;
        }
    }
}

