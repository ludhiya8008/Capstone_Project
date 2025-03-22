package tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass3 {
	    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	    // Initialize the browser
	    public static void initialization(String browser) {
	        try {
	            WebDriver webDriver;
	            switch (browser.toLowerCase()) {
	                case "chrome":
	                    //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
	                    webDriver = new ChromeDriver();
	                    break;
	                case "firefox":
	                    //System.setProperty("webdriver.gecko.driver", "path/to/geckodriver.exe");
	                    webDriver = new FirefoxDriver();
	                    break;
	                case "edge":
	                    //System.setProperty("webdriver.edge.driver", "path/to/msedgedriver.exe");
	                    webDriver = new EdgeDriver();
	                    break;
	                default:
	                    throw new IllegalArgumentException("Invalid browser name: " + browser);
	            }
	            webDriver.manage().window().maximize();
	            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	            driver.set(webDriver);
	        } catch (Exception e) {
	            throw new RuntimeException("Error initializing browser: " + e.getMessage());
	        }
	    }

	    // Get WebDriver instance
	    public static WebDriver getDriver() {
	        if (driver.get() == null) {
	            throw new IllegalStateException("WebDriver is not initialized. Call initialization() first.");
	        }
	        return driver.get();
	    }

	    // Close the browser
	    public static void closeBrowser() {
	        if (driver.get() != null) {
	            driver.get().quit();
	            driver.remove();
	        }
	    }

	    // Take a screenshot
	    public static void takeScreenshot(String testName) {
	        try {
	            WebDriver webDriver = getDriver();
	            File src = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
	            String screenshotPath = "./screenshots/" + testName + "-" + System.currentTimeMillis() + ".png";
	            Files.copy(src.toPath(), Paths.get(screenshotPath));
	            System.out.println("Screenshot saved: " + screenshotPath);
	        } catch (IOException e) {
	            System.out.println("Error capturing screenshot: " + e.getMessage());
	        }
	    }
	}



