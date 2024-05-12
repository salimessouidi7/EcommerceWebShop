package org.ecommerce.customer;

import org.ecommerce.SetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import baseUtils.RefactorMethods;

public class CheckoutProcess {
	
	static WebDriverWait wait;
	WebDriver driver = SetUp.getWebDriver(SetUp.BrowserType.CHROME);
	
	public void proceedToCheckout() {
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		RefactorMethods.scrollToElement(driver, By.id("proceed"));
		
		RefactorMethods.clickById(driver, "proceed");
		// Wait for a page to load
		wait.until(ExpectedConditions.urlContains("/index.php?q=orderdetails"));
		
		RefactorMethods.scrollToElement(driver, By.id("PLACE"));
		// Find the <select> element by its id
        WebElement selectElement = driver.findElement(By.id("PLACE"));
        
        // Create a Select object
        Select select = new Select(selectElement);
        
        // Select the first option
        select.selectByIndex(1); // Index 0 is for the first option, but since "Select" is there, we'll select the next option.
        
        // Click submit button
        RefactorMethods.clickById(driver, "btn");
        
        wait.until(ExpectedConditions.urlContains("/index.php?q=profile"));
        
        WebElement orderList = driver.findElement(By.xpath("//*[@id=\"home\"]/label"));
      
        // Compare the actual alert text with the expected value
        String expectedText = "Order created successfully!";
        assertEquals(orderList.getText(), expectedText, "Message mismatch");

	}

}
