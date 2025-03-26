package StepDef;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;

import utils.ExtentReportManager;

public class BaseClass {
	
	    protected WebDriver driver;
	    protected ExtentReports extent;

	    @BeforeClass
	    @Parameters({"browser", "url"})
	    public void setup(@Optional("chrome")String browser, @Optional("https://magento.softwaretestingboard.com/customer/account/login/") String url) {
	        extent = ExtentReportManager.getInstance();

	        if (browser.equalsIgnoreCase("chrome")) {
	            driver = new ChromeDriver();
	        } else if (browser.equalsIgnoreCase("edge")) {
	            driver = new EdgeDriver();
	        } else {
	            throw new IllegalArgumentException("Browser not supported: " + browser);
	        }

	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.get(url);
	    }

	    @AfterClass
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	        extent.flush();
	    }
	    
	}
