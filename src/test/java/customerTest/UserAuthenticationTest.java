package customerTest;

import org.ecommerce.customer.UserAuthentication;
import org.testng.annotations.Test;

import base.ConfigTest;

public class UserAuthenticationTest extends ConfigTest{
	
	String username = "amirakh7";
	String password = "amira12345678";
	
	//@Test(priority = 1)
	public void loginTest() {
		new UserAuthentication().logIn(username, password);
	}
	
	//@Test(priority = 2)
	public void logoutTest() {
		//authenticate.logIn(username, password);
		new UserAuthentication().logOut();
		
	}

}
