package utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager {
	private static ExtentReports extent;
    private static ExtentTest test;

    public static void initReport() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("report/LoginReports.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    
    

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }

	public static void logInfo(String message) {
		// TODO Auto-generated method stub
		if(test==null) {
			test = extent.createTest("Test execution");
		}
		test.info(message);
		
	}




	public static void initializeReport() {
		// TODO Auto-generated method stub
		
	}
}
