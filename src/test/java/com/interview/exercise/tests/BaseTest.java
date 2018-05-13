package com.interview.exercise.tests;

import com.interview.exercise.utils.TestWebDriverPopUpEventListener;
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

    protected final WebDriver driver;
    protected final WebDriverWait webDriverWait;
    protected final FluentWait fluentWait;
    private final Properties properties;

    public BaseTest() throws IOException {
        properties = loadConfigFile();
        driver = createDriver();
        webDriverWait = createWebDriverWait();
        fluentWait = createFluentWait();
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
        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
        eventFiringWebDriver.register(new TestWebDriverPopUpEventListener());
        return eventFiringWebDriver;
    }

    private WebDriverWait createWebDriverWait() {
        return new WebDriverWait(driver, Long.parseLong(properties.getProperty("explicitTimeoutInSeconds")));
    }

    private FluentWait createFluentWait() {
        return new FluentWait(driver)
                .pollingEvery(Duration.ofSeconds(Long.parseLong(properties.getProperty("fluentWaitPoolingInSeconds"))))
                .withTimeout(Duration.ofSeconds(Long.parseLong(properties.getProperty("fluentWaitTimeoutInSeconds"))))
                .ignoring(NoSuchElementException.class);
    }

    protected void switchToAnotherTab(String pageTitlePrefix) {
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
}
