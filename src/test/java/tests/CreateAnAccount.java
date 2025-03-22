package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class CreateAnAccount {

	    private WebDriver driver;
	    private ExtentReports extent;
	    private ExtentTest test;

	    @BeforeSuite
	    public void setUpReport() {
	        // Initialize ExtentReports and attach a SparkReporter
	        extent = new ExtentReports();
	        ExtentSparkReporter spark = new ExtentSparkReporter("reports/CreateReport.html");
	        extent.attachReporter(spark);
	    }

	    @BeforeMethod
	    public void setUp() {
	        // Set the path to your WebDriver executable
	        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

	        // Initialize the WebDriver
	    	 driver = new ChromeDriver();
	         driver.manage().window().maximize();
	    }

	    @Test
	    public void testAccountCreation() {
	        // Create a test in the Extent Report
	        test = extent.createTest("Magento create an account test", "Test to validate account creation on Magento");

	        try {
	            // Navigate to the account creation page
	            driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
	            test.log(Status.INFO, "Navigated to the account creation page");

	            // Fill out the registration form
	            WebElement firstName = driver.findElement(By.id("firstname"));
	            firstName.sendKeys("Ludhiya");
	            test.log(Status.INFO, "Entered first name: Ludhiya");

	            WebElement lastName = driver.findElement(By.id("lastname"));
	            lastName.sendKeys("Payagalla");
	            test.log(Status.INFO, "Entered last name: Payagalla");

	            WebElement email = driver.findElement(By.id("email_address"));
	            email.sendKeys("ludhiya1011@gmail.com");
	            test.log(Status.INFO, "Entered email: ludhiya1014@gmail.com");

	            WebElement password = driver.findElement(By.id("password"));
	            password.sendKeys("Sampath@123");
	            test.log(Status.INFO, "Entered password: Sampath@123");

	            WebElement confirmPassword = driver.findElement(By.id("password-confirmation"));
	            confirmPassword.sendKeys("Sampath@123");
	            test.log(Status.INFO, "Confirmed password: Sampath@123");

	            // click on create account
	            WebElement createAccountButton = driver.findElement(By.cssSelector("button.action.submit.primary"));
	            createAccountButton.click();
	            test.log(Status.INFO, "Clicked on the 'Create Account' button");

	         
	            // to check successful creation of an account
	            String successMessage = driver.findElement(By.cssSelector(".message-success")).getText();
	            if (successMessage.contains("Thank you for registering")) {
	                test.log(Status.PASS, "Account creation was successful");
	            } else {
	                test.log(Status.FAIL, "Account creation failed");
	            }
	        } catch (Exception e) {
	            // Log any exceptions in the report
	            test.log(Status.FAIL, "Test failed with exception: " + e.getMessage());
	        }
	    }

	    @AfterMethod
	    public void tearDown() {
	        // Close the browser
	        if (driver != null) {
	            driver.quit();
	        }
	    }

	    @AfterSuite
	    public void tearDownReport() {
	        // Flush the ExtentReports instance to generate the report
	        extent.flush();
	    }
	}


