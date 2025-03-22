package StepDef;



import tests.BaseClass3;
import pages.Loginpage2;
import utils.ConfigReader;
import utils.ReportManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class invalidLogin {

    private WebDriver driver;
    private Loginpage2 loginpage2;

    @Before
    public void setup() {
        ReportManager.createTest("User attempts to create an account with invalid details");

        // Initialize WebDriver from BaseClass1
        BaseClass3.initialization(ConfigReader.getProperty("browser"));
        driver = BaseClass3.getDriver();
        loginpage2 = new Loginpage2(driver);
    }

    @Given("User is on the Magento Sign Up page")
    public void user_is_on_the_magento_sign_up_page() {
        driver.get(ConfigReader.getProperty("url"));
        ReportManager.logInfo("User navigated to Magento Sign Up page");
    }

    @When("User enters invalid first name {string}, last name {string}, email {string}, password {string}, and confirm password {string}")
    public void user_enters_invalid_details(String firstName, String lastName, String email, String password, String confirmPassword) {
        loginpage2.enterFirstName(firstName);
        loginpage2.enterLastName(lastName);
        loginpage2.enterEmail(email);
        loginpage2.enterPassword(password);
        loginpage2.enterConfirmPassword(confirmPassword);
        ReportManager.logInfo("Entered invalid details: " + firstName + ", " + lastName + ", " + email);
    }

    @And("Clicks on Create Account button")
    public void clicks_on_create_account_button() {
        loginpage2.clickCreateAccount();
        ReportManager.logInfo("Clicked on Create Account button");
    }

    @Then("User should see an error message")
    public void user_should_see_an_error_message() throws IOException {
       
    }

    @After
    public void tearDown() {
        ReportManager.flushReport();
        BaseClass3.closeBrowser();
    }
}
