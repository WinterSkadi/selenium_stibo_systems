package com.interview.exercise.eventlistener;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class WebDriverPopUpEventListener extends AbstractWebDriverEventListener {
    private static final By POPUP_CLOSE_BUTTON = By.cssSelector("div.leadinModal-close");
    private static final By POPUP_OVERLAY = By.cssSelector("div.leadinModal-overlay");
    private static final String PAGE_URL = "blog.stibosystems.com";
    private final FluentWait<WebDriver> popUpFluentWait;

    private boolean handlePopUp = true;

    public WebDriverPopUpEventListener(FluentWait<WebDriver> popUpFluentWait) {
        this.popUpFluentWait = popUpFluentWait;
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        closePotentialPopup(driver);
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        closePotentialPopup(driver);
    }

    private void closePotentialPopup(final WebDriver driver) {
        if (handlePopUp == false || driver.getCurrentUrl().contains(PAGE_URL) == false) {
            return;
        }

        try {
            WebElement closeButton = popUpFluentWait.until(ExpectedConditions.elementToBeClickable(POPUP_CLOSE_BUTTON));
            if (closeButton != null) {
                closeButton.click();
                popUpFluentWait.until(ExpectedConditions.invisibilityOfElementLocated(POPUP_OVERLAY));
                handlePopUp = false;
            }
        } catch (TimeoutException timeoutException) {
        }
    }
}
