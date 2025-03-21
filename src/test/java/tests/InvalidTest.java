package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.InvalidPage;
import utils.ConfigReader;
import utils.ExtentReport;

public class InvalidTest extends BaseClass1 {
    private InvalidPage createAccountPage;

    @BeforeMethod
    public void setUp() {
        initialization(ConfigReader.getProperty("browser"));
        ExtentReport.initializeReport();
        ExtentReport.createTest("Create Account with Invalid Credentials");
        createAccountPage = new InvalidPage(driver);
    }

    @Test
    public void testCreateAccountWithInvalidCredentials() {
        try {
            // Navigate to Create Account Page
            driver.findElement(By.linkText("Create an Account")).click();

            // Enter invalid credentials
            createAccountPage.enterFirstName(ConfigReader.getProperty("firstname"));
            Thread.sleep(2000);
            createAccountPage.enterLastName(ConfigReader.getProperty("lastname"));
            Thread.sleep(2000);
            createAccountPage.enterEmail(ConfigReader.getProperty("invalid_email"));
            Thread.sleep(2000);
            createAccountPage.enterPassword(ConfigReader.getProperty("password"));
            Thread.sleep(2000);
            createAccountPage.enterConfirmPassword(ConfigReader.getProperty("confirm_password"));
            Thread.sleep(2000);

            // Click Create Account button
            createAccountPage.clickCreateAccountButton();

            
        } catch (Exception e) {
            ExtentReport.logFail("Test Failed: " + e.getMessage());
            Assert.fail("Test Failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        ExtentReport.flushReport();
    }
}