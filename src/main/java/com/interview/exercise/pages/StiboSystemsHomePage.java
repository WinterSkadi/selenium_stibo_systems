package com.interview.exercise.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class StiboSystemsHomePage extends BasePage {
    private static final By SEARCH_BUTTON = By.className("search");
    private static final By SEARCH_INPUT_BOX = By.id("search-input");
    private static final By NUMBER_OF_SEARCH_RESULTS = By.xpath("//div[@class = 'ais-body ais-stats--body']/div");
    private static final By ABOUT_US_RESULT = By.xpath("//div[@class = 'ais-hits--item']/a[text() = 'About Us']");
    private static final By NEXT_PAGE_BUTTON = By.xpath("//li[@class = 'ais-pagination--item ais-pagination--item__next']");
    private static final By CURRENT_RESULT_PAGE_NUMBER = By.xpath("//li[@class='ais-pagination--item ais-pagination--item__page ais-pagination--item__active']/a");

    public StiboSystemsHomePage(final WebDriver driver, final WebDriverWait webDriverWait, final FluentWait fluentWait) {
        super(driver, webDriverWait, fluentWait);
        driver.getWindowHandles();
    }

    public StiboSystemsHomePage searchText(final String searchText) {
        clickSearchIcon();
        searchSpecifiedText(searchText);
        return this;
    }

    public int getSearchResultCount() {
        WebElement searchResultsBox = webDriverWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(NUMBER_OF_SEARCH_RESULTS)));
        String resultsText = searchResultsBox.getText();
        final String whiteSpace = " ";
        String resultsCount = resultsText.split(whiteSpace)[0];
        return Integer.parseInt(resultsCount);
    }

    public StiboSystemsHomePage navigateToAboutUsResult() {
        while (elementExists(ABOUT_US_RESULT) == false) {
            if (elementExists(NEXT_PAGE_BUTTON)) {
                driver.findElement(NEXT_PAGE_BUTTON).click();
            } else {
                break;
            }
        }

        return this;
    }

    public int getAboutUsSearchPageNumber() {
        return Integer.parseInt(webDriverWait.until(ExpectedConditions.elementToBeClickable(CURRENT_RESULT_PAGE_NUMBER)).getText());
    }

    public StiboSystemsAboutUsPage goToAboutPage() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(ABOUT_US_RESULT)).click();
        return new StiboSystemsAboutUsPage(driver, webDriverWait, fluentWait);
    }

    private boolean elementExists(final By selector) {
        try {
            fluentWait.until(ExpectedConditions.elementToBeClickable(selector));
            return true;
        } catch (TimeoutException timeoutException) {
            return false;
        }
    }

    private void clickSearchIcon() {
        WebElement searchIcon = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_BUTTON));
        searchIcon.click();
    }

    private void searchSpecifiedText(final String searchText) {
        WebElement searchBox = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT_BOX));
        searchBox.sendKeys(searchText);
        waitForSearchResults(searchText);
    }

    private void waitForSearchResults(final String searchText) {
        webDriverWait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                return (Boolean) js.executeScript("return (search.helper.lastResults._state.query == '" + searchText + "');");
            }
        });
    }
}