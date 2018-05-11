package com.interview.exercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchResultsPage {
    private final WebDriver driver;
    private final WebDriverWait webDriverWait;
    private static final By searchResult = By.xpath("//a[text() = 'Stibo Systems - Business-first data management solutions' and @href='https://www.stibosystems.com/']");

    public GoogleSearchResultsPage(final WebDriver driver, final WebDriverWait webDriverWait) {
        this.driver = driver;
        this.webDriverWait = webDriverWait;
    }

    public StiboSystemsHomePage navigateToStiboSystemsWebsite() {
        WebElement googleSearchResult = driver.findElement(searchResult);
        googleSearchResult.click();
        return new StiboSystemsHomePage(driver, webDriverWait);
    }
}