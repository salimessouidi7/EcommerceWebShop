package org.ecommerce.customer;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import org.ecommerce.SetUp;

public class ProductSearch {
	
	static WebDriverWait wait;
	WebDriver driver = SetUp.getWebDriver(SetUp.BrowserType.CHROME);
	
	public void productSearch(String product) {
		
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement prodsearch = driver.findElement(By.name("search"));
			
			prodsearch.sendKeys(product);
			prodsearch.sendKeys(Keys.ENTER);
			
			// Find all product elements
            List<WebElement> productElements = driver.findElements(By.cssSelector(".product-image-wrapper"));

            // If at least one product element is found, the test passes
            if (productElements.size() > 0) {
                System.out.println("Test Passed: Products are available for the search term '" + product + "'");
            } else {
                // If no product element is found, the test fails
                System.out.println("Test Failed: No products found for the search term '" + product + "'");
            }
			
		}catch (Exception e) {
			// Handle exceptions or log the error
            e.printStackTrace();
            System.out.println("Test Failed: Exception occurred while searching for products.");
        }
	}

}
