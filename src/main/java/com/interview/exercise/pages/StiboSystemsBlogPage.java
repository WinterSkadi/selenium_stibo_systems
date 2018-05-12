package com.interview.exercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StiboSystemsBlogPage extends BasePage {
    private static final By popUpWindow = By.cssSelector("div[class=leadin-preview-wrapper]");
    private static final By popUpWindowClose = By.cssSelector("div[class=leadinModal-close]");
    private static final By emailInputBox = By.xpath("//input[contains(@class, 'hs-input') and @type = 'email' and @name = 'email']");
    private static final By sendButton = By.xpath("//input[@value = 'Send' and @class = 'hs-button primary large']");
    private static final By invalidEmailMessageText = By.xpath("//ul[@class = 'hs-error-msgs inputs-list']/li/label");
    private static final By correctEmailMessageText = By.xpath("//div[@class = 'submitted-message']");


    public StiboSystemsBlogPage(final WebDriver driver, final WebDriverWait webDriverWait, final FluentWait fluentWait) {
        super(driver, webDriverWait, fluentWait);
    }

    public StiboSystemsBlogPage fillEmailInputBoxAndSend(final String inputEmail) {
        fillInputWithEmail(inputEmail);
        send();
        return this;
    }

    public String getIncorrectEmailFormatErrorMessage() {
        return driver.findElement(invalidEmailMessageText).getText();
    }

    public String getCorrectEmailFormatErrorMessage() {
        WebElement subsctribingSuccesfullMessage = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(correctEmailMessageText));
        String value = subsctribingSuccesfullMessage.getText();
        return value;
    }

    private void fillInputWithEmail(String email) {
        WebElement inputEmail = webDriverWait.until(ExpectedConditions.elementToBeClickable(emailInputBox));
        inputEmail.clear();
        inputEmail.click();
        inputEmail.sendKeys(email);
    }

    private void send() {
        WebElement submitButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(sendButton));
        submitButton.click();
    }

    private void closePopUpWindow() {
    }
}
