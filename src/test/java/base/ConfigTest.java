package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import org.ecommerce.SetUp;

public class ConfigTest {

    protected static WebDriver driver;

    @BeforeMethod
    @Parameters({"browserType", "url"})
    public void setUp(ITestContext context, SetUp.BrowserType browserType, String url) {
        driver = SetUp.getWebDriver(browserType, url);
    }

    @AfterMethod
    public void tearDown() {
        SetUp.closeWebDriver();
    }
}
