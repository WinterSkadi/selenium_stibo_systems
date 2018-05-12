package com.interview.exercise.tests;

import com.interview.exercise.pages.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StiboTest {
    private final String searchPage = "https://www.google.com/";
    private final String googleSearchInput = "stibo systems";
    private static String incorrectEmailInput = "test";
    private static String correctEmailInput = "bla@q.com";
    private int searchResultsCount;
    private String invalidEmailFormatMessage;

    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private FluentWait fluentWait;

    private GoogleSearchPage googleSearchPage;

    @BeforeAll
    public void setUp() {
        driver = createDriver();
        webDriverWait = createWebDriverWait();
        fluentWait = createFluentWait();
        driver.manage().window().maximize();
        driver.get(searchPage);
        googleSearchPage = new GoogleSearchPage(driver, webDriverWait, fluentWait);
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
        stiboSystemsBlogPage.fillEmailInputBoxAndSend(incorrectEmailInput);
        invalidEmailFormatMessage = stiboSystemsBlogPage.getIncorrectEmailFormatErrorMessage();
        System.out.println(invalidEmailFormatMessage);
        stiboSystemsBlogPage.fillEmailInputBoxAndSend(correctEmailInput);

        String validSubscriptionMessage = stiboSystemsBlogPage.getCorrectEmailFormatErrorMessage();
        Assertions.assertEquals("Thanks for Subscribing!",validSubscriptionMessage);
    }

    @AfterAll
    public void clean() {
        //driver.close();
    }

    private WebDriver createDriver() {
        System.setProperty("webdriver.chrome.driver", "src\\\\test\\\\java\\\\resources\\\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait((20), TimeUnit.SECONDS);
        return driver;
    }

    private WebDriverWait createWebDriverWait() {
        return new WebDriverWait(driver, (10));
    }

    private FluentWait createFluentWait() {
        return new FluentWait(driver)
                .pollingEvery(Duration.ofSeconds(1))
                .withTimeout(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
    }
}


