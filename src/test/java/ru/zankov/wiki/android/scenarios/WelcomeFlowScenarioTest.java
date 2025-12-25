package ru.zankov.wiki.android.scenarios;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zankov.wiki.android.screenobjects.WelcomeFlowScreenObject;
import ru.zankov.wiki.android.screenobjects.WikiHomeScreenObject;

public class WelcomeFlowScenarioTest extends AndroidTestFoundation {

    @Test
    public void verifyAppLaunchAndWelcomeFlow() {
        pause();
        WelcomeFlowScreenObject welcomeScreenObject = new WelcomeFlowScreenObject(appDriver);
        welcomeScreenObject.finishWelcomeFlow();

        pause();
        WikiHomeScreenObject homeScreenObject = new WikiHomeScreenObject(appDriver);
        Assert.assertTrue(homeScreenObject.isHomeScreenVisible(),
                "Home screen should be visible after completing welcome flow");
    }

    @Test
    public void verifyHomeScreenElements() {
        pause();

        WelcomeFlowScreenObject welcomeScreenObject = new WelcomeFlowScreenObject(appDriver);
        welcomeScreenObject.finishWelcomeFlow();

        pause();

        WikiHomeScreenObject homeScreenObject = new WikiHomeScreenObject(appDriver);
        Assert.assertTrue(homeScreenObject.isSearchAreaVisible(),
                "Search area should be visible on home screen");
    }
}
