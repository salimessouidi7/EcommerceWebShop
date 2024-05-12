package adminTest;

import admin.CategoryManagement;
import admin.AdminAuthentication;
import org.testng.annotations.Test;

import base.ConfigTest;

public class CategoryManagementTest extends ConfigTest{
    
    //@Test(priority = 1)
    public void addCategoryTest() {
        new AdminAuthentication(driver).loginAdmin(TestData.USERNAME, TestData.PASSWORD);
        new CategoryManagement(driver).addNewCategory(TestData.CATEGORY_NAME);
    }
    
    //@Test(priority = 2)
    public void editCategoryTest() {
        new AdminAuthentication(driver).loginAdmin(TestData.USERNAME, TestData.PASSWORD);
        new CategoryManagement(driver).editCategory(TestData.CATEGORY_NAME, TestData.NEW_CATEGORY_NAME);
    }
    
    //@Test(priority = 3)
    public void deleteCategoryTest() {
        new AdminAuthentication(driver).loginAdmin(TestData.USERNAME, TestData.PASSWORD);
        new CategoryManagement(driver).deleteCategory(TestData.NEW_CATEGORY_NAME);
    }
}
