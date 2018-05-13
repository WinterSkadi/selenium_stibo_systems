package com.interview.exercise.tests;

import com.interview.exercise.pages.*;
import org.junit.jupiter.api.*;

import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StiboTest extends BaseTest {
    private final String searchPage = "https://www.google.com/";
    private final String googleSearchInput = "stibo systems";
    private final String incorrectEmailInput = "test";
    private final String correctEmailInput = "jm@gmail.com";
    private final String linkedInPageTitlePrefix = "LinkedIn";
    private final String stiboSystemsPageTitlePrefix = "Master Data Management";

    private GoogleSearchPage googleSearchPage;

    public StiboTest() throws IOException {
    }

    @BeforeAll
    public void setUp() {
        maximizeBrowserWindow();
        driver.get(searchPage);
        googleSearchPage = new GoogleSearchPage(driver, webDriverWait, fluentWait);
    }

    @Test
    public void when_using_google_search_engine() {
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
}