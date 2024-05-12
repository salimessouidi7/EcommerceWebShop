package admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import baseUtils.RefactorMethods;

public class AdminAuthentication {
    private static WebDriverWait wait;
    private WebDriver driver;

    public AdminAuthentication(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void loginAdmin(String username, String password) {
        RefactorMethods.sendKeysByName(driver, "user_email", username);
        RefactorMethods.sendKeysByName(driver, "user_pass", password);
        RefactorMethods.clickByName(driver, "btnLogin");

        // Wait until URL contains "/admin/products/"
        wait.until(ExpectedConditions.urlContains("/admin/products/"));
    }
    
    public void logoutAdmin() {
        RefactorMethods.clickByXPath(driver, "//*[@id='wrapper']/nav/ul/li[2]/a");
        RefactorMethods.clickByCssSelector(driver, "a[href='/ecommerce/admin/logout.php']");
    }
    
    public void editProfile(String username, String password) {
        RefactorMethods.clickByXPath(driver, "//*[@id='wrapper']/nav/ul/li[2]/a");
        driver.findElement(By.linkText("Edit My Profile")).click();

        // Update username
        WebElement usernameElement = driver.findElement(By.id("U_USERNAME"));
        usernameElement.clear();
        usernameElement.sendKeys(username);

        // Save changes
        RefactorMethods.clickByName(driver, "save");

        // Wait until URL contains "/user/index.php"
        wait.until(ExpectedConditions.urlContains("/user/index.php"));
    }
}
