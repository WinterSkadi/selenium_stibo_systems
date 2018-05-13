package com.interview.exercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StiboSystemsAboutUsPage extends BasePage {
    private static final By FACEBOOK_ICON_BUTTON = By.xpath("//a[@class = 'icon-facebook']");
    private static final By BLOG_PAGE_BUTTON = By.xpath("//a[text() = 'Blog']");

    public StiboSystemsAboutUsPage(final WebDriver driver, final WebDriverWait webDriverWait, final FluentWait fluentWait) {
        super(driver, webDriverWait, fluentWait);
    }

    public boolean existsFacebookIcon() {
        return driver.findElements(FACEBOOK_ICON_BUTTON).size() > 0;
    }

    public StiboSystemsBlogPage goToToBlogPage() {
        WebElement blogPage = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(BLOG_PAGE_BUTTON));
        blogPage.click();
        return new StiboSystemsBlogPage(driver, webDriverWait, fluentWait);
    }
}