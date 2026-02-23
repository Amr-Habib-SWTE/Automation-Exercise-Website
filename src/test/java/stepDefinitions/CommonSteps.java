package stepDefinitions;

import io.cucumber.java.en.Given;
import utils.DriverFactory;

public class CommonSteps {

    @Given("I navigate to Automation Exercise")
    public void navigate() {
        DriverFactory.getDriver().get("https://automationexercise.com");
    }
}