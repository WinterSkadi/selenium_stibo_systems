package com.interview.exercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchPage extends BasePage {
    private static final By INPUT_BOX = By.id("lst-ib");
    private static final By SUBMIT_BUTTON = By.name("btnK");

    public GoogleSearchPage(final WebDriver driver, final WebDriverWait webDriverWait, final FluentWait fluentWait) {
        super(driver, webDriverWait, fluentWait);
    }

    public GoogleSearchResultsPage searchText(final String inputText) {
        fillGoogleSearchInput(inputText);
        submitSearch();
        return new GoogleSearchResultsPage(driver, webDriverWait, fluentWait);
    }

    private void fillGoogleSearchInput(final String inputText) {
        WebElement googleSearchInput = webDriverWait.until(ExpectedConditions.elementToBeClickable(INPUT_BOX));
        googleSearchInput.click();
        googleSearchInput.sendKeys(inputText + Keys.TAB);
    }

    private void submitSearch() {
        WebElement searchSubmitButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(SUBMIT_BUTTON));
        searchSubmitButton.click();
    }
}
