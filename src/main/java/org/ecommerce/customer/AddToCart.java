package org.ecommerce.customer;

import org.ecommerce.SetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import baseUtils.RefactorMethods;

public class AddToCart {
	static WebDriverWait wait;
    WebDriver driver;

    public AddToCart(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

	public void addProductToCart() {

		try {
			
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
			RefactorMethods.clickByXPath(driver, "//a[img[@src='images/home/logo.png']]");
			// Wait for a page to load
            wait.until(ExpectedConditions.urlContains("/ecommerce/"));
			
			//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".product-image-wrapper")));
			
            RefactorMethods.scrollToElement(driver, By.cssSelector(".add-to-cart"));
            
			// Find all product elements
			List<WebElement> productElements = driver.findElements(By.cssSelector(".product-image-wrapper"));
			WebElement addToCartButton = productElements.get(0).findElement(By.cssSelector(".add-to-cart"));
			addToCartButton.click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cart_items\"]/div/div[2]/label")));
			WebElement addItemText = driver.findElement(By.xpath("//*[@id=\"cart_items\"]/div/div[2]/label"));
			
			// Compare the actual alert text with the expected value
            String expectedText = "1 Item added in the cart.";
            assertEquals(addItemText.getText(), expectedText, "Confirmation message mismatch");

			
		} catch (Exception e) {
			// Handle exceptions or log the error
			e.printStackTrace();
		}
	}
	
	public void addNewOrderToCart(WebDriver driver) {
		returnToProductsPage(driver);
        
        // Find all product elements
     	List<WebElement> AddNewOrder = driver.findElements(By.cssSelector(".product-image-wrapper"));
     	
     	WebElement addToCartBtn = AddNewOrder.get(1).findElement(By.cssSelector(".add-to-cart"));
     	addToCartBtn.click();
        
     	int productsAddedToCart = countProductsAddedToCart(driver);
     	System.out.println("Number of products added to the cart: " + productsAddedToCart);
	}
	
	public void addSameOrderToCart(WebDriver driver) {
		returnToProductsPage(driver);
        
        // Find all product elements
     	List<WebElement> AddNewOrder = driver.findElements(By.cssSelector(".product-image-wrapper"));
     	
     	// Try to add the same product to the cart
     	WebElement addSameProduct = AddNewOrder.get(0).findElement(By.cssSelector(".add-to-cart"));
     	addSameProduct.click();
     	WebElement messageElement = driver.findElement(By.cssSelector("label.alert-danger"));
        
        // Extract the text of the message
        String errorMessage = messageElement.getText();
        
        // Verify if the error message is displayed
        assertEquals(errorMessage, "Item is already in the cart.");
	}
	
	public void adjustQuantityAndVerifyPrice(WebDriver driver, int newQuantity) {
        // Find the first <tr> element in the tbody
        WebElement firstRow = driver.findElement(By.xpath("//tbody//tr[1]"));
        
        // Find the input element where you can adjust the quantity by its data-id
        WebElement quantityInput = firstRow.findElement(By.cssSelector("input.QTY"));
        
        // Get the product ID from the quantity input element's data-id attribute
        String productId = quantityInput.getAttribute("data-id");
        
        // Clear the current value and set the new quantity
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(newQuantity));
        
        // Find the output element where the updated price is displayed
        WebElement updatedPriceElement = firstRow.findElement(By.cssSelector("output"));
        // Get the text of the updated price
        String updatedPriceText = updatedPriceElement.getText();

        System.out.println("Updated price for product " + productId + ": " + updatedPriceText);
    }
	
	public void removeProductFromCart(WebDriver driver, int productIndex) {
        // Find the row corresponding to the specified product index
        WebElement productRow = driver.findElement(By.xpath("//tbody//tr[" + productIndex + "]"));
        
        // Find the remove button in the product row and click it
        WebElement removeButton = productRow.findElement(By.cssSelector("a.btn-danger"));
        removeButton.click();
        
        WebElement removeItemText = driver.findElement(By.xpath("//*[@id=\"cart_items\"]/div/div[2]/label"));
        // Compare the actual alert text with the expected value
        String expectedText = "1 item has been removed in the cart.";
        assertEquals(removeItemText.getText(), expectedText, "Message mismatch");
      
    }
	
	public void proceedToCheckout() {
		RefactorMethods.scrollToElement(driver, By.id("proceed"));
		
		RefactorMethods.clickById(driver, "proceed");
		// Wait for a page to load
		wait.until(ExpectedConditions.urlContains("/index.php?q=orderdetails"));
		
		int numberOfProducts = countProductsInOrderSummary(driver);
		System.out.println("Number of products in the order summary: " + numberOfProducts);

		
	}
	
	public static int countProductsInOrderSummary(WebDriver driver) {

        WebElement tbodyElement = driver.findElement(By.tagName("tbody"));
        // Find all <tr> elements within the <tbody>
        // Each <tr> represents a product in the order summary
        // So, the number of <tr> elements represents the number of products in the order summary
        return tbodyElement.findElements(By.tagName("tr")).size();
    }
	
	public static int countProductsAddedToCart(WebDriver driver) {
        int count = 0;
        // Find all rows in the tbody element
        List<WebElement> rows = driver.findElements(By.xpath("//tbody//tr"));

        // Iterate through each row
        for (WebElement row : rows) {
            // Check if the "Add to wishlist" link is present in the row
            List<WebElement> addToWishlistLinks = row.findElements(By.xpath(".//a[contains(@title, 'Add to wishlist')]"));
            // If the link is present, increment the count
            if (!addToWishlistLinks.isEmpty()) {
                count++;
            }
        }
        return count;
    }
	
	public static void returnToProductsPage(WebDriver driver) {
		RefactorMethods.scrollToElement(driver, By.cssSelector("a.btn.btn-default.check_out.pull-left"));
        
        // Repeat the process to add multiple products to the shopping cart
        RefactorMethods.clickByCssSelector(driver, "a.btn.btn-default.check_out.pull-left");
        // Wait for a page to load
        wait.until(ExpectedConditions.urlContains("/index.php?q=product"));
        
        RefactorMethods.scrollToElement(driver, By.cssSelector(".add-to-cart"));
	}
	
}
