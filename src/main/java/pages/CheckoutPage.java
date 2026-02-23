package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(xpath = "(//a[normalize-space()='Proceed To Checkout'])[1]") WebElement proceedCheckoutBtn;
    @FindBy(xpath = "(//h2[normalize-space()='Address Details'])[1]") WebElement addressDetailsHeader;
    @FindBy(className = "form-control") WebElement commentArea;
    @FindBy(xpath = "(//a[normalize-space()='Place Order'])[1]") WebElement placeOrderBtn;

    // Payment
    @FindBy(name = "name_on_card") WebElement nameCard;
    @FindBy(name = "card_number") WebElement numCard;
    @FindBy(name = "cvc") WebElement cvc;
    @FindBy(name = "expiry_month") WebElement expMonth;
    @FindBy(name = "expiry_year") WebElement expYear;
    @FindBy(id = "submit") WebElement payBtn;
    @FindBy(xpath = "//p[normalize-space()='Congratulations! Your order has been confirmed!']") WebElement successMsg;
    @FindBy(xpath = "(//a[normalize-space()='Download Invoice'])[1]") WebElement downloadInvoiceBtn;

    public CheckoutPage(WebDriver driver) { super(driver); }

    public void clickProceedCheckout() { click(proceedCheckoutBtn); }
    public boolean verifyAddressDetails() { return isDisplayed(addressDetailsHeader); }
    public void placeOrder(String comment) {
        sendKeys(commentArea, comment);
        click(placeOrderBtn);
    }
    public void enterPaymentDetails(String name, String num, String cvcCode, String mo, String yr) {
        sendKeys(nameCard, name);
        sendKeys(numCard, num);
        sendKeys(cvc, cvcCode);
        sendKeys(expMonth, mo);
        sendKeys(expYear, yr);
        click(payBtn);
    }
    public boolean verifyOrderSuccess() { return isDisplayed(successMsg); }
    public void clickDownloadInvoice() { click(downloadInvoiceBtn); }
}