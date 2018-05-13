package com.interview.exercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LinkedInSignUpPage extends BasePage {
    private static final By JOIN_LINKED_IN_FORM = By.id("join-form");
    private static final By JOIN_LINKED_IN_BUTTON = By.id("join-submit");

    public LinkedInSignUpPage(final WebDriver driver, final WebDriverWait webDriverWait, final FluentWait fluentWait) {
        super(driver, webDriverWait, fluentWait);
    }

    public LinkedInSignUpPage joinLinkedIn() {
        WebElement joinButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(JOIN_LINKED_IN_BUTTON));
        joinButton.click();
        return this;
    }

    public boolean checkIfSignUpFormExists() {
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(JOIN_LINKED_IN_FORM));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }
}
