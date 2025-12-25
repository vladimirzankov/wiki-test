package ru.zankov.wiki.android.screenobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import ru.zankov.wiki.core.base.AbstractScreenObject;

import java.util.List;

public class ArticleSearchScreenObject extends AbstractScreenObject {

    @AndroidFindBy(id = "search_src_text")
    private WebElement queryTextField;

    @AndroidFindBy(id = "page_list_item_title")
    private List<WebElement> foundItems;

    @AndroidFindBy(id = "search_lang_button")
    private WebElement languageSelector;

    public ArticleSearchScreenObject(AppiumDriver appDriver) {
        super(appDriver);
    }

    public void submitSearchQuery(String queryText) {
        inputText(queryTextField, queryText);
    }

    public int countFoundItems() {
        pauseExecution(2000);
        return foundItems.size();
    }

    public void selectFirstItem() {
        if (!foundItems.isEmpty()) {
            performClick(foundItems.get(0));
        }
    }

    public String getFirstItemTitle() {
        if (!foundItems.isEmpty()) {
            return foundItems.get(0).getText();
        }
        return "";
    }

    public boolean hasResults() {
        pauseExecution(1500);
        return !foundItems.isEmpty();
    }
}

