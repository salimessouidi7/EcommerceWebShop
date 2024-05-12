package adminTest;

import admin.OrdersManagement;
import admin.AdminAuthentication;
import org.testng.annotations.Test;

import base.ConfigTest;

public class OrdersManagementTest extends ConfigTest{
	
	//@Test
	public void confirmOrdersTest() {
		new AdminAuthentication(driver).loginAdmin(TestData.USERNAME, TestData.PASSWORD);
		new OrdersManagement(driver).confirmOrders();
	}

}
