package ru.zankov.wiki.android.scenarios;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.zankov.wiki.core.config.SettingsProvider;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidTestFoundation {

    protected AndroidDriver appDriver;

    @BeforeClass
    public void initializeAppiumDriver() throws MalformedURLException {
        Capabilities driverCapabilities = new UiAutomator2Options()
                .setPlatformName(SettingsProvider.getAndroidPlatform())
                .setDeviceName(SettingsProvider.getAndroidDevice())
                .setAppPackage(SettingsProvider.getAppPackageName())
                .setAppActivity(SettingsProvider.getAppMainActivity())
                .setAutomationName(SettingsProvider.getAutomationEngine())
                .setAppWaitPackage(SettingsProvider.getAppPackageName())
                .setNoReset(true)
                .setFullReset(false);

        appDriver = new AndroidDriver(new URL(SettingsProvider.getAppiumEndpoint()), driverCapabilities);
    }

    @AfterClass
    public void terminateAppiumDriver() {
        if (appDriver != null) {
            appDriver.terminateApp(SettingsProvider.getAppPackageName());
            appDriver.quit();
        }
    }

    protected void pause() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
