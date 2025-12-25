package ru.zankov.wiki.browser.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.zankov.wiki.core.base.AbstractPageObject;

import java.util.List;

public class ArticlePageObject extends AbstractPageObject {

    @FindBy(id = "firstHeading")
    private WebElement headingElement;

    @FindBy(css = "#mw-content-text p")
    private List<WebElement> contentParagraphs;

    @FindBy(css = "#vector-toc")
    private WebElement tocSection;

    @FindBy(css = "#catlinks")
    private WebElement categoryLinksSection;

    @FindBy(css = ".interlanguage-link")
    private List<WebElement> interlanguageLinks;

    public ArticlePageObject(WebDriver webDriver) {
        super(webDriver);
    }

    public String retrieveHeading() {
        awaitElementPresence(headingElement);
        return headingElement.getText();
    }

    public boolean isHeadingVisible() {
        try {
            awaitElementPresence(headingElement);
            return headingElement.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public int countParagraphs() {
        return contentParagraphs.size();
    }

    public boolean hasCategoryLinks() {
        try {
            return categoryLinksSection.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public int countInterlanguageLinks() {
        return interlanguageLinks.size();
    }
}

