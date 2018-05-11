package com.interview.exercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchPage {
    private static final By inputBox = By.id("lst-ib");
    private static final By submitButton = By.name("btnK");

    private final WebDriver driver;
    private final WebDriverWait webDriverWait;

    public GoogleSearchPage(final WebDriver driver, final WebDriverWait webDriverWait) {
        this.driver = driver;
        this.webDriverWait = webDriverWait;
    }

    public GoogleSearchResultsPage searchText(final String inputText) {
        fillGoogleSearchInput(inputText);
        submitSearch();
        return new GoogleSearchResultsPage(driver, webDriverWait);
    }

    private void fillGoogleSearchInput(final String inputText) {
        WebElement googleSearchInput = driver.findElement(inputBox);
        googleSearchInput.click();
        googleSearchInput.sendKeys(inputText + Keys.TAB);
    }

    private void submitSearch() {
        WebElement searchSubmitButton = driver.findElement(submitButton);
        searchSubmitButton.click();
    }
}
