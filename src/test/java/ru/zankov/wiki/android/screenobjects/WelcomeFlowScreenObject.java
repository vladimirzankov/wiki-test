package ru.zankov.wiki.android.screenobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import ru.zankov.wiki.core.base.AbstractScreenObject;

public class WelcomeFlowScreenObject extends AbstractScreenObject {

    @AndroidFindBy(id = "primaryTextView")
    private WebElement welcomeTitle;

    @AndroidFindBy(id = "fragment_onboarding_forward_button")
    private WebElement continueButton;

    @AndroidFindBy(id = "fragment_onboarding_skip_button")
    private WebElement skipFlowButton;

    @AndroidFindBy(id = "fragment_onboarding_done_button")
    private WebElement finishButton;

    public WelcomeFlowScreenObject(AppiumDriver appDriver) {
        super(appDriver);
    }

    public void tapContinue() {
        performClick(continueButton);
    }

    public void tapSkip() {
        if (checkElementVisibility(skipFlowButton)) {
            performClick(skipFlowButton);
        }
    }

    public void tapFinish() {
        if (checkElementVisibility(finishButton)) {
            performClick(finishButton);
        }
    }

    public void finishWelcomeFlow() {
        try {
            if (checkElementVisibility(skipFlowButton)) {
                tapSkip();
            } else {
                for (int step = 0; step < 4; step++) {
                    if (checkElementVisibility(continueButton)) {
                        tapContinue();
                    } else if (checkElementVisibility(finishButton)) {
                        tapFinish();
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

