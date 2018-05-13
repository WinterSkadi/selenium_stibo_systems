package com.interview.exercise.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;


import java.time.Duration;

public class TestWebDriverPopUpEventListener extends AbstractWebDriverEventListener {
    private static final By popupClose = By.cssSelector("div.leadinModal-close");
    private static final By popupOverlay = By.cssSelector("div.leadinModal-overlay");

    private static final String pageUrl = "blog.stibosystems.com";
    private boolean handlePopUp = true;

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        closePotentialPopup(driver);
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        closePotentialPopup(driver);
    }

    private void closePotentialPopup(WebDriver driver) {
        if (handlePopUp == false || driver.getCurrentUrl().contains(pageUrl) == false) {
            return;
        }
        FluentWait<WebDriver> fluentWait = new FluentWait(driver)
                .pollingEvery(Duration.ofMillis(50))
                .withTimeout(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);

        try {
            WebElement closeButton = fluentWait.until(ExpectedConditions.elementToBeClickable(popupClose));
            if (closeButton != null) {
                closeButton.click();
                fluentWait.until(ExpectedConditions.invisibilityOfElementLocated(popupOverlay));
                handlePopUp = false;
            }
        } catch (TimeoutException timeoutException) {
        }
    }
}
