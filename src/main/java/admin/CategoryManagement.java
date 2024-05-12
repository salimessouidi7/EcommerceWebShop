package admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.assertEquals;
import java.time.Duration;

import baseUtils.RefactorMethods;

public class CategoryManagement {
	WebDriver driver;
	WebDriverWait wait;

	public CategoryManagement(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void addNewCategory(String categoryName) {
		try {
			RefactorMethods.clickByCssSelector(driver, "a[href='/ecommerce/admin/category/index.php']");
			wait.until(ExpectedConditions.urlContains("/category/index.php"));

			RefactorMethods.clickByCssSelector(driver, "a[href='index.php?view=add']");
			RefactorMethods.sendKeysById(driver, "CATEGORY", categoryName);
			RefactorMethods.clickByName(driver, "save");

			WebElement addCategory = driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div/label"));
			// Compare the actual alert text with the expected value
			String expectedText = "New [" + categoryName + "] created successfully!";
			assertEquals(addCategory.getText(), expectedText, "Confirmation message mismatch");

			// Verify the new category exists in the list of categories
			RefactorMethods.sendKeysByXpath(driver, "//*[@id=\"dash-table_filter\"]/label/input", categoryName);

			if (countNumberOfCategories(driver) != 0) {
				System.out.println("The new category was added successfully!");
			}

		} catch (Exception e) {
			// Handle exceptions or log the error
			e.printStackTrace();
		}

	}
	
	public void editCategory(String categoryName, String newCategoryName) {
		try {

			RefactorMethods.clickByCssSelector(driver, "a[href='/ecommerce/admin/category/index.php']");
			wait.until(ExpectedConditions.urlContains("/category/index.php"));
			// Find the category to delete
			RefactorMethods.sendKeysByXpath(driver, "//*[@id=\"dash-table_filter\"]/label/input", categoryName);
			
			// Test if the provided category name exist before the edition
			if (countNumberOfCategories(driver) != 0) {
				RefactorMethods.clickByXPath(driver, "//*[@id=\"dash-table\"]/tbody/tr/td[2]/a[1]");
				
				driver.findElement(By.id("CATEGORY")).clear();
				RefactorMethods.sendKeysById(driver, "CATEGORY", newCategoryName);
				RefactorMethods.clickByName(driver, "save");

				WebElement delCategory = driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div/label"));
				// Compare the actual alert text with the expected value
				String expectedText = "[" + newCategoryName + "] has been updated!";
				assertEquals(delCategory.getText(), expectedText, "Confirmation message mismatch");
			} else {
				System.out.println("No matching records found for " + categoryName);
			}

		} catch (Exception e) {
			// Handle exceptions or log the error
			e.printStackTrace();
		}
	}

	public void deleteCategory(String categoryName) {
		try {

			RefactorMethods.clickByCssSelector(driver, "a[href='/ecommerce/admin/category/index.php']");
			wait.until(ExpectedConditions.urlContains("/category/index.php"));
			// Find the category to delete
			RefactorMethods.sendKeysByXpath(driver, "//*[@id=\"dash-table_filter\"]/label/input", categoryName);
			
			// Test if the provided category name exist before the deletion
			if (countNumberOfCategories(driver) != 0) {
				RefactorMethods.clickByXPath(driver, "//*[@id=\"dash-table\"]/tbody/tr/td[2]/a[2]");

				verifyConfirmationMessage("Category already Deleted!");
			} else {
				System.out.println("No matching records found for " + categoryName);
			}

		} catch (Exception e) {
			// Handle exceptions or log the error
			e.printStackTrace();
		}
	}

	private static int countNumberOfCategories(WebDriver driver) {

		WebElement tbodyElement = driver.findElement(By.tagName("tbody"));
		// Find all <tr> elements within the <tbody>
		// Each <tr> represents a category
		// So, the number of <tr> elements represents the number of categories in the
		// list of categories
		return tbodyElement.findElements(By.tagName("tr")).size();
	}
	
	private void verifyConfirmationMessage(String expectedMessage) {
        WebElement messageLabel = driver.findElement(By.xpath("//*[@id='page-wrapper']/div/div/label"));
        assertEquals(messageLabel.getText(), expectedMessage, "Confirmation message mismatch");
    }

}
