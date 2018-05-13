package com.interview.exercise.tests;

import com.interview.exercise.pages.*;
import com.interview.exercise.utils.TestWebDriverEventListener;
import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StiboTest {
    private final String searchPage = "https://www.google.com/";
    private final String googleSearchInput = "stibo systems";
    private final String incorrectEmailInput = "test";
    private final String correctEmailInput = "bla@q.com";
    private final String linkedInPageTitlePrefix = "LinkedIn";
    private final String stiboSystemsPageTitlePrefix = "Master Data Management";

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
        int searchResultsCount = stiboSystemsHomePage.getSearchResultCount();
        System.out.println(searchResultsCount);
        stiboSystemsHomePage.navigateToAboutUsResult();
        System.out.println(stiboSystemsHomePage.getAboutUsSearchPageNumber());
        StiboSystemsAboutUsPage stiboSystemsAboutUsPage = stiboSystemsHomePage.goToAboutPage();

        Assertions.assertTrue(stiboSystemsAboutUsPage.existsFacebookIcon(), "facebook icon exists on About Us page");

        StiboSystemsBlogPage stiboSystemsBlogPage = stiboSystemsAboutUsPage.goToToBlogPage();
        stiboSystemsBlogPage.fillEmailInputBoxAndSend(incorrectEmailInput);
        String invalidEmailFormatMessage = stiboSystemsBlogPage.getIncorrectEmailFormatErrorMessage();
        System.out.println(invalidEmailFormatMessage);
        stiboSystemsBlogPage.fillEmailInputBoxAndSend(correctEmailInput);

        String validSubscriptionMessage = stiboSystemsBlogPage.getCorrectEmailFormatErrorMessage();
        Assertions.assertEquals("Thanks for Subscribing!", validSubscriptionMessage);
        //Here starts advanced scenario
        LinkedInSignUpPage linkedInSignUpPage = stiboSystemsBlogPage.clickLinkedInIcon();
        switchToAnotherTab(linkedInPageTitlePrefix);
        Assertions.assertTrue(linkedInSignUpPage.checkIfSignUpFormExists(), "The sign up screen is shown");

        linkedInSignUpPage.linkedInPage();
        closeCurrentTab();
        switchToAnotherTab(stiboSystemsPageTitlePrefix);
        stiboSystemsBlogPage.expandAllCategoriesListAndClickOnCategory();
    }

    @AfterAll
    public void clean() {
        closeCurrentTab();
    }

    private WebDriver createDriver() {
        System.setProperty("webdriver.chrome.driver", "src\\\\test\\\\java\\\\resources\\\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait((20), TimeUnit.SECONDS);
        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
        eventFiringWebDriver.register(new TestWebDriverEventListener());
        return eventFiringWebDriver;
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

    private void switchToAnotherTab(String pageTitlePrefix) {
        Set<String> tabs = driver.getWindowHandles();

        for (String tab : tabs) {
            driver.switchTo().window(tab);
            if (driver.getTitle().startsWith(pageTitlePrefix)) {
                return;
            }
        }
    }

    private void closeCurrentTab() {
        driver.close();
    }
}