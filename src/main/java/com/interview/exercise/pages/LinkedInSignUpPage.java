package com.interview.exercise.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LinkedInSignUpPage extends BasePage {

    private static final By joinLinkedInForm = By.id("join-form");
    private static final By joinLinkedInButton = By.id("join-submit");

    public LinkedInSignUpPage(final WebDriver driver, final WebDriverWait webDriverWait, final FluentWait fluentWait) {
        super(driver, webDriverWait, fluentWait);
    }

    public LinkedInSignUpPage linkedInPage() {
        clickOnJoinButton();
        return this;
    }

    public boolean checkIfSignUpFormExists() {
        try {
            WebElement element = webDriverWait
                    .ignoring(NoSuchElementException.class)
                    .withTimeout(Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(joinLinkedInForm));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    private void clickOnJoinButton() {
        WebElement joinButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(joinLinkedInButton));
        joinButton.click();
    }
}
