package customerTest;

import java.util.Arrays;
import java.util.List;

import org.ecommerce.customer.ProductSearch;
import org.testng.annotations.Test;

import base.ConfigTest;

public class ProductSearchTest extends ConfigTest{
	
	//@Test
	public void productSearchTest() {
		//String product = "pant";
		//new ProductSearch().productSearch(product);
		
		List<String> products = Arrays.asList("pant", "shirt", "invalid_product_name");

        for (String product : products) {
        	new ProductSearch().productSearch(product);
        }
	}

}
