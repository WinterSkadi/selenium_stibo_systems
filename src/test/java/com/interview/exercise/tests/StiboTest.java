package com.interview.exercise.tests;

import com.interview.exercise.pages.*;
import org.junit.jupiter.api.*;

import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StiboTest extends BaseTest {
    private static final String ROOT_TEST_SITE = "https://www.google.com/";
    private static final String GOOGLE_SEARCH_INPUT = "stibo systems";
    private static final String STIBO_SEARCH_INPUT_TEXT = "energy";
    private static final String INCORRECT_EMAIL_INPUT = "test";
    private static final String CORRECT_EMAIL_INPUT = "jm@gmail.com";
    private static final String LINKED_IN_PAGE_TITLE_PREFIX = "LinkedIn";
    private static final String STIBO_SYSTEMS_PAGE_TITLE_PREFIX = "Master Data Management";

    private GoogleSearchPage googleSearchPage;

    public StiboTest() throws IOException {
    }

    @BeforeAll
    public void setUp() {
        maximizeBrowserWindow();
        driver.get(ROOT_TEST_SITE);
        googleSearchPage = new GoogleSearchPage(driver, webDriverWait, fluentWait);
    }

    @Test
    @DisplayName("when using google search engine navigate to stibo systems website and perform basic and advanced test scenarios")
    public void perform_basic_and_advanced_test_scenarios() {
        //Here starts basic scenario
        GoogleSearchResultsPage googleSearchResultsPage = googleSearchPage.searchText(GOOGLE_SEARCH_INPUT);
        StiboSystemsHomePage stiboSystemsHomePage = googleSearchResultsPage.navigateToStiboSystemsWebsite();

        stiboSystemsHomePage.searchText(STIBO_SEARCH_INPUT_TEXT);
        int searchResultsCount = stiboSystemsHomePage.getSearchResultCount();
        System.out.println("Number of results: " + searchResultsCount);
        stiboSystemsHomePage.navigateToAboutUsResult();
        System.out.println("About Us search page number: " + stiboSystemsHomePage.getAboutUsSearchPageNumber());
        StiboSystemsAboutUsPage stiboSystemsAboutUsPage = stiboSystemsHomePage.goToAboutPage();
        Assertions.assertTrue(stiboSystemsAboutUsPage.existsFacebookIcon(), "Facebook icon exists on About Us page");
        StiboSystemsBlogPage stiboSystemsBlogPage = stiboSystemsAboutUsPage.goToToBlogPage();

        stiboSystemsBlogPage.checkAgreements();
        stiboSystemsBlogPage.fillEmailInputBoxAndSend(INCORRECT_EMAIL_INPUT);
        String invalidEmailFormatMessage = stiboSystemsBlogPage.getIncorrectEmailFormatErrorMessage();
        System.out.println("Validation error message: " + invalidEmailFormatMessage);
        stiboSystemsBlogPage.fillEmailInputBoxAndSend(CORRECT_EMAIL_INPUT);
        String validSubscriptionMessage = stiboSystemsBlogPage.getCorrectEmailFormatErrorMessage();
        Assertions.assertEquals("Thanks for Subscribing!", validSubscriptionMessage, "'Thanks for Subscribing!' message should be shown");

        //Here starts advanced scenario
        LinkedInSignUpPage linkedInSignUpPage = stiboSystemsBlogPage.clickLinkedInIcon();
        switchToAnotherTab(LINKED_IN_PAGE_TITLE_PREFIX);
        Assertions.assertTrue(linkedInSignUpPage.checkIfSignUpFormExists(), "The sign up screen is shown");
        linkedInSignUpPage.joinLinkedIn();
        closeCurrentTab();
        switchToAnotherTab(STIBO_SYSTEMS_PAGE_TITLE_PREFIX);

        stiboSystemsBlogPage.expandAllCategories();
        stiboSystemsBlogPage.clickOnCMDMCategory();
    }

    @AfterAll
    public void clean() {
        driver.quit();
    }
}