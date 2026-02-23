package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.CheckoutPage;
import pages.ProductPage;
import utils.DriverFactory;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ProductCheckoutSteps {

    ProductPage productPage = new ProductPage(DriverFactory.getDriver());
    CheckoutPage checkoutPage = new CheckoutPage(DriverFactory.getDriver());

    @When("I click Products button")
    public void click_products() {
        productPage.clickProducts();
    }

    @When("I search for {string}")
    public void search_product(String name) {
        productPage.searchProduct(name);
    }

    @When("I add first product to cart")
    public void add_first() {
        productPage.hoverAndAddFirstProduct();
    }

    @When("I click Continue Shopping")
    public void click_continue() {
        productPage.clickContinueShopping();
    }

    @When("I view second product and add {int} quantity")
    public void view_second(int qty) {
        productPage.viewSecondProduct();
        productPage.setQuantity(String.valueOf(qty));
    }

    @When("I click View Cart")
    public void view_cart() {
        productPage.clickViewCart();
    }

    @When("I proceed to checkout")
    public void proceed_checkout() {
        checkoutPage.clickProceedCheckout();
    }

    @When("I place order with comment {string}")
    public void place_order(String cmt) {
        checkoutPage.placeOrder(cmt);
    }

    @When("I enter payment details")
    public void payment() {
        checkoutPage.enterPaymentDetails("Amr Habib", "4242424242424242", "123", "12", "2030");
    }

    @Then("I verify order success message")
    public void verify_success() {
        Assert.assertTrue(checkoutPage.verifyOrderSuccess(), "Order success message not visible");
    }

    @Then("I download invoice and take screenshot")
    public void download_invoice_and_screenshot() throws InterruptedException, IOException {
        WebDriver driver = DriverFactory.getDriver();

        // 1. Click Download
        checkoutPage.clickDownloadInvoice();

        // 2. Wait for the file to exist in Downloads folder
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
}