package com.interview.exercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchResultsPage extends BasePage {
    private static final By SEARCH_RESULT_LINK = By.xpath("//a[text() = 'Stibo Systems - Business-first data management solutions' and @href='https://www.stibosystems.com/']");

    public GoogleSearchResultsPage(final WebDriver driver, final WebDriverWait webDriverWait, final FluentWait fluentWait) {
        super(driver, webDriverWait, fluentWait);
    }

    public StiboSystemsHomePage navigateToStiboSystemsWebsite() {
        WebElement googleSearchResult = webDriverWait.until(ExpectedConditions.elementToBeClickable(SEARCH_RESULT_LINK));
        googleSearchResult.click();
        return new StiboSystemsHomePage(driver, webDriverWait, fluentWait);
    }
}