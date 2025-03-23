package Runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "C:\\Users\\sampa\\eclipse-workspace\\Project_Luma\\src\\test\\java\\feature\\checkout.feature",
    glue = "StepDef", 
    plugin = {"pretty", "html:target/cucumber-reports.html","json:target/cucumber-reports.json"},
    monochrome = true)
public class checkoutRunner {
	

}
