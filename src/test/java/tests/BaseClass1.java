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


public class BaseClass1 {
    static WebDriver driver;

    // Initialize the browser
    public static void initialization(String browser) {
        try {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid browser name: " + browser);
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } catch (Exception e) {
            System.out.println("Error initializing browser: " + e.getMessage());
        }
    }

    // Close the browser
    public static void closeBrowser() {
        try {
            if (driver != null) {
                driver.quit();
                driver = null; // Prevent stale driver issues
            }
        } catch (Exception e) {
            System.out.println("Error closing browser: " + e.getMessage());
        }
    }

    // Take a screenshot
    public static void takeScreenshot(String testName) {
        try {
            if (driver != null) {
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String screenshotPath = "./screenshots/" + testName + "-" + System.currentTimeMillis() + ".png";
                Files.copy(src.toPath(), Paths.get(screenshotPath));
                System.out.println("Screenshot saved: " + screenshotPath);
            }
        } catch (IOException e) {
            System.out.println("Error capturing screenshot: " + e.getMessage());
        }
    }

    // Get WebDriver instance
    public static WebDriver getDriver() {
        return driver;
    }
}
