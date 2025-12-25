package ru.zankov.wiki.browser.scenarios;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.zankov.wiki.core.config.SettingsProvider;

import java.time.Duration;

public class WebTestFoundation {

    protected WebDriver webDriver;
    protected String targetUrl;

    @BeforeMethod
    public void initializeWebDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.addArguments("--start-maximized");
        browserOptions.addArguments("--disable-notifications");
        browserOptions.addArguments("--disable-popup-blocking");

        webDriver = new ChromeDriver(browserOptions);

        int implicitTimeout = SettingsProvider.getImplicitTimeout();
        int pageTimeout = SettingsProvider.getPageTimeout();

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitTimeout));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageTimeout));

        targetUrl = SettingsProvider.getBrowserUrl();
    }

    @AfterMethod
    public void terminateWebDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    protected void openMainPage() {
        webDriver.get(targetUrl);
    }

    protected void pause() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
