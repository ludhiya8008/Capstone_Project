package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	
	    private static ExtentReports extent;
	    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	    public static void logInfo(String message) {
	        test.get().info(message);
	    }

	    public static void logPass(String message) {
	        test.get().pass(message);
	    }

	    public static void logFail(String message) {
	        test.get().fail(message);
	    }

	    public static void flushReport() {
	        extent.flush();
	    }

	    public static void createTest(String testName) {
	        test.set(extent.createTest(testName));
	    }

	    public static void initializeReport() {
	        extent = new ExtentReports();
	        ExtentSparkReporter spark = new ExtentSparkReporter("reports/InvalidReport.html");
	        extent.attachReporter(spark);
	    }
	}