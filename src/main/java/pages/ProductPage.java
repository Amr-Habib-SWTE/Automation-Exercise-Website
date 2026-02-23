package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//a[contains(text(),'Products')]") WebElement productsBtn;
    @FindBy(xpath = "(//a[@href='/products'])[1]") WebElement allProductsPage;
    @FindBy(id = "search_product") WebElement searchInput;
    @FindBy(id = "submit_search") WebElement searchBtn;
    @FindBy(xpath = "//h2[text()='Searched Products']") WebElement searchedProductsHeader;
    @FindBy(xpath = "//div[@class='productinfo text-center'][1]") WebElement firstProduct;

    // Locators specific to the complex hover steps in the source file
    @FindBy(xpath = "(//div)[41]") WebElement firstProductHoverArea;
    @FindBy(xpath = "(//a[contains(text(),'Add to cart')])[2]") WebElement firstProductAddToCart;
    @FindBy(xpath = "//button[@class='btn btn-success close-modal btn-block']") WebElement continueShoppingBtn;
    @FindBy(xpath = "(//a[contains(text(),'View Product')])[2]") WebElement secondProductView;
    @FindBy(id = "quantity") WebElement quantityInput;
    @FindBy(css = "button[type='button']") WebElement addToCartButton2;
    @FindBy(xpath = "(//u[normalize-space()='View Cart'])[1]") WebElement viewCartBtn;

    public ProductPage(WebDriver driver) { super(driver); }

    public void clickProducts() { click(productsBtn); }
    public boolean verifyAllProductsPage() { return isDisplayed(allProductsPage); }
    public void searchProduct(String name) {
        sendKeys(searchInput, name);
        click(searchBtn);
    }
    public boolean verifySearchedProducts() { return isDisplayed(searchedProductsHeader); }

    public void hoverAndAddFirstProduct() {
        Actions actions = new Actions(driver);
        actions.moveToElement(firstProductHoverArea).perform();
        click(firstProductAddToCart);
    }
    public void clickContinueShopping() { click(continueShoppingBtn); }
    public void viewSecondProduct() { click(secondProductView); }
    public void setQuantity(String qty) {
        quantityInput.clear();
        sendKeys(quantityInput, qty);
        click(addToCartButton2);
    }
    public void clickViewCart() { click(viewCartBtn); }
}