package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

    @FindBy(xpath = "//b[text()='Enter Account Information']")
    WebElement accountInfoHeader;

    @FindBy(id = "id_gender1") WebElement genderMr;
    @FindBy(id = "password") WebElement passwordField;
    @FindBy(id = "days") WebElement daySelect;
    @FindBy(id = "months") WebElement monthSelect;
    @FindBy(id = "years") WebElement yearSelect;
    @FindBy(id = "newsletter") WebElement newsletterChk;
    @FindBy(id = "optin") WebElement optinChk;

    @FindBy(id = "first_name") WebElement firstName;
    @FindBy(id = "last_name") WebElement lastName;
    @FindBy(id = "company") WebElement company;
    @FindBy(id = "address1") WebElement address1;
    @FindBy(id = "address2") WebElement address2;
    @FindBy(id = "country") WebElement country;
    @FindBy(id = "state") WebElement state;
    @FindBy(id = "city") WebElement city;
    @FindBy(id = "zipcode") WebElement zipcode;
    @FindBy(id = "mobile_number") WebElement mobile;

    @FindBy(xpath = "//button[text()='Create Account']") WebElement createAccountBtn;
    @FindBy(xpath = "//b[text()='Account Created!']") WebElement accountCreatedHeader;
    @FindBy(xpath = "//a[text()='Continue']") WebElement continueBtn;

    public RegistrationPage(WebDriver driver) { super(driver); }

    public boolean verifyAccountInfoHeader() { return isDisplayed(accountInfoHeader); }

    public void fillAccountDetails(String password) {
        click(genderMr);
        sendKeys(passwordField, password);
        sendKeys(daySelect, "1");
        sendKeys(monthSelect, "January");
        sendKeys(yearSelect, "1990");
        click(newsletterChk);
        click(optinChk);
    }

    public void fillPersonalDetails(String fname, String lname, String comp, String add1, String add2, String cntry, String st, String ct, String zip, String mob) {
        sendKeys(firstName, fname);
        sendKeys(lastName, lname);
        sendKeys(company, comp);
        sendKeys(address1, add1);
        sendKeys(address2, add2);
        sendKeys(country, cntry);
        sendKeys(state, st);
        sendKeys(city, ct);
        sendKeys(zipcode, zip);
        sendKeys(mobile, mob);
    }

    public void clickCreateAccount() { click(createAccountBtn); }
    public boolean verifyAccountCreated() { return isDisplayed(accountCreatedHeader); }
    public void clickContinue() { click(continueBtn); }
}