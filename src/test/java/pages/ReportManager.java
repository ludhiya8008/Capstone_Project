package pages;

import java.text.SimpleDateFormat;

import java.util.Date;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportManager {
	    private static ExtentReports extent;
	    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	    // Initialize ExtentReports
	    public static void initializeReport() {
	        if (extent == null) {
	            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	            String reportName = "Test-Report-" + timeStamp + ".html";
	            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/" + reportName);
	            extent = new ExtentReports();
	            extent.attachReporter(spark);

	            // Report Configuration
	            spark.config().setDocumentTitle("Magento Login Test Report");
	            spark.config().setReportName("Automation Test Results");
	            spark.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);
	        }
	    }

	    // Create a test case
	    public static void createTest(String testName) {
	        if (extent == null) {
	            throw new IllegalStateException("ExtentReports has not been initialized. Call initializeReport() first.");
	        }
	        test.set(extent.createTest(testName));
	    }

	    // Log a passed step
	    public static void logPass(String message) {
	        test.get().pass(message);
	    }

	    // Log a failed step with a screenshot
	    public static void logFail(String message, WebDriver driver) {
	        test.get().fail(message);
	        // Capture and attach screenshot
	        String screenshotPath = captureScreenshot(driver);
	        if (screenshotPath != null && !screenshotPath.isEmpty()) {
	            test.get().addScreenCaptureFromPath(screenshotPath);
	        }
	    }

	    // Log an informational message
	    public static void logInfo(String message) {
	        test.get().info(message);
	    }

	    // Finalize and generate the report
	    public static void flushReport() {
	        if (extent != null) {
	            extent.flush();
	        }
	    }

	    // Capture screenshot and return the file path
	    private static String captureScreenshot(WebDriver driver) {
	        try {
	            // Implement screenshot capture logic (e.g., using TakesScreenshot)
	            // Example:
	            // File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	            // String screenshotPath = "screenshots/" + System.currentTimeMillis() + ".png";
	            // FileUtils.copyFile(screenshotFile, new File(screenshotPath));
	            // return screenshotPath;
	            return "path/to/screenshot.png"; // Replace with actual screenshot path
	        } catch (Exception e) {
	            System.err.println("Failed to capture screenshot: " + e.getMessage());
	            return "";
	        }
	    }
	}