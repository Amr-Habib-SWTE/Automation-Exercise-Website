import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;


public class LoginUsingValidCredentialsTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }
    @Test
    public void loginUsingValidCredentials() {
                // Step 1: Click on 'Signup / Login' button
                driver.get("https://automationexercise.com");
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                WebElement signupLoginButton = driver.findElement(By.xpath("//a[normalize-space()='Signup / Login']"));
                signupLoginButton.click();

                // Step 2: Verify 'Login to your account' is visible
                WebElement loginHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Login to your account']")));
                System.out.println("Login to your account is visible.");

                // Step 3: Enter correct email address and password
                WebElement emailInput = driver.findElement(By.cssSelector("input[data-qa='login-email']"));
                WebElement passwordInput = driver.findElement(By.cssSelector("input[placeholder='Password']"));
                emailInput.sendKeys("amrhabib1@example.com"); // Correct email (already registered)
                passwordInput.sendKeys("password123"); // Correct password

                // Step 4: Click 'login' button
                WebElement loginButton = driver.findElement(By.xpath("//button[text()='Login']"));
                loginButton.click();

                // Step 5: Verify that 'Logged in as username' is visible
                WebElement loggedInMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li:nth-child(10) a:nth-child(1)")));
                if (loggedInMessage.isDisplayed()) {
                    System.out.println("Logged in as username is visible. You are logged in successfully.");
                } else {
                    System.out.println("Login was unsuccessful.");
                }

            }

            @AfterMethod
            public void teardown() {
                driver.quit();
                }

}


