package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DriverFactory;

//“I manage WebDriver lifecycle using Cucumber hooks
// and capture screenshots automatically on scenario failure to improve debugging and reporting.”

public class Hooks {

    @Before
    public void setup() {
        DriverFactory.initializeDriver();
    }

    @After
    public void teardown(Scenario scenario) {
        // Capture screenshot on failure
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failure Screenshot");
        }
        DriverFactory.quitDriver();
    }
}