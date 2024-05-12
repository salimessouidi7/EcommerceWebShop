package adminTest;

import admin.ProductManagement;
import admin.AdminAuthentication;
import org.testng.annotations.Test;

import base.ConfigTest;

public class ProductManagementTest extends ConfigTest {

	// @Test(priority = 1)
	public void addNewProductTest() {
		new AdminAuthentication(driver).loginAdmin(TestData.USERNAME, TestData.PASSWORD);
		new ProductManagement(driver).addNewProduct(TestData.OWNER, TestData.PHONE, TestData.DESCRIPTION,
				TestData.CATEGORY, TestData.ORIGINAL_PRICE, TestData.PRICE, TestData.QUANTITY, TestData.IMAGE_PATH);
	}

	// @Test(priority = 2)
	public void deleteProductTest() {
		new AdminAuthentication(driver).loginAdmin(TestData.USERNAME, TestData.PASSWORD);
		new ProductManagement(driver).deleteProduct(driver);
	}
}
