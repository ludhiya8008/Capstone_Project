package Runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class)
@CucumberOptions(features = "C:\\Users\\sampa\\eclipse-workspace\\Project_Luma\\src\\test\\java\\feature\\magento.feature",
glue="StepDef",plugin= {"pretty","html:target/magento_Reports.html"}, monochrome=true)


public class magentoRunner extends AbstractTestNGCucumberTests{

}
