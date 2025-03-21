package StepDef;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.google.common.io.Files;

public class BaseClass2 {
	public static WebDriver driver;


	    // Method to initialize the WebDriver
	    public static void initialization() {
	        // Set the path to your WebDriver executable
	        String browser = "chrome"; // Change this to "firefox" or "edge" if needed
	        switch (browser.toLowerCase()) {
	            case "chrome":
	                System.setProperty("webdriver.chrome.driver", "path/to/chromedriver"); // Update the path
	                driver = new ChromeDriver();
	                break;
	            case "firefox":
	                System.setProperty("webdriver.gecko.driver", "path/to/geckodriver"); // Update the path
	                driver = new FirefoxDriver();
	                break;
	            case "edge":
	                System.setProperty("webdriver.edge.driver", "path/to/msedgedriver"); // Update the path
	                driver = new EdgeDriver();
	                break;
	            default:
	                throw new IllegalArgumentException("Unsupported browser: " + browser);
	        }

	        // Common setup tasks
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.get("https://magento.softwaretestingboard.com/customer/account/login/");
	    }

	    // Method to close the browser
	    public static void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}