package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static Pages.CartPage.parsePrice;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[@title='View']")
    private List<WebElement> viewButton;

    @FindBy(xpath = "//a[@title='Add to cart']")
    private List<WebElement> addToCartButton;

    @FindBy(xpath = "//i[@class='icon-chevron-left left']")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//div[@class='product-container']")
    private List<WebElement> productContainer;

    @FindBy(xpath = "//ul[@id='homefeatured']")
    private WebElement productSection;

    @FindBy(xpath = "//div[@class='left-block']//div[@class='content_price']/span[@itemprop='price']")
    private List<WebElement> productPrice;

    @FindBy(xpath = "//div[@class='right-block']//a[@class='product-name']")
    private List<WebElement> productName;

    @FindBy(xpath = "//a[@title='View my shopping cart']")
    private WebElement cartButton;

    private Logger log = LogManager.getLogger(HomePage.class);

    Actions actions;

    public HomePage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
        actions = new Actions(driver);
    }

    public ProductPage clickMoreButton(int productNumber) {
        webDriverWait.until(ExpectedConditions.visibilityOf(productContainer.get(productNumber)));
        actions.moveToElement(productContainer.get(productNumber)).click().perform();
        viewButton.get(productNumber).click();
        log.info("View Button clicked");
        return new ProductPage(driver, webDriverWait);
    }

    public boolean isHomePageDisplayed() {
        webDriverWait.until(ExpectedConditions.visibilityOf(productSection));
        actions.moveToElement(productSection).perform();
        log.info("Scrolling element into view");
        return productSection.isDisplayed();
    }

    public ProductPage clickAddToCart(int productNumber) {
        actions.moveToElement(productContainer.get(productNumber)).click().perform();
        addToCartButton.get(productNumber).click();
        log.info("Clicking Add to Cart button");
        return new ProductPage(driver, webDriverWait);
    }

    public double getProductPrice(int productNumber) {
        actions.moveToElement(productContainer.get(productNumber)).click().perform();
        String price = productPrice.get(productNumber).getText();
        log.info("Getting Product Price");
        return parsePrice(price);
    }

    public String getProductName(int productNumber) {
        actions.moveToElement(productContainer.get(productNumber)).click().perform();
        log.info("Scroll element into view");
        return productName.get(productNumber).getText();
    }

    public HomePage clickContinueShopping() {
        webDriverWait.until(ExpectedConditions.visibilityOf(continueShoppingButton));
        continueShoppingButton.click();
        log.info("Click Continue Shopping button");
        webDriverWait.until(ExpectedConditions.invisibilityOfAllElements(continueShoppingButton));
        log.info("Wait until Element disappear");
        return this;
    }

    public CartPage clickCartButton() {
        cartButton.click();
        log.info("Clicking Cart button");
        return new CartPage(driver, webDriverWait);
    }
}
