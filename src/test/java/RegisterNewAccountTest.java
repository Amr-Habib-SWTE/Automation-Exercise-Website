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


public class RegisterNewAccountTest {

     WebDriver driver;
     WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        // Set up WebDriver and initialize browser
        driver = new ChromeDriver();
    }

         @Test
         public void registerNewAccount(){
            // Step 1: Launch browser
            driver.get("https://automationexercise.com");

            // Step 2: Verify home page is visible
             wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='/static/images/home/logo.png']")));

            if (logo.isDisplayed()) {
                System.out.println("Home Page is visible successfully.");
            } else {
                System.out.println("Home Page not visible.");
            }

            // Step 3: Click on 'Signup / Login' button
            WebElement signupLoginButton = driver.findElement(By.xpath("//a[normalize-space()='Signup / Login']"));
            signupLoginButton.click();

            // Step 4: Verify 'New User Signup!' is visible
            WebElement newUserSignup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='New User Signup!']")));
            System.out.println("New User Signup! is visible.");

            // Step 5: Enter name and email address
            WebElement nameInput = driver.findElement(By.cssSelector("input[placeholder='Name']"));
            WebElement emailInput = driver.findElement(By.cssSelector("input[data-qa='signup-email']"));
            nameInput.sendKeys("Amr Habib");
            emailInput.sendKeys("amrhabib6@example.com");

            // Step 6: Click 'Signup' button
            WebElement signupButton = driver.findElement(By.xpath("//button[text()='Signup']"));
            signupButton.click();

            // Step 7: Verify 'ENTER ACCOUNT INFORMATION' is visible
            WebElement accountInfoHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()='Enter Account Information']")));
            System.out.println("ENTER ACCOUNT INFORMATION is visible.");

            // Step 8: Fill details
            driver.findElement(By.id("id_gender1")).click(); // Title
            driver.findElement(By.id("password")).sendKeys("password123");
            driver.findElement(By.id("days")).sendKeys("1");
            driver.findElement(By.id("months")).sendKeys("January");
            driver.findElement(By.id("years")).sendKeys("1990");

            // Step 9: Select checkbox 'Sign up for our newsletter!'
            driver.findElement(By.id("newsletter")).click();

            // Step 10: Select checkbox 'Receive special offers from our partners!'
            driver.findElement(By.id("optin")).click();

            // Step 11: Fill remaining details
            driver.findElement(By.id("first_name")).sendKeys("Amr");
            driver.findElement(By.id("last_name")).sendKeys("Habib");
            driver.findElement(By.id("company")).sendKeys("Allianz");
            driver.findElement(By.id("address1")).sendKeys("Building A1");
            driver.findElement(By.id("address2")).sendKeys("Business Park A");
            driver.findElement(By.id("country")).sendKeys("Egypt");
            driver.findElement(By.id("state")).sendKeys("New Cairo");
            driver.findElement(By.id("city")).sendKeys("Cairo Festival City");
            driver.findElement(By.id("zipcode")).sendKeys("90001");
            driver.findElement(By.id("mobile_number")).sendKeys("01088796544");

            // Step 12: Click 'Create Account' button
            driver.findElement(By.xpath("//button[text()='Create Account']")).click();

            // Step 13: Verify 'ACCOUNT CREATED!' is visible
            WebElement accountCreatedMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()='Account Created!']")));
            System.out.println("ACCOUNT CREATED! is visible.");

            // Step 14: Click 'Continue' button
            driver.findElement(By.xpath("//a[text()='Continue']")).click();

            // Step 15: Verify 'Logged in as username' is visible
            WebElement loggedInMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li:nth-child(10) a:nth-child(1)")));
            System.out.println("Logged in as username is visible.");

        }

        @AfterMethod
        public void teardown () {
            driver.quit();
        }

}
