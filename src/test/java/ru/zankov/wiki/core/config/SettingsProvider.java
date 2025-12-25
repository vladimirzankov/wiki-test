package ru.zankov.wiki.core.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SettingsProvider {

    private static final Properties settings;

    static {
        settings = new Properties();
        try (FileInputStream inputStream = new FileInputStream("src/test/resources/config.properties")) {
            settings.load(inputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Unable to load configuration file");
        }
    }

    public static String getValue(String propertyKey) {
        return settings.getProperty(propertyKey);
    }

    public static int getIntValue(String propertyKey) {
        return Integer.parseInt(settings.getProperty(propertyKey));
    }

    public static String getBrowserUrl() {
        return getValue("browser.target.url");
    }

    public static int getImplicitTimeout() {
        return getIntValue("browser.implicit.timeout");
    }

    public static int getPageTimeout() {
        return getIntValue("browser.page.timeout");
    }

    public static String getAndroidPlatform() {
        return getValue("android.os.name");
    }

    public static String getAndroidDevice() {
        return getValue("android.device.id");
    }

    public static String getAppPackageName() {
        return getValue("android.app.package");
    }

    public static String getAppMainActivity() {
        return getValue("android.app.activity");
    }

    public static String getAutomationEngine() {
        return getValue("android.automation.engine");
    }

    public static String getAppiumEndpoint() {
        return getValue("android.appium.endpoint");
    }
}

