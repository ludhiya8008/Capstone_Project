package Runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import Pages.ReportManager;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
	    features = "C:\\Users\\sampa\\eclipse-workspace\\Project_Luma\\src\\test\\java\\feature\\login.feature",
	    glue = "StepDef",
	    tags = "@Smoke",
	    plugin = {"pretty","html:target/cucumber-reports/cucumber.html"},
	    monochrome = true
	)
	public class loginRunner extends AbstractTestNGCucumberTests {
	    
	    @BeforeClass
	    public static void setup() {
	        ReportManager.initializeReport();
	    }

	    @AfterClass
	    public static void teardown() {
	        ReportManager.flushReport();
	    }
	}
