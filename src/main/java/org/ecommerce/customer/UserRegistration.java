package org.ecommerce.customer;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.assertEquals;
import java.time.Duration;

import org.ecommerce.SetUp;
import baseUtils.RefactorMethods;

public class UserRegistration {

    static WebDriverWait wait;
    
    public void registrationCustomer(String first_name, String last_name, String username, String city, String password,
                                     String phone, String gender) {
        WebDriver driver = SetUp.getWebDriver(SetUp.BrowserType.CHROME);

        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
        	
        	// Locate the element using CSS selector based on its data attributes
        	RefactorMethods.clickByCssSelector(driver, "a[data-target='#smyModal'][data-toggle='modal']");
        	
        	wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.btn.btn-default.check_out[href='#profile'][data-toggle='tab']")));
        	RefactorMethods.clickByCssSelector(driver, "a.btn.btn-default.check_out[href='#profile'][data-toggle='tab']");
        	
        	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FNAME")));
        	
        	// Fill in the form fields
        	RefactorMethods.sendKeysById(driver, "FNAME", first_name);
        	RefactorMethods.sendKeysById(driver, "LNAME", last_name);
        	
        	// Construct the CSS selector or XPath dynamically based on the gender value
            String locator = String.format("input[name='GENDER'][value='%s']", gender);

            // Locate the radio button element based on the constructed locator
            RefactorMethods.clickByCssSelector(driver, locator);
            
            RefactorMethods.sendKeysById(driver, "CITYADD", city);
            RefactorMethods.sendKeysById(driver, "CUSUNAME", username);
            RefactorMethods.sendKeysById(driver, "CUSPASS", "amira12345678");
        	RefactorMethods.sendKeysById(driver, "PHONE", phone);
        	
        	// Agree terms and conditions
        	RefactorMethods.clickById(driver, "conditionterms");
        	
        	// Scroll down until the element is found
            RefactorMethods.scrollToElement(driver, By.name("submit"));
            
            RefactorMethods.clickByName(driver, "submit");
            
            // Accepts (Click on OK) Chrome Alert Browser for RESET button.
            Alert alertOK = driver.switchTo().alert();
            
            // Get the text of the alert
            String alertText = alertOK.getText();
            
            alertOK.accept();
            
            // Compare the actual alert text with the expected value
            String expectedAlertText = "You are now successfully registered. It will redirect to your order details.";
            assertEquals(alertText, expectedAlertText, "Confirmation message mismatch");
        	
        } catch (Exception e) {
            // Handle exceptions or log the error
            e.printStackTrace();
        }
    }
} 
