package com.interview.exercise.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class StiboSystemsHomePage extends BasePage {
    private final String stiboSearchInput = "energy";
    private static final By searchButton = By.className("search");
    private static final By searchInput = By.id("search-input");
    private static final By numberOfResults = By.xpath("//div[@class = 'ais-body ais-stats--body']/div");
    private static final By aboutUsResult = By.xpath("//div[@class = 'ais-hits--item']/a[text() = 'About Us']");
    private static final By nextPage = By.xpath("//li[@class = 'ais-pagination--item ais-pagination--item__next']");
    private static final By currentResultPageNumber = By.xpath("//li[@class='ais-pagination--item ais-pagination--item__page ais-pagination--item__active']/a");

    public StiboSystemsHomePage(final WebDriver driver, final WebDriverWait webDriverWait, final FluentWait fluentWait) {
        super(driver, webDriverWait, fluentWait);
        driver.getWindowHandles();
    }

    public StiboSystemsHomePage searchText() {
        clickSearchIcon();
        searchSpecifiedText();
        return this;
    }

    public StiboSystemsHomePage navigateToAboutUsResult() {
        while (elementExists(aboutUsResult) == false) {
            if (elementExists(nextPage)) {
                driver.findElement(nextPage).click();
            } else {
                break;
            }
        }

        return this;
    }

    public int getAboutUsSearchPageNumber() {
        return Integer.parseInt(webDriverWait.until(ExpectedConditions.elementToBeClickable(currentResultPageNumber)).getText());
    }

    public StiboSystemsAboutUsPage goToAboutPage() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(aboutUsResult)).click();
        return new StiboSystemsAboutUsPage(driver, webDriverWait, fluentWait);
    }

    private boolean elementExists(By selector) {
        try {
            fluentWait.until(ExpectedConditions.elementToBeClickable(selector));
            return true;
        } catch (TimeoutException timeoutException) {
            return false;
        }
    }

    private void clickSearchIcon() {
        WebElement searchIcon = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
        searchIcon.click();
    }

    private void searchSpecifiedText() {
        WebElement searchBox = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]", searchBox, stiboSearchInput.substring(0, stiboSearchInput.length() - 1));
        searchBox.sendKeys(stiboSearchInput.substring(stiboSearchInput.length() - 1));
    }

    public int getSearchResultCount() {
        WebElement searchResultsBox = webDriverWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(numberOfResults)));
        String resultsText = searchResultsBox.getText();
        String resultsCount = resultsText.split(" ")[0];
        return Integer.parseInt(resultsCount);
    }
}
