package com.interview.exercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StiboSystemsBlogPage extends BasePage {
    private static final By emailInputBox = By.xpath("//input[contains(@class, 'hs-input') and @type = 'email' and @name = 'email']");
    private static final By sendButton = By.xpath("//input[@value = 'Send' and @class = 'hs-button primary large']");
    private static final By invalidEmailMessageText = By.xpath("//ul[@class = 'hs-error-msgs inputs-list']/li/label");
    private static final By correctEmailMessageText = By.xpath("//div[@class = 'submitted-message']");
    private static final By linkedInIcon = By.cssSelector("a[class=icon-linkedin]");
    private static final By seeAllCategoriesButton = By.xpath("//a[@class= 'filter-expand-link']");
    private static final By selectCategoryButton = By.xpath("//a[text() = 'Customer Master Data Management (CMDM)']");


    public StiboSystemsBlogPage(final WebDriver driver, final WebDriverWait webDriverWait, final FluentWait fluentWait) {
        super(driver, webDriverWait, fluentWait);
    }

    public StiboSystemsBlogPage fillEmailInputBoxAndSend(final String inputEmail) {
        fillInputWithEmail(inputEmail);
        send();
        return this;
    }

    public StiboSystemsBlogPage expandAllCategoriesListAndClickOnCategory() {
        seeAllCategories();
        selectCategory();
        return this;
    }

    public LinkedInSignUpPage clickLinkedInIcon() {
        WebElement linkedInIconButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(linkedInIcon));
        linkedInIconButton.click();

        return new LinkedInSignUpPage(driver, webDriverWait, fluentWait);
    }

    public String getIncorrectEmailFormatErrorMessage() {
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(invalidEmailMessageText)).getText();
    }

    public String getCorrectEmailFormatErrorMessage() {
        WebElement subscribingSuccesfullMessage = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(correctEmailMessageText));
        String value = subscribingSuccesfullMessage.getText();
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

    private void seeAllCategories() {
        WebElement seeAllCategories = webDriverWait.until(ExpectedConditions.elementToBeClickable(seeAllCategoriesButton));
        seeAllCategories.click();
    }
    private void selectCategory(){
        WebElement chooseCategory = webDriverWait.until(ExpectedConditions.elementToBeClickable(selectCategoryButton));
        chooseCategory.click();
    }
}
