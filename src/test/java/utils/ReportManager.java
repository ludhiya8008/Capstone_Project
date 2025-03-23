package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;





public class ReportManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testThreadLocal = new ThreadLocal<>();

    // Initialize the Extent Report
    public static void initializeReport() {
        if (extent == null) {
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter("target/productDisplayed.html");
            htmlReporter.config().setDocumentTitle("Automation Report");
            htmlReporter.config().setReportName("Magento Automation Testing");
            htmlReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
    }

    // Create a new test case in the report
    public static void createTest(String testName) {
        if (extent == null) {
            initializeReport(); // Ensure report is initialized
        }
        ExtentTest test = extent.createTest(testName);
        testThreadLocal.set(test);
    }

    // Log information to the test
    public static void logInfo(String message) {
        if (testThreadLocal.get() != null) {
            testThreadLocal.get().info(message);
        } else {
            throw new IllegalStateException("Test instance is null. Call createTest() first.");
        }
    }

    // Log a failure message
    public static void logFail(String message) {
        if (testThreadLocal.get() != null) {
            testThreadLocal.get().fail(message);
        }
    }

    // Log a success message
    public static void logPass(String message) {
        if (testThreadLocal.get() != null) {
            testThreadLocal.get().pass(message);
        }
    }

    // Flush the report at the end of execution
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
