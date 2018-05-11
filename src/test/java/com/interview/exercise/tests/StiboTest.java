package com.interview.exercise.tests;

import com.interview.exercise.pages.GoogleSearchPage;
import com.interview.exercise.pages.GoogleSearchResultsPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StiboTest {
    private final String searchPage = "https://www.google.com/";
    private final String googleSearchInput = "stibo systems";
    private final String stiboSearchInput = "energy";

    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private GoogleSearchPage googleSearchPage;

    @BeforeAll
    public void setUp() {
        driver = createDriver();
        driver.get(searchPage);
        googleSearchPage = new GoogleSearchPage(driver, webDriverWait);
    }

    @Test
    public void when_using_google_search_engine() {
        GoogleSearchResultsPage googleSearchResultsPage = googleSearchPage.searchText(googleSearchInput);
        googleSearchResultsPage.navigateToStiboSystemsWebsite();
    }

    @AfterAll
    public void clean() {
        driver.close();
    }

    private WebDriver createDriver() {
        System.setProperty("webdriver.chrome.driver", "src\\\\test\\\\java\\\\resources\\\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait((20), TimeUnit.SECONDS);
        return driver;
    }
}


