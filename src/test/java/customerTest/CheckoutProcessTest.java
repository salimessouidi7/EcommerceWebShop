package customerTest;

import org.ecommerce.customer.UserAuthentication;
import org.ecommerce.customer.AddToCart;
import org.ecommerce.customer.CheckoutProcess;
import org.testng.annotations.Test;

import base.ConfigTest;

public class CheckoutProcessTest extends ConfigTest{

	String username = "amirakh7";
	String password = "amira12345678";

	//@Test
	public void checkoutProcessTest() {
		new UserAuthentication().logIn(username, password);
		new AddToCart(driver).addProductToCart();
		new CheckoutProcess().proceedToCheckout();

	}
}
