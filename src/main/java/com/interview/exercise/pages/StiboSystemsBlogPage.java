package com.interview.exercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StiboSystemsBlogPage {
    WebDriver driver;
    WebDriverWait webDriverWait;

    public StiboSystemsBlogPage(final WebDriver driver, final WebDriverWait webDriverWait) {
        this.driver = driver;
        this.webDriverWait = webDriverWait;
    }
}
