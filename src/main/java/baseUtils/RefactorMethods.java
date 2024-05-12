package baseUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RefactorMethods {

    static WebDriverWait wait;

    // Refactored method to perform explicit wait
    public static void waitForElement(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust timeout as needed
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Refactored method to find and click element by id
    public static void clickById(WebDriver driver, String id) {
        driver.findElement(By.id(id)).click();
    }

    // Refactored method to find and click element by name
    public static void clickByName(WebDriver driver, String name) {
        driver.findElement(By.name(name)).click();
    }

    // Refactored method to find and click element by xpath
    public static void clickByXPath(WebDriver driver, String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    // Refactored method to find and click element by cssSelector
    public static void clickByCssSelector(WebDriver driver, String cssSelector) {
        driver.findElement(By.cssSelector(cssSelector)).click();
    }

    // Refactored method to find and click element by classname
    public static void clickByClassname(WebDriver driver, String classname) {
        driver.findElement(By.className(classname)).click();
    }

    // Refactored method to find and send keys to element by id
    public static void sendKeysById(WebDriver driver, String id, String keys) {
        driver.findElement(By.id(id)).sendKeys(keys);
    }

    // Refactored method to find and send keys to element by xpath
    public static void sendKeysByXpath(WebDriver driver, String xpath, String keys) {
        driver.findElement(By.xpath(xpath)).sendKeys(keys);
    }

    // Refactored method to find and send keys to element by name
    public static void sendKeysByName(WebDriver driver, String name, String keys) {
        driver.findElement(By.name(name)).sendKeys(keys);
    }

    public static String getElementText(WebDriver driver, By locator) {
        return driver.findElement(locator).getText();
    }

    // Method to scroll down until element is found
    public static void scrollToElement(WebDriver driver, By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean elementFound = false;

        while (!elementFound) {
            // Check if the element is present on the page
            if (driver.findElements(locator).size() > 0) {
                elementFound = true;
            } else {
                // Scroll down by a certain amount of pixels
                js.executeScript("window.scrollBy(0, 500);");
            }
        }
    }
}
