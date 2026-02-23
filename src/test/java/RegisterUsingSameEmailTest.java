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


public class RegisterUsingSameEmailTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
         driver = new ChromeDriver();

    }

    @Test
    public void registerUsingSameEmail() {

        // Step 1: Launch browser
        driver.get("https://automationexercise.com");

        // Step 2: Verify home page is visible
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='/static/images/home/logo.png']")));

        if (logo.isDisplayed()) {
            System.out.println("Home Page is visible successfully.");
        } else {
            System.out.println("Home Page not visible.");
            return;
        }

        // Step 3: Click on 'Signup / Login' button
        WebElement signupLoginButton = driver.findElement(By.xpath("//a[normalize-space()='Signup / Login']"));
        signupLoginButton.click();

        // Step 4: Verify 'New User Signup!' is visible
        WebElement newUserSignup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='New User Signup!']")));
        System.out.println("New User Signup! is visible.");

        // Step 5: Enter name and already registered email address
        WebElement nameInput = driver.findElement(By.cssSelector("input[placeholder='Name']"));
        WebElement emailInput = driver.findElement(By.cssSelector("input[data-qa='signup-email']"));
        nameInput.sendKeys("Amr Habib");
        emailInput.sendKeys("amrhabib1@example.com"); // Assuming this email is already registered

        // Step 6: Click 'Signup' button
        WebElement signupButton = driver.findElement(By.xpath("//button[text()='Signup']"));
        signupButton.click();

        // Step 7: Verify error message 'Email Address already exist!' is visible
        WebElement emailExistErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Email Address already exist!']")));
        if (emailExistErrorMessage.isDisplayed()) {
            System.out.println("Error Message: 'Email Address already exist!' is visible.");
        } else {
            System.out.println("Error message not displayed.");
        }
    }
        @AfterMethod
        public void teardown () {
            driver.quit();
        }
    }


