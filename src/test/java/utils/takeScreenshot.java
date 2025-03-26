package utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class takeScreenshot{
		WebDriver driver;
		public void screenshot(String name) {
	    try {
	        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(src, new File("screenshots/" + name + "_" + System.currentTimeMillis() + ".png"));
	    } catch (Exception e) {
	        System.out.println("Screenshot failed: " + e.getMessage());
	    }
	}
}


