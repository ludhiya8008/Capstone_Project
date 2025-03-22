package StepDef;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import utils.ReportManager;

public class Hooks {

    @BeforeAll
    public static void setupSuite() {
        ReportManager.initializeReport(); // Ensure Extent Report is initialized
    }

    @AfterAll
    public static void tearDownSuite() {
        ReportManager.flushReport(); // Flush Extent Report at the end
    }
}
