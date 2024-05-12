package org.ecommerce.customer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.assertEquals;
import java.time.Duration;

import org.ecommerce.SetUp;
import baseUtils.RefactorMethods;

public class UserAuthentication {
	static WebDriverWait wait;
	WebDriver driver = SetUp.getWebDriver(SetUp.BrowserType.CHROME);
	
	public void logIn(String username, String password) {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
        	
        	// Locate the element using CSS selector based on its data attributes
        	RefactorMethods.clickByCssSelector(driver, "a[data-target='#smyModal'][data-toggle='modal']");
        	wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.btn.btn-default.check_out[href='#home'][data-toggle='tab']")));
        	
        	RefactorMethods.sendKeysById(driver, "U_USERNAME", username);
        	RefactorMethods.sendKeysById(driver, "U_PASS", password);
        	RefactorMethods.clickById(driver, "modalLogin");
        	
        	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cart_items\"]/div/div[2]/div[1]/div[2]")));
        	String homePageAccount = driver.findElement(By.xpath("//*[@id=\"cart_items\"]/div/div[2]/div[1]/div[2]")).getText();
        	
        	// Compare the actual alert text with the expected value
            String expectedMsgText = "Amira kh";
            assertEquals(homePageAccount, expectedMsgText, "Confirmation message mismatch");
        	
		}catch (Exception e) {
            // Handle exceptions or log the error
            e.printStackTrace();
        }
	}
	
	public void logOut() {
		RefactorMethods.clickByCssSelector(driver, "a[href='/ecommerce/logout.php']");
	}

}
