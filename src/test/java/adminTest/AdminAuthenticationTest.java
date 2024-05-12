package adminTest;

import admin.AdminAuthentication;
import org.testng.annotations.Test;

import base.ConfigTest;

public class AdminAuthenticationTest extends ConfigTest {

    @Test(priority = 1)
    public void loginAndLogoutAdminTest() {
        AdminAuthentication adminAuth = new AdminAuthentication(driver);

        // Login
        adminAuth.loginAdmin(TestData.USERNAME, TestData.PASSWORD);

        // Logout
        adminAuth.logoutAdmin();
        
    }
    @Test(priority = 2)
    public void editProfileTest() {
    	String newUsername = "salimsouidi7@gmail.com";
    	// Log in with admin credentials
        new AdminAuthentication(driver).loginAdmin(TestData.USERNAME, TestData.PASSWORD);
        new AdminAuthentication(driver).editProfile(newUsername, TestData.PASSWORD);
    }
}

