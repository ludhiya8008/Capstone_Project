package StepDef;


import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import tests.ProductPage2;
import utils.ExtentReport;
import utils.ReportManager;



public class checkout {
    WebDriver driver;
    LoginPage loginPage;
    ProductPage2 productPage;
  

    @Given("User is on the Magento login page")
    public void user_is_on_the_magento_login_page() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Initialize Page Objects
        loginPage = new LoginPage(driver);
        
        productPage = new ProductPage2(driver);
        ReportManager.createTest("Magento checkout test");
        
        loginPage.navigateToLoginPage();
    }

    @When("User logs in with {string} and {string}")
    public void user_logs_in_with_and(String email, String password) {
    
        loginPage.enterEmail(email);
        
        loginPage.enterPassword(password);
        

        loginPage.clickSignIn();
        ReportManager.logInfo("User logged in with Email: " + email);
        
    }

    @When("User selects a hoodie and adds to cart")
    public void user_selects_a_hoodie_and_adds_to_cart() {
        productPage.searchProduct("Hoodies and Sweatshirts for women");
        
        productPage.selectHoodie();
       
        productPage.selectSize("M");  // Select Medium Size
        
        productPage.selectColour();  // Select Colour
        

        productPage.addToCart();  // Add hoodie to cart
        
        ReportManager.logInfo("User selected a hoodie and added it to the cart");

    }

    @Then("the product should be displayed in the cart")
    public void the_product_should_be_displayed_in_the_cart() throws IOException {
        productPage.openCart();
        
        ReportManager.logPass("Hoodie is displayed in the cart");

      
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            ReportManager.logInfo("Browser closed");
        }
    }
}
