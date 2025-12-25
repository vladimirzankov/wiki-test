package ru.zankov.wiki.android.screenobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import ru.zankov.wiki.core.base.AbstractScreenObject;

public class ArticleViewScreenObject extends AbstractScreenObject {

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Navigate up']")
    private WebElement navigationBackButton;

    @AndroidFindBy(className = "android.webkit.WebView")
    private WebElement articleWebContent;

    public ArticleViewScreenObject(AppiumDriver appDriver) {
        super(appDriver);
    }

    public boolean isArticleContentVisible() {
        pauseExecution(3000);
        return checkElementVisibility(articleWebContent);
    }

    public void navigateBack() {
        if (checkElementVisibility(navigationBackButton)) {
            performClick(navigationBackButton);
        }
    }
}

