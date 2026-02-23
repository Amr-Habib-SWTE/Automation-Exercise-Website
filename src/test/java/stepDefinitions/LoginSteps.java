package stepDefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.LoginPage;
import utils.DriverFactory;

public class LoginSteps {

    LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

    @When("I click Signup Login button")
    public void click_signup_login() {
        loginPage.clickSignupLogin();
    }

    @When("I enter email {string} and password {string}")
    public void enter_credentials(String email, String pwd) {
        loginPage.login(email, pwd);
    }

    @When("I click Login button")
    public void click_login() {

    }

    @Then("I see error message {string}")
    public void see_error(String msg) {
        Assert.assertTrue(loginPage.verifyLoginError(), "Login error message not visible");
    }

    @Then("I verify user is logged in")
    public void verify_logged_in() {
        Assert.assertTrue(loginPage.verifyLoggedInUser(), "User is not logged in");
    }

    @Then("I see email exist error {string}")
    public void see_email_error(String msg) {
        Assert.assertTrue(loginPage.verifyEmailExistError(), "Email exist error not visible");
    }
}