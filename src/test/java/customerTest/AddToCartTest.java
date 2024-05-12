package customerTest;

import org.ecommerce.customer.UserAuthentication;
import org.ecommerce.customer.AddToCart;
import org.testng.annotations.Test;

import base.ConfigTest;

public class AddToCartTest extends ConfigTest{
	
	String username = "amirakh7";
	String password = "amira12345678";
	
	//@Test(priority = 1)
	public void loginTest() {
		new UserAuthentication().logIn(username, password);
	}
	
	//@Test(priority = 2)
	public void addProductToCartTest() {
		new UserAuthentication().logIn(username, password);
		new AddToCart(driver).addProductToCart();
	}

	//@Test(priority = 3)
	public void addNewOrderToCartTest() {
		addProductToCartTest();
		new AddToCart(driver).addNewOrderToCart(driver);
	}

	//@Test(priority = 4)
	public void addSameOrderToCartTest() {
		addProductToCartTest();
		new AddToCart(driver).addSameOrderToCart(driver);
	}

	//@Test(priority = 5)
	public void adjustQuantityAndVerifyPriceTest() {
		addSameOrderToCartTest();
		new AddToCart(driver).adjustQuantityAndVerifyPrice(driver, 2);
	}

	//@Test(priority = 6)
	public void removeProductFromCartTest() {
		adjustQuantityAndVerifyPriceTest();
		new AddToCart(driver).removeProductFromCart(driver, 1);
	}
	
	//@Test(priority = 7)
	public void proceedToCheckoutTest() {
		adjustQuantityAndVerifyPriceTest();
		new AddToCart(driver).proceedToCheckout();
	}
}

