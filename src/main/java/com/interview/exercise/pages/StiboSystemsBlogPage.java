package com.interview.exercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StiboSystemsBlogPage extends BasePage {
    private static final By EMAIL_INPUT_BOX = By.xpath("//input[contains(@class, 'hs-input') and @type = 'email' and @name = 'email']");
    private static final By SEND_BUTTON = By.xpath("//input[@value = 'Send' and @class = 'hs-button primary large']");
    private static final By INVALID_EMAIL_MESSAGE_TEXT = By.xpath("//ul[@class = 'hs-error-msgs inputs-list']/li/label");
    private static final By CORRECT_EMAIL_MESSAGE_TEXT = By.xpath("//div[@class = 'submitted-message']");
    private static final By LINKED_IN_ICON_LINK_BUTTON = By.cssSelector("a[class=icon-linkedin]");
    private static final By SEE_ALL_CATEGORIES_BUTTON = By.xpath("//a[@class= 'filter-expand-link']");
    private static final By CMDM_CATEGORY_LINK = By.xpath("//a[text() = 'Customer Master Data Management (CMDM)']");


    public StiboSystemsBlogPage(final WebDriver driver, final WebDriverWait webDriverWait, final FluentWait fluentWait) {
        super(driver, webDriverWait, fluentWait);
    }

    public StiboSystemsBlogPage fillEmailInputBoxAndSend(final String inputEmail) {
        fillInputWithEmail(inputEmail);
        send();
        return this;
    }

    public StiboSystemsBlogPage expandAllCategories() {
        WebElement seeAllCategories = webDriverWait.until(ExpectedConditions.elementToBeClickable(SEE_ALL_CATEGORIES_BUTTON));
        seeAllCategories.click();
        return this;
    }

    public StiboSystemsBlogPage clickOnCMDMCategory(){
        WebElement chooseCategory = webDriverWait.until(ExpectedConditions.elementToBeClickable(CMDM_CATEGORY_LINK));
        chooseCategory.click();
        return this;
    }

    public LinkedInSignUpPage clickLinkedInIcon() {
        WebElement linkedInIconButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(LINKED_IN_ICON_LINK_BUTTON));
        linkedInIconButton.click();

        return new LinkedInSignUpPage(driver, webDriverWait, fluentWait);
    }

    public String getIncorrectEmailFormatErrorMessage() {
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(INVALID_EMAIL_MESSAGE_TEXT)).getText();
    }

    public String getCorrectEmailFormatErrorMessage() {
        WebElement subscribingSuccesfullMessage = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(CORRECT_EMAIL_MESSAGE_TEXT));
        return subscribingSuccesfullMessage.getText();
    }

    private void fillInputWithEmail(final String email) {
        WebElement inputEmail = webDriverWait.until(ExpectedConditions.elementToBeClickable(EMAIL_INPUT_BOX));
        inputEmail.clear();
        inputEmail.click();
        inputEmail.sendKeys(email);
    }

    private void send() {
        WebElement submitButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(SEND_BUTTON));
        submitButton.click();
    }
}
