package com.interview.exercise.tests;

import com.interview.exercise.pages.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StiboTest {
    private final String searchPage = "https://www.google.com/";
    private final String googleSearchInput = "stibo systems";
    private int searchResultsCount;

    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private GoogleSearchPage googleSearchPage;

    @BeforeAll
    public void setUp() {
        driver = createDriver();
        driver.get(searchPage);
        webDriverWait = new WebDriverWait(driver, (20));
        googleSearchPage = new GoogleSearchPage(driver, webDriverWait);
    }

    @Test
    public void when_using_google_search_engine() throws InterruptedException {
        GoogleSearchResultsPage googleSearchResultsPage = googleSearchPage.searchText(googleSearchInput);
        StiboSystemsHomePage stiboSystemsHomePage = googleSearchResultsPage.navigateToStiboSystemsWebsite();
        stiboSystemsHomePage.searchText();
        searchResultsCount = stiboSystemsHomePage.getSearchResultCount();
        System.out.println(searchResultsCount);
        stiboSystemsHomePage.navigateToAboutUsResult();
        System.out.println(stiboSystemsHomePage.getAboutUsSearchPageNumber());
        StiboSystemsAboutUsPage stiboSystemsAboutUsPage = stiboSystemsHomePage.goToAboutPage();

        Assertions.assertTrue(stiboSystemsAboutUsPage.existsFacebookIcon(), "facebook icon exists on About Us page");

        StiboSystemsBlogPage stiboSystemsBlogPage = stiboSystemsAboutUsPage.goToToBlogPage();


    }

    @AfterAll
    public void clean() {
        //driver.close();
    }

    private WebDriver createDriver() {
        System.setProperty("webdriver.chrome.driver", "src\\\\test\\\\java\\\\resources\\\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait((20), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}


