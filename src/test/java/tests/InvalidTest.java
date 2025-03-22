package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseClass1;
import pages.InvalidPage;
import utils.ConfigReader;


public class InvalidTest extends BaseClass1 {
    
	private InvalidPage createAccountPage;

    @BeforeMethod
    public void setUp() {
        initialization(ConfigReader.getProperty("browser")); // Fixed function call
        driver.get(ConfigReader.getProperty("url")); // Ensure URL is loaded
        createAccountPage = new InvalidPage(getDriver()); // Use getDriver() for consistency
    }

    @Test
    public void testCreateAccountWithInvalidCredentials() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement createAccountLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Create an Account")));
            createAccountLink.click();

            // Enter invalid credentials
            createAccountPage.enterFirstName(ConfigReader.getProperty("firstname"));
            createAccountPage.enterLastName(ConfigReader.getProperty("lastname"));
            createAccountPage.enterEmail(ConfigReader.getProperty("invalid_email"));
            createAccountPage.enterPassword(ConfigReader.getProperty("password"));
            createAccountPage.enterConfirmPassword(ConfigReader.getProperty("confirm_password"));

            // Click Create Account button
            createAccountPage.clickCreateAccountButton();

            

        } catch (Exception e) {
            e.printStackTrace();            
        }
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser();
    }
}