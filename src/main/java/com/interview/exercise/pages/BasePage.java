package com.interview.exercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait webDriverWait;
    protected final FluentWait<WebDriver> fluentWait;

    public BasePage(final WebDriver driver, final WebDriverWait webDriverWait, final FluentWait fluentWait) {
        this.driver = driver;
        this.webDriverWait = webDriverWait;
        this.fluentWait = fluentWait;
    }
}
