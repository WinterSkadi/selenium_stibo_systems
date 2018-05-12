package com.interview.exercise.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class StiboSystemsHomePage {
    private final String stiboSearchInput = "energy";
    private static final By searchButton = By.className("search");
    private static final By searchInput = By.id("search-input");
    private static final By numberOfResults = By.xpath("//div[@class = 'ais-body ais-stats--body']/div");
    private static final By aboutUsResult = By.xpath("//div[@class = 'ais-hits--item']/a[text() = 'About Us']");
    private static final By nextPage = By.xpath("//li[@class = 'ais-pagination--item ais-pagination--item__next']");
    private static final By currentResultPageNumber = By.xpath("//li[@class='ais-pagination--item ais-pagination--item__page ais-pagination--item__active']/a");

    private final WebDriver driver;
    private final WebDriverWait webDriverWait;

    public StiboSystemsHomePage(final WebDriver driver, final WebDriverWait webDriverWait) {
        this.driver = driver;
        this.webDriverWait = webDriverWait;
    }

    public StiboSystemsHomePage searchText() throws InterruptedException {
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
        return Integer.parseInt(driver.findElement(currentResultPageNumber).getText());
    }

    public StiboSystemsAboutUsPage goToAboutPage() {
        driver.findElement(aboutUsResult).click();
        return new StiboSystemsAboutUsPage(driver, webDriverWait);
    }

    private boolean elementExists(By selector) {
        return driver.findElements(selector).size() > 0;
    }

    private void clickSearchIcon() {
        WebElement searchIcon = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
        searchIcon.click();
    }

    private void searchSpecifiedText() throws InterruptedException {
        WebElement searchBox = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        searchBox.sendKeys(stiboSearchInput);
        Thread.sleep(2000);
    }

    public int getSearchResultCount() {
        WebElement searchResultsBox = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(numberOfResults));
        String resultsText = searchResultsBox.getText();
        String resultsCount = resultsText.split(" ")[0];
        return Integer.parseInt(resultsCount);
    }
}
