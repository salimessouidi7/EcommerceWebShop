package admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.assertEquals;
import java.time.Duration;

import org.ecommerce.SetUp;
import baseUtils.RefactorMethods;

// This code don't work at this moment due to errors in the code of developer
public class OrdersManagement {
	WebDriver driver;
	WebDriverWait wait;

	public OrdersManagement(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public void confirmOrders() {
		RefactorMethods.clickByCssSelector(driver, "a[href='/ecommerce/admin/orders/index.php']");
		wait.until(ExpectedConditions.urlContains("/orders/index.php"));
		
		RefactorMethods.clickByCssSelector(driver, "a[href='controller.php?action=edit&id=97&customerid=18&actions=confirm']");
	}
}
