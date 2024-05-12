package org.ecommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class SetUp {

    protected static WebDriver driver;

    public enum BrowserType {
        CHROME,
        EDGE
        // Add more browser types if needed
    }

    public static WebDriver getWebDriver(BrowserType browserType) {
        if (driver == null) {
            initializeWebDriver(browserType);
        }
        return driver;
    }

    // Method to initialize WebDriver with a custom URL
    public static WebDriver getWebDriver(BrowserType browserType, String url) {
        if (driver == null) {
            initializeWebDriver(browserType, url);
        }
        return driver;
    }

    private static void initializeWebDriver(BrowserType browserType) {
        initializeWebDriver(browserType, "http://localhost/ecommerce/");
    }

    private static void initializeWebDriver(BrowserType browserType, String url) {
        switch (browserType) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            // Add more cases for other browsers if needed
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
        driver.manage().window().maximize();
        driver.get(url); // Use the provided URL
    }

    public static void closeWebDriver() {
        if (driver != null) {
            driver.quit(); // Use quit() instead of close() to close all browser windows and end the WebDriver session.
            driver = null; // Set driver to null to indicate that the WebDriver session has ended.
        }
    }
}
