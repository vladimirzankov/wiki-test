package ru.zankov.wiki.android.screenobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import ru.zankov.wiki.core.base.AbstractScreenObject;

public class WikiHomeScreenObject extends AbstractScreenObject {

    @AndroidFindBy(id = "search_container")
    private WebElement searchArea;

    public WikiHomeScreenObject(AppiumDriver appDriver) {
        super(appDriver);
    }

    public boolean isHomeScreenVisible() {
        return checkElementVisibility(searchArea);
    }

    public void tapSearchArea() {
        performClick(searchArea);
    }

    public boolean isSearchAreaVisible() {
        return checkElementVisibility(searchArea);
    }
}

