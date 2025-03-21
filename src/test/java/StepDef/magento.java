package StepDef;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class magento extends BaseClass2{
	static WebDriver driver;
	
	@Given("I am on the login page")
	public void i_am_on_the_login_page() {
		//driver = new ChromeDriver();
		driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://magento.softwaretestingboard.com/customer/account/login/");

	    
	}

	@Given("I am on the {string}")
	public void i_am_on_the(String string) {
		//click on create an account
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
	    
	}

	@When("I enter first name in the First Name field.")
	public void i_enter_first_name_in_the_first_name_field() {
		//enter first name
		  driver.findElement(By.name("firstname")).sendKeys("Ludhiya");

	    
	}

	@And("I enter last name in the Last Name field.")
	public void i_enter_last_name_in_the_last_name_field() {
		//enter last name
		driver.findElement(By.name("lastname")).sendKeys("Payagalla");

	    
	}

	@And("I enter valid email address in the Email field.")
	public void i_enter_valid_email_address_in_the_email_field() {
		//enter email id
		driver.findElement(By.name("email")).sendKeys("ludhiya985@gmail.com");

	}

	@And("I enter valid password in the Password field.")
	public void i_enter_valid_password_in_the_password_field() {
		//enter password
		driver.findElement(By.name("password")).sendKeys("Sampath@123");
		//it will check if password is weak
	    try {
			WebElement passwordError = driver.findElement(By.id("password-error"));
			if(passwordError.isDisplayed()) {
				System.out.println("Weak Password Error:"+passwordError.getText());
			
				
			}
		}catch(Exception e) {
			
		}
	        
	}

	@And("I enter password in the Confirm Password field.")
	public void i_enter_password_in_the_confirm_password_field() {
		//confirm password
		driver.findElement(By.name("password_confirmation")).sendKeys("Sampath@123");

	    
	}

	@And("I click on the {string} button")
	public void i_click_on_the_button(String string) {
		//Create an account
		driver.findElement(By.xpath("//button[@class='action submit primary']")).click();

	}

	@Then("I should be redirected to the customer dashboard")
	//dashboard
	public void i_should_be_redirected_to_the_customer_dashboard() {
	    
	}

	@Then("I close the browser in Google Search.")
//	//close the browser
	public void i_close_the_browser_in_google_search() {
		//throw new io.cucumber.java.PendingException();
	}
}





