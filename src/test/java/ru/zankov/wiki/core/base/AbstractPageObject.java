package ru.zankov.wiki.core.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPageObject {

    protected WebDriver webDriver;
    protected WebDriverWait explicitWait;

    public AbstractPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.explicitWait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        PageFactory.initElements(webDriver, this);
    }

    protected void awaitElementPresence(WebElement element) {
        explicitWait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void awaitClickability(WebElement element) {
        explicitWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void performClick(WebElement element) {
        awaitClickability(element);
        element.click();
    }

    protected void inputText(WebElement element, String content) {
        awaitElementPresence(element);
        element.clear();
        element.sendKeys(content);
    }

    protected boolean checkElementVisibility(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    protected void pauseExecution(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}

