package adminTest;

import admin.UserManagement;
import admin.AdminAuthentication;
import org.testng.annotations.Test;

import base.ConfigTest;

public class UserManagementTest extends ConfigTest{
    
    //@Test(priority = 1)
    public void addUserTest() {
        // Log in with admin credentials
        new AdminAuthentication(driver).loginAdmin(TestData.USERNAME, TestData.PASSWORD);
        
        // Add a new user using test data
        new UserManagement(driver).addNewUser(TestData.ACCOUNT_NAME, TestData.EMAIL_ADDRESS, TestData.ACCOUNT_PASSWORD, TestData.ROLE);
    }
    
    //@Test(priority = 2)
    public void editUserTest() {
        // Log in with admin credentials
        new AdminAuthentication(driver).loginAdmin(TestData.USERNAME, TestData.PASSWORD);
        
        // Editing the user using test data
        new UserManagement(driver).editUser(TestData.NEW_ACCOUNT_NAME, TestData.NEW_EMAIL_ADDRESS, TestData.NEW_ACCOUNT_PASSWORD, TestData.NEW_ROLE);
    }
    
    //@Test(priority = 3)
    public void deleteUserTest() {
        // Log in with admin credentials
        new AdminAuthentication(driver).loginAdmin(TestData.USERNAME, TestData.PASSWORD);
        
        // Delete a user
        new UserManagement(driver).deleteUser();
    }
    
}

