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

public class ProductManagement {

	WebDriver driver;
    WebDriverWait wait;

    public ProductManagement(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

	public void addNewProduct(String owner, String phone, String description, String category, int original_price, int price, int quantity, String imagePath) {
		try {
			
			RefactorMethods.clickByCssSelector(driver, "a[href='index.php?view=add']");
			
			RefactorMethods.sendKeysById(driver, "OWNERNAME", owner);
			RefactorMethods.sendKeysById(driver, "OWNERPHONE",phone);
			RefactorMethods.sendKeysById(driver, "PRODESC", description);
			
			// Convert the category parameter to uppercase
	        category = category.toUpperCase();

	        // Select the category
	        //RefactorMethods.sendKeysById(driver, "CATEGORY", category );
	        String categoryOptionXPath = String.format("//select[@id='CATEGORY']/option[text()='%s']", category);
	        RefactorMethods.clickByXPath(driver, categoryOptionXPath);
			
			RefactorMethods.sendKeysById(driver, "ORIGINALPRICE",String.valueOf(original_price));
			RefactorMethods.sendKeysById(driver, "PROPRICE",String.valueOf(price));
			RefactorMethods.sendKeysById(driver, "PROQTY",String.valueOf(quantity));
			RefactorMethods.sendKeysById(driver, "image", imagePath);
			
			RefactorMethods.clickByName(driver, "save");
			
			wait.until(ExpectedConditions.urlContains("/products/index.php"));
			
            verifyConfirmationMessage("New Product created successfully!");
			

		} catch (Exception e) {
			// Handle exceptions or log the error
			e.printStackTrace();
		}

	}

	// To be developed
	public void editProduct() {
		try {

		} catch (Exception e) {
			// Handle exceptions or log the error
			e.printStackTrace();
		}
	}

	public void deleteProduct(WebDriver driver) {
		try {
			// it works only using the full xpath
			RefactorMethods.clickByXPath(driver, "/html/body/div/div[2]/div/div/form/div/div[1]/div[2]/div/table/tbody/tr[7]/td[1]/input");
			RefactorMethods.scrollToElement(driver, By.name("delete"));
			RefactorMethods.clickByName(driver, "delete");
			
            verifyConfirmationMessage("Product has been Deleted!");

		} catch (Exception e) {
			// Handle exceptions or log the error
			e.printStackTrace();
		}
	}
	
	private void verifyConfirmationMessage(String expectedMessage) {
        WebElement messageLabel = driver.findElement(By.xpath("//*[@id='page-wrapper']/div/div/label"));
        assertEquals(messageLabel.getText(), expectedMessage, "Confirmation message mismatch");
    }

}
