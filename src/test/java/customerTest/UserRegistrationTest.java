package customerTest;

import org.ecommerce.customer.UserRegistration;
import org.testng.annotations.Test;

import base.ConfigTest;

public class UserRegistrationTest extends ConfigTest {

    String first_name = "Amira";
    String last_name = "kh";
    String username = "amirakh7";
    String password = "amira12345678";
    String city = "Tunisia";
    String phone = "+21612398702";
    String gender = "Female";

    //@Test
    public void registrationTest() {
        UserRegistration signup = new UserRegistration();
        signup.registrationCustomer(first_name, last_name, username, password, city, phone, gender);
    }
}
