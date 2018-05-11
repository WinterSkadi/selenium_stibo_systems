package com.interview.exercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StiboSystemsHomePage {
    private final WebDriver driver;
    private final WebDriverWait webDriverWait;

    public StiboSystemsHomePage(final WebDriver driver, final WebDriverWait webDriverWait) {
        this.driver = driver;
        this.webDriverWait = webDriverWait;
    }
}
