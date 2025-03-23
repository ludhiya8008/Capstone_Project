package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pages.LoginPage3;
import utils.ExtentReportManager;


public class LoginTest {
	
    WebDriver driver;
    LoginPage3 loginPage;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://magento.softwaretestingboard.com/customer/account/login/");
        loginPage = new LoginPage3(driver);
        extent = ExtentReportManager.getInstance();
    }

    @Test(enabled = true)
    public void testValidLogin() {
        test = extent.createTest("Valid Login Test");
        loginPage.enterEmail("ludhiya2002@gmail.com");
        loginPage.enterPassword("Sampath@123");
        loginPage.clickSignIn();

        String expectedTitle = "My Account";
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle), "Login failed");

        test.log(Status.PASS, "User successfully logged in");
    }

    @Test(enabled = false)
    public void testInvalidLogin() {
        test = extent.createTest("Invalid Login Test");
        
        // Navigate back to the login page
        driver.get("https://magento.softwaretestingboard.com/customer/account/login/");
       
      
        loginPage.enterEmail("invalid@example.com");
        loginPage.enterPassword("123");
        loginPage.clickSignIn();

        
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed: " + result.getName());
            test.log(Status.FAIL, "Test Failed with exception: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Skipped: " + result.getName());
        }
        extent.flush();
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}