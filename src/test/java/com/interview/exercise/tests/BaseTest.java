package com.interview.exercise.tests;

import com.interview.exercise.eventlistener.WebDriverPopUpEventListener;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

public abstract class BaseTest {
    private static final String CONFIG_FILE_PATH = "src\\test\\java\\resources\\config.properties";
    private final Properties properties;

    protected final WebDriver driver;
    protected final WebDriverWait webDriverWait;
    protected final FluentWait fluentWait;

    public BaseTest() throws IOException {
        properties = loadConfigFile();
        driver = createDriver();
        webDriverWait = createWebDriverWait();
        fluentWait = createFluentWait(driver,
                Long.parseLong(properties.getProperty("fluentWaitPollingFrequencyInMiliseconds")),
                Long.parseLong(properties.getProperty("fluentWaitTimeoutInMiliseconds")));
    }

    protected void switchToAnotherTab(final String pageTitlePrefix) {
        Set<String> tabs = driver.getWindowHandles();

        for (String tab : tabs) {
            driver.switchTo().window(tab);
            if (driver.getTitle().startsWith(pageTitlePrefix)) {
                return;
            }
        }
    }

    protected void closeCurrentTab() {
        driver.close();
    }

    protected void maximizeBrowserWindow() {
        driver.manage().window().maximize();
    }

    private Properties loadConfigFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH);
        Properties properties = new Properties();
        properties.load(fileInputStream);
        fileInputStream.close();
        return properties;
    }

    private WebDriver createDriver() {
        System.setProperty("webdriver.chrome.driver", properties.getProperty("chromeDriverPath"));
        WebDriver driver = new ChromeDriver();
        EventFiringWebDriver eventFiringWebDriver = createEventFiringWebDriver(driver);

        return eventFiringWebDriver;
    }

    private EventFiringWebDriver createEventFiringWebDriver(WebDriver driver) {
        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);

        final FluentWait fluentWaitPopUp = createFluentWait(driver,
                Long.parseLong(properties.getProperty("fluentWaitPopUpPollingFrequencyInMiliseconds")),
                Long.parseLong(properties.getProperty("fluentWaitPopUpTimeoutInMiliseconds")));

        eventFiringWebDriver.register(
                new WebDriverPopUpEventListener(fluentWaitPopUp));
        return eventFiringWebDriver;
    }

    private WebDriverWait createWebDriverWait() {
        return new WebDriverWait(driver, Long.parseLong(properties.getProperty("explicitTimeoutInSeconds")));
    }

    private FluentWait createFluentWait(WebDriver driver, Long pollingFrequencyMiliseconds, Long timeoutMiliseconds) {
        return new FluentWait(driver)
                .pollingEvery(Duration.ofMillis(pollingFrequencyMiliseconds))
                .withTimeout(Duration.ofMillis(timeoutMiliseconds))
                .ignoring(NoSuchElementException.class);
    }
}
