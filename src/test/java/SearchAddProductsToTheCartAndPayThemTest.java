import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static org.testng.Assert.assertTrue;

@Test
public class SearchAddProductsToTheCartAndPayThemTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com");
        WebElement homePageLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='/static/images/home/logo.png']")));
        if (homePageLogo.isDisplayed()) {
            System.out.println("Home page is visible successfully.");
        } else {
            System.out.println("Home page is not visible.");
            return;
        }

        // Click on 'Signup / Login' button
        WebElement signupLoginButton = driver.findElement(By.xpath("//a[normalize-space()='Signup / Login']"));
        signupLoginButton.click();

        // Verify 'Login to your account' is visible
        WebElement loginHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Login to your account']")));
        assertTrue(loginHeader.isDisplayed(), "'Login to your account' is not visible");

        // Enter correct email address and password
        WebElement emailInput = driver.findElement(By.cssSelector("input[data-qa='login-email']"));
        WebElement passwordInput = driver.findElement(By.cssSelector("input[placeholder='Password']"));
        emailInput.sendKeys("amrhabib1@example.com"); // Correct email
        passwordInput.sendKeys("password123"); // Correct password

        // Click 'login' button
        WebElement loginButton = driver.findElement(By.xpath("//button[text()='Login']"));
        loginButton.click();
    }

    @Test
    public void checkout() throws InterruptedException, AWTException, IOException {
        // Step 2: Click on 'Products' button
        WebElement productsButton = driver.findElement(By.xpath("//a[contains(text(),'Products')]"));
        productsButton.click();

        // Step 3: Verify user is navigated to ALL PRODUCTS page successfully
        WebElement allProductsPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@href='/products'])[1]")));
        System.out.println("Navigated to All Products page successfully.");

        // Step 4: Enter product name in search input and click search button
        WebElement searchInput = driver.findElement(By.id("search_product"));
        WebElement searchButton = driver.findElement(By.id("submit_search"));
        searchInput.sendKeys("T-shirt");  // Replace with any product you want to search
        searchButton.click();

        // Step 5: Verify 'SEARCHED PRODUCTS' is visible
        WebElement searchedProductsHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Searched Products']")));
        System.out.println("Searched Products are visible.");

        // Step 6: Verify all the products related to search are visible
        WebElement firstProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='productinfo text-center'][1]")));
        if (firstProduct.isDisplayed()) {
            System.out.println("All searched products are visible.");
        } else {
            System.out.println("Searched products are not visible.");
            return;
        }

        // Step 7: Hover over first product and click 'Add to cart'
        Actions actions = new Actions(driver);
        WebElement firstProductHover = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div)[41]")));
        actions.moveToElement(firstProductHover).perform();
        System.out.println("Hovered on the first product");

        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Add to cart')])[2]")));
        addToCartButton.click();
        System.out.println("Added product to cart successfully.");

        // Step 8: Click 'Continue Shopping' button
        WebElement continueShoppingButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-success close-modal btn-block']")));
        continueShoppingButton.click();
        System.out.println("Click 'Continue Shopping' button");

        // Step 9: Hover over second product and click on 'View Product'
        WebElement secondProductHover = driver.findElement(By.xpath("(//div)[41]"));
        actions.moveToElement(secondProductHover).perform();
        System.out.println("Hovered on the second product");
        WebElement viewProductButton = driver.findElement(By.xpath("(//a[contains(text(),'View Product')])[2]"));
        viewProductButton.click();
        System.out.println("Click 'View product' button");

        // Step 10: Verify product detail is opened
        WebElement productDetailHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='product-information']")));
        System.out.println("Product Detail page opened.");

        // Step 11: Increase quantity to 4
        WebElement quantityInput = driver.findElement(By.xpath("(//input[@id='quantity'])[1]"));
        quantityInput.clear();
        quantityInput.sendKeys("4");
        System.out.println("Increase quantity to 4");

        // Step 12: Click 'Add to cart' button
        WebElement addToCartButton2 = driver.findElement(By.cssSelector("button[type='button']"));
        addToCartButton2.click();
        System.out.println("Added product to cart successfully.");

        // Step 13: Click 'View Cart' button
        WebElement viewCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//u[normalize-space()='View Cart'])[1]")));
        viewCartButton.click();
        System.out.println("Click 'View Cart' button");

        // Step 14: Verify that all added products are in Cart
        WebElement cartProduct1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Pure Cotton V-Neck T-Shirt"))); // Verify the T-shirt is in the cart
        WebElement cartProduct2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Green Side Placket Detail T-Shirt"))); // Replace with the actual second product name
        System.out.println("All products are in the cart.");

        // Step 15: Verify prices, quantities, and total price
        // Step 15.1: Locate the price of the first product in the cart
        WebElement firstProductPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[contains(text(),'Rs. 1299')])[1]")));
        String firstProductPriceText = firstProductPriceElement.getText();
        double firstProductPrice = Double.parseDouble(firstProductPriceText.replace("Rs. ", "").replace(",", ""));
        System.out.println("First product price is " + firstProductPrice);

        // Step 15.2: Locate the quantity of the first product in the cart
        WebElement firstProductQuantityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tr[id='product-28'] button[class='disabled']")));
        // Step 15.2.1:Extract the text inside the button
        String firstQuantityText = firstProductQuantityElement.getText();
        // Step 15.2.2:Parse the quantity to an integer
        int firstProductQuantity = Integer.parseInt(firstQuantityText);

        //  Step 15.2: Calculate the expected total price
        double expectedTotalPriceForTheFirstProduct = firstProductPrice * firstProductQuantity;
        System.out.println("Total price for the first product is " + expectedTotalPriceForTheFirstProduct);

        // Step 15.3: Locate the total price in the cart and verify it for the first product
        WebElement firstProductTotalPriceElement = driver.findElement(By.xpath("//p[@class='cart_total_price'][normalize-space()='Rs. 1299']"));
        String firstProductTotalPriceText = firstProductTotalPriceElement.getText();
        double firstProductTotalPrice = Double.parseDouble(firstProductTotalPriceText.replace("Rs. ", "").replace(",", ""));

        // Step 15.4: Verify the total price calculation
        if (firstProductTotalPrice == expectedTotalPriceForTheFirstProduct) {
            System.out.println("Price and total price for the first product are correct.");
        } else {
            System.out.println("Error: Price or total price is incorrect.");
            System.out.println("Expected Total Price For The First Product: Rs. " + expectedTotalPriceForTheFirstProduct);
            System.out.println("Actual Total Price For THe First Product: Rs. " + firstProductTotalPrice);
        }
        // Step 15.5: Locate the price of the Second product in the cart
        WebElement secondProductPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//p[normalize-space()='Rs. 1000'])[1]")));
        String secondProductPriceText = secondProductPriceElement.getText();
        double secondProductPrice = Double.parseDouble(secondProductPriceText.replace("Rs. ", "").replace(",", ""));
        System.out.println("Second product price is " + secondProductPrice);

        // Step 15.6: Locate the quantity of the second product in the cart
        WebElement secondProductQuantityElement = driver.findElement(By.xpath("(//button[normalize-space()='4'])[1]"));
        // Step 15.6.1:Extract the text inside the button
        String secondQuantityText = secondProductQuantityElement.getText();
        // Step 15.6.2:Parse the quantity to an integer
        int secondProductQuantity = Integer.parseInt(secondQuantityText);

        //  Step 15.7: Calculate the expected total price
        double expectedTotalPriceForTheSecondProduct = secondProductPrice * secondProductQuantity;
        System.out.println("Total price for the second product is " + expectedTotalPriceForTheSecondProduct);

        // Step 15.8: Locate the total price in the cart and verify it
        WebElement secondProductTotalPriceElement = driver.findElement(By.xpath("(//p[normalize-space()='Rs. 4000'])[1]"));
        String secondProductTotalPriceText = secondProductTotalPriceElement.getText();
        double secondProductTotalPrice = Double.parseDouble(secondProductTotalPriceText.replace("Rs. ", "").replace(",", ""));

        // Step 15.9: Verify the total price calculation
        if (secondProductTotalPrice == expectedTotalPriceForTheSecondProduct) {
            System.out.println("Price and total price for the second product are correct.");
        } else {
            System.out.println("Error: Price or total price is incorrect.");
            System.out.println("Expected Total Price For The Second Product: Rs. " + expectedTotalPriceForTheSecondProduct);
            System.out.println("Actual Total Price For The Second Product: Rs. " + secondProductTotalPrice);
        }

        // Step 16: Click 'Proceed to checkout' button
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("(//a[normalize-space()='Proceed To Checkout'])[1]"));
        proceedToCheckoutButton.click();

        // Step 17: Verify Address Details and Review Your Order
        WebElement addressDetailsHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h2[normalize-space()='Address Details'])[1]")));
        assertTrue(addressDetailsHeader.isDisplayed(), "Address Details");
        WebElement reviewYourOrderHeader = driver.findElement(By.xpath("(//h2[normalize-space()='Review Your Order'])[1]"));
        assertTrue(reviewYourOrderHeader.isDisplayed(), "Review Your Order");
        System.out.println("Address Details and Review Your Order sections are visible.");

        // Step 18: Enter description in comment text area and click 'Place Order'
        WebElement commentTextArea = driver.findElement(By.className("form-control"));
        commentTextArea.sendKeys("Please deliver on time.");
        WebElement placeOrderButton = driver.findElement(By.xpath("(//a[normalize-space()='Place Order'])[1]"));
        placeOrderButton.click();

        // Step 19: Enter payment details and click 'Pay and Confirm Order'
        WebElement nameOnCardInput = driver.findElement(By.xpath("(//input[@name='name_on_card'])[1]"));
        WebElement cardNumberInput = driver.findElement(By.xpath("(//input[@name='card_number'])[1]"));
        WebElement cvcInput = driver.findElement(By.xpath("//input[@placeholder='ex. 311']"));
        WebElement expirationMonthInput = driver.findElement(By.xpath("//input[@placeholder='MM']"));
        WebElement expirationYearInput = driver.findElement(By.xpath("//input[@placeholder='YYYY']"));
        nameOnCardInput.sendKeys("Amr Habib");
        cardNumberInput.sendKeys("4242424242424242");
        cvcInput.sendKeys("123");
        expirationMonthInput.sendKeys("12");
        expirationYearInput.sendKeys("2030");
        WebElement payAndConfirmOrderButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit")));
        payAndConfirmOrderButton.click();

        // Step 20: Verify success message 'Your order has been placed successfully!'
        wait.until(ExpectedConditions.urlContains("payment"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Congratulations! Your order has been confirmed!']")));
        Assert.assertTrue(true, "Congratulations! Your order has been confirmed!");
        System.out.println("Congratulations! Your order has been confirmed!");

        // Step 21. Wait for the file to exist in Downloads folder
        String downloadPath = System.getProperty("user.home") + "/Downloads";
        File dir = new File(downloadPath);
        File invoiceFile = null;

        // Try finding the file for up to 10 seconds
        for (int i = 0; i < 10; i++) {
            File[] files = dir.listFiles((d, name) -> name.contains("invoice") && name.endsWith(".txt"));
            if (files != null && files.length > 0) {
                // Get the most recently downloaded file
                invoiceFile = files[0];
                break;
            }
            Thread.sleep(1000);
        }

        Assert.assertNotNull(invoiceFile, "Invoice file was not downloaded!");

        // 3. Open the local .txt file inside the Chrome Browser
        // This allows Selenium to see the content and screenshot it
        String fileURL = "file:///" + invoiceFile.getAbsolutePath();
        driver.get(fileURL);

        // 4. Take Screenshot using Selenium
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File("target/invoice_screenshot.png");
        FileUtils.copyFile(srcFile, destFile);

        System.out.println("Invoice Screenshot saved at: " + destFile.getAbsolutePath());

//        // Optional: Navigate back to the website if you had more steps
//        driver.navigate().back();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
