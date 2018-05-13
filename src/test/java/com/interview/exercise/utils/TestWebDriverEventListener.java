package com.interview.exercise.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;


import java.time.Duration;

public class TestWebDriverEventListener extends AbstractWebDriverEventListener {
    private static final By popupClose = By.cssSelector("div[class=leadinModal-close]");

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        //todo: run only on desired page
        closePotentialPopup(driver);
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        //todo: run only on desired page
        closePotentialPopup(driver);
    }

    private void closePotentialPopup(WebDriver driver) {
        FluentWait<WebDriver> fluentWait = new FluentWait(driver)
                .pollingEvery(Duration.ofMillis(50))
                .withTimeout(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);

        try {
            WebElement closeButton = fluentWait.until(ExpectedConditions.elementToBeClickable(popupClose));
            if (closeButton != null) {
                closeButton.click();
            }
        } catch (TimeoutException timeoutException) {
        }
    }
}
