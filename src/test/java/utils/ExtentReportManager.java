package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
	private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/MyCart.html");
            ExtentSparkReporter spark1 = new ExtentSparkReporter("reports/Login.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.attachReporter(spark1);
            
           
            
        }
        return extent;
    }

}
