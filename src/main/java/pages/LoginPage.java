package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    // --- Locators ---
    @FindBy(xpath = "//a[normalize-space()='Signup / Login']")
    WebElement signupLoginBtn;

    @FindBy(xpath = "//h2[text()='Login to your account']")
    WebElement loginHeader;

    @FindBy(css = "input[data-qa='login-email']")
    WebElement loginEmail;

    @FindBy(css = "input[placeholder='Password']")
    WebElement loginPassword;

    @FindBy(xpath = "//button[text()='Login']")
    WebElement loginBtn;

    @FindBy(xpath = "//p[text()='Your email or password is incorrect!']")
    WebElement loginErrorMsg;

    @FindBy(xpath = "//h2[text()='New User Signup!']")
    WebElement signupHeader;

    @FindBy(css = "input[placeholder='Name']")
    WebElement signupName;

    @FindBy(css = "input[data-qa='signup-email']")
    WebElement signupEmail;

    @FindBy(xpath = "//button[text()='Signup']")
    WebElement signupBtn;

    @FindBy(xpath = "//p[text()='Email Address already exist!']")
    WebElement emailExistError;

    @FindBy(css = "li:nth-child(10) a:nth-child(1)")
    WebElement loggedInAsUser;


    public LoginPage(WebDriver driver) { super(driver); }

    // --- Actions ---
    public void clickSignupLogin() { click(signupLoginBtn); }
    public boolean verifyLoginHeader() { return isDisplayed(loginHeader); }
    public void login(String email, String pwd) {
        sendKeys(loginEmail, email);
        sendKeys(loginPassword, pwd);
        click(loginBtn);
    }
    public boolean verifyLoginError() { return isDisplayed(loginErrorMsg); }
    public boolean verifySignupHeader() { return isDisplayed(signupHeader); }
    public void signup(String name, String email) {
        sendKeys(signupName, name);
        sendKeys(signupEmail, email);
        click(signupBtn);
    }
    public boolean verifyEmailExistError() { return isDisplayed(emailExistError); }
    public boolean verifyLoggedInUser() { return isDisplayed(loggedInAsUser); }
}