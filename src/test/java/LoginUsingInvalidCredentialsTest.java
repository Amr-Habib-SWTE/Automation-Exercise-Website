import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;


public class LoginUsingInvalidCredentialsTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        // Step 1: Click on 'Signup / Login' button
        driver.get("https://automationexercise.com");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void loginUsingInvalidCredentials() {
        WebElement signupLoginButton = driver.findElement(By.xpath("//a[normalize-space()='Signup / Login']"));
        signupLoginButton.click();

        // Step 2: Verify 'Login to your account' is visible
        WebElement loginHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Login to your account']")));
        Assert.assertEquals(loginHeader.getText(), "Login to your account");
        System.out.println("Login to your account is visible.");

        // Step 3: Enter incorrect email address and password
        WebElement emailInput = driver.findElement(By.cssSelector("input[data-qa='login-email']"));
        WebElement passwordInput = driver.findElement(By.cssSelector("input[placeholder='Password']"));
        emailInput.sendKeys("incorrectemail@example.com"); // Invalid email
        passwordInput.sendKeys("wrong password"); // Invalid password

        // Step 4: Click 'login' button
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        // Step 5: Verify error 'Your email or password is incorrect!' is visible
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Your email or password is incorrect!']")));
        if (errorMessage.isDisplayed()) {
            System.out.println("Error Message: 'Your email or password is incorrect!' is visible.");
        } else {
            System.out.println("Error message not displayed.");
        }

    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}






