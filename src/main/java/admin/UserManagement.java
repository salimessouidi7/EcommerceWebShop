package admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.assertEquals;
import java.time.Duration;
import org.openqa.selenium.support.ui.Select;

import baseUtils.RefactorMethods;

public class UserManagement {

	WebDriver driver;
	WebDriverWait wait;

	public UserManagement(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void addNewUser(String accountName, String emailAddress, String accountPassword, String role) {
		try {
			RefactorMethods.clickByCssSelector(driver, "a[href='/ecommerce/admin/user/index.php']");
			wait.until(ExpectedConditions.urlContains("/user/index.php"));
			RefactorMethods.clickByCssSelector(driver, "a[href='index.php?view=add']");

			RefactorMethods.sendKeysById(driver, "U_NAME", accountName);
			RefactorMethods.sendKeysById(driver, "U_USERNAME", emailAddress);
			RefactorMethods.sendKeysById(driver, "U_PASS", accountPassword);

			// Locate the select element by its ID
			WebElement selectElement = driver.findElement(By.id("U_ROLE"));

			// Create a Select object
			Select select = new Select(selectElement);

			// Select the option with the value "Administrator"
			select.selectByValue(role);

			RefactorMethods.clickByName(driver, "save");

			WebElement addUserElem = driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div/label"));
			// Compare the actual alert text with the expected value
			String expectedText = "New [" + accountName + "] created successfully!";
			assertEquals(addUserElem.getText(), expectedText, "Confirmation message mismatch");

		} catch (Exception e) {
			// Handle exceptions or log the error
			e.printStackTrace();
		}
	}

	public void editUser(String accountName, String emailAddress, String accountPassword, String role) {
		try {
			RefactorMethods.clickByCssSelector(driver, "a[href='/ecommerce/admin/user/index.php']");
			wait.until(ExpectedConditions.urlContains("/user/index.php"));

			RefactorMethods.clickByXPath(driver, "//*[@id=\"dash-table\"]/tbody/tr[4]/td[4]/a[1]");
			driver.findElement(By.id("U_NAME")).clear();
			RefactorMethods.sendKeysById(driver, "U_NAME", accountName);
			driver.findElement(By.id("U_USERNAME")).clear();
			RefactorMethods.sendKeysById(driver, "U_USERNAME", emailAddress);
			driver.findElement(By.id("U_PASS")).clear();
			RefactorMethods.sendKeysById(driver, "U_PASS", accountPassword);

			// Locate the select element by its ID
			WebElement selectElement = driver.findElement(By.id("U_ROLE"));

			// Create a Select object
			Select select = new Select(selectElement);

			// Select the option with the value "Administrator"
			select.selectByValue(role);

			RefactorMethods.clickByName(driver, "save");

			WebElement editUserElem = driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div/label"));
			// Compare the actual alert text with the expected value
			String expectedText = "[" + accountName + "] has been updated!";
			assertEquals(editUserElem.getText(), expectedText, "Confirmation message mismatch");

		} catch (Exception e) {
			// Handle exceptions or log the error
			e.printStackTrace();
		}
	}

	public void deleteUser() {
		try {
			RefactorMethods.clickByCssSelector(driver, "a[href='/ecommerce/admin/user/index.php']");
			wait.until(ExpectedConditions.urlContains("/user/index.php"));

			RefactorMethods.clickByXPath(driver, "//*[@id=\"dash-table\"]/tbody/tr[4]/td[4]/a[2]/span");

			WebElement delUserElem = driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div/label"));
			// Compare the actual alert text with the expected value
			String expectedText = "User already Deleted!";
			assertEquals(delUserElem.getText(), expectedText, "Confirmation message mismatch");

		} catch (Exception e) {
			// Handle exceptions or log the error
			e.printStackTrace();
		}
	}

}
