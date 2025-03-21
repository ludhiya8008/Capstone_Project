package StepDef;

import static org.testng.Assert.assertTrue;


import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import Pages.LoginPage;
import Pages.ReportManager;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	private WebDriver driver;
    private LoginPage loginPage;
    
    @Before
    public void setup(Scenario scenario) {
    	driver = new ChromeDriver();
    	loginPage = new LoginPage(driver);
    	ReportManager.createTest(scenario.getName());
    }
   
    	
	
    @Given("I navigate to Magento login page")
	public void i_navigate_to_magento_login_page()  {
            loginPage.navigateToLoginPage();
            ReportManager.logInfo("Navigated to login page");
        }

        @When("I enter email {string}")
        public void enterEmail(String email) {
            loginPage.enterEmail(email);
            ReportManager.logInfo("Entered email: " + email);
        }

        @When("I enter password {string}")
        public void enterPassword(String password) {
            loginPage.enterPassword(password);
            ReportManager.logInfo("Entered password");
        }

        @When("I click the Sign In button")
    	public void i_click_the_sign_in_button() {
            loginPage.clickSignIn();
            ReportManager.logInfo("Clicked sign in button");
        }

        @Then("I should be redirected to my account dashboard")
    	public void i_should_be_redirected_to_my_account_dashboard() {	
            assertTrue(loginPage.getCurrentUrl().contains("account/"));
            ReportManager.logPass("Successfully redirected to dashboard");
        }

        @Then("I should see the welcome message containing {string}")
    	public void i_should_see_the_welcome_message_containing(String string) {
    		
    	    
    	}

        
    }
