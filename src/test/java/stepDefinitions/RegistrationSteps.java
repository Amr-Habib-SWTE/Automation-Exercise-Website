package stepDefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.LoginPage;
import pages.RegistrationPage;
import utils.DriverFactory;

public class RegistrationSteps {

    LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
    RegistrationPage registrationPage = new RegistrationPage(DriverFactory.getDriver());

    @When("I enter new name {string} and email {string}")
    public void enter_new_user(String name, String email) {
        loginPage.signup(name, email);
    }

    @When("I click Signup button")
    public void click_signup() {
    }

    @Then("I see {string} header")
    public void see_account_header(String header) {
        Assert.assertTrue(registrationPage.verifyAccountInfoHeader(), "Account Info header not visible");
    }

    @When("I fill account details with password {string}")
    public void fill_acct(String pwd) {
        registrationPage.fillAccountDetails(pwd);
    }

    @When("I fill personal details")
    public void fill_personal() {
        registrationPage.fillPersonalDetails("Amr", "Habib", "Allianz", "Building A1", "Business Park", "Egypt", "New Cairo", "Cairo", "90001", "01088796544");
    }

    @When("I enter new name {string} and existing email {string}")
    public void enterNameAndExistingEmail(String name, String email) {
        loginPage.signup(name, email);
    }

    @When("I click Create Account")
    public void click_create() {
        registrationPage.clickCreateAccount();
    }

    @Then("I see {string} message")
    public void see_msg(String msg) {
        Assert.assertTrue(registrationPage.verifyAccountCreated(), "Account Created message not visible");
    }

}