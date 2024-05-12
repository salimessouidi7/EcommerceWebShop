package adminTest;

import admin.ProductSettings;
import admin.AdminAuthentication;
import org.testng.annotations.Test;

import base.ConfigTest;

public class ProductSettingsTest extends ConfigTest {

    //@Test(priority = 1)
    public void setDiscountTest() {
        new AdminAuthentication(driver).loginAdmin(TestData.USERNAME, TestData.PASSWORD);
        new ProductSettings(driver).setDiscount(TestData.PRODUCT_NAME, TestData.DISCOUNT);
    }

    //@Test(priority = 2)
    public void addNewDeliveryFeeTest() {
        new AdminAuthentication(driver).loginAdmin(TestData.USERNAME, TestData.PASSWORD);
        new ProductSettings(driver).addNewDeliveryFee(TestData.PLACE, TestData.BRGY, TestData.DELIVERY_PRICE);
    }

    //@Test(priority = 3)
    public void editDeliveryFeeTest() {
        String deliveryName = TestData.BRGY + " " + TestData.PLACE;
        new AdminAuthentication(driver).loginAdmin(TestData.USERNAME, TestData.PASSWORD);
        new ProductSettings(driver).editDeliveryFee(TestData.NEW_PLACE, TestData.NEW_BRGY, TestData.NEW_DELIVERY_PRICE, deliveryName);
    }
}

