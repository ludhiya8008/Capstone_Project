package Runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import org.testng.annotations.BeforeClass;
import utils.ReportManager;

//@RunWith(Cucumber.class)
@CucumberOptions(
        features = "C:\\Users\\sampa\\eclipse-workspace\\Project_Luma\\src\\test\\java\\feature\\invalidlogin.feature",
        glue = {"StepDef"},
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        tags = "@InvalidSignUp",
        monochrome = true
)
public class invalidRunner extends AbstractTestNGCucumberTests{

    @BeforeClass
    public static void setupSuite() {
        ReportManager.initializeReport(); // Ensure ExtentReports is initialized
    }
}

