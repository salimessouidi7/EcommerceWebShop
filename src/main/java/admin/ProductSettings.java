package admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.assertEquals;
import java.time.Duration;
import java.util.List;

import baseUtils.RefactorMethods;

public class ProductSettings {

	WebDriver driver;
	WebDriverWait wait;

	public ProductSettings(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void setDiscount(String productName, int discount) {
		try {
			RefactorMethods.clickByCssSelector(driver, "a[href='/ecommerce/admin/settings/index.php']");
			wait.until(ExpectedConditions.urlContains("/settings/index.php"));
			RefactorMethods.sendKeysByXpath(driver, "//*[@id=\"dash-table_filter\"]/label/input", productName);

			// Test if the provided product name exist before to set the discount
			WebElement tbodyElement = driver.findElement(By.tagName("tbody"));
			int numberOfProduct = tbodyElement.findElements(By.tagName("tr")).size();
			if (numberOfProduct != 0) {
				RefactorMethods.clickByXPath(driver, "//*[@id=\"dash-table\"]/tbody/tr/td[4]/a[2]");

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PRODISCOUNT")));
				RefactorMethods.sendKeysById(driver, "PRODISCOUNT", String.valueOf(discount));
				RefactorMethods.clickByName(driver, "submit");

				// System.out.println("The discount price for the product "+ productName + " is
				// "+discountedPrice);
			}
		} catch (Exception e) {
			// Handle exceptions or log the error
			e.printStackTrace();
		}

	}

	public void addNewDeliveryFee(String place, String brgy, String delPrice) {
		try {

			// Go to Delivery Fee location
			setDeliveryFeeCommons();

			RefactorMethods.clickByCssSelector(driver, "a[href='index.php?view=add']");

			RefactorMethods.sendKeysById(driver, "PLACE", place);
			RefactorMethods.sendKeysById(driver, "BRGY", brgy);
			RefactorMethods.sendKeysById(driver, "DELPRICE", delPrice);
			RefactorMethods.clickByName(driver, "save");

			WebElement addDeliveryElem = driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div/label"));
			// Compare the actual alert text with the expected value
			String expectedText = "New location created successfully!";
			assertEquals(addDeliveryElem.getText(), expectedText, "Confirmation message mismatch");

		} catch (Exception e) {
			// Handle exceptions or log the error
			e.printStackTrace();
		}
	}

	public void editDeliveryFee(String place, String brgy, String delPrice, String deliveryName) {
		try {

			// Go to Delivery Fee location
			setDeliveryFeeCommons();

			// Find all rows in the tbody element
			List<WebElement> rows = driver.findElements(By.xpath("//tbody//tr"));
			for (WebElement row : rows) {
				// Find the first cell of the row containing the delivery name
				WebElement nameCell = row.findElement(By.xpath(".//td[1]")); // Adjusted XPath
				// The first <td> contains the delivery name
				String name = nameCell.getText().trim();
				if (!name.isEmpty()) { // Check if the name is not empty

					if (name.equals(deliveryName.trim())) { // Trim the name for comparison
						// If the delivery name matches, locate the 'Edit' link in the third cell and
						// click it
						WebElement editLink = row.findElement(By.xpath(".//td[3]/a"));
						editLink.click();
						break; // Exit loop after clicking the edit link
					}
				}
			}

			// Clear fields before sendkeys
			driver.findElement(By.id("PLACE")).clear();
			RefactorMethods.sendKeysById(driver, "PLACE", place);

			driver.findElement(By.id("BRGY")).clear();
			RefactorMethods.sendKeysById(driver, "BRGY", brgy);

			driver.findElement(By.id("DELPRICE")).clear();
			RefactorMethods.sendKeysById(driver, "DELPRICE", delPrice);

			RefactorMethods.clickByName(driver, "save");

			WebElement editDeliveryElem = driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div/label"));
			// Compare the actual alert text with the expected value
			String expectedText = "Location has been updated!";
			assertEquals(editDeliveryElem.getText(), expectedText, "Confirmation message mismatch");

		} catch (Exception e) {
			// Handle exceptions or log the error
			e.printStackTrace();
		}
	}

	private void setDeliveryFeeCommons() {
		RefactorMethods.clickByCssSelector(driver, "a[href='/ecommerce/admin/settings/index.php']");
		wait.until(ExpectedConditions.urlContains("/settings/index.php"));

		RefactorMethods.scrollToElement(driver, By.cssSelector("a[href='#collapseTwo']"));
		RefactorMethods.clickByCssSelector(driver, "a[href='#collapseTwo']");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='index.php?view=add']")));
	}

}
