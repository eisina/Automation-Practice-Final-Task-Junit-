package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage extends BasePage{

    @FindBy(xpath = "//a[@title='View']")
    private List<WebElement>  viewButton;

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

    Actions actions;

    public HomePage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
        actions = new Actions(driver);
    }

    public ProductPage clickMoreButton(int productNumber) {
        actions.moveToElement(productContainer.get(productNumber)).click().perform();
        viewButton.get(productNumber).click();
        return new ProductPage(driver, webDriverWait);
    }

    public boolean isHomePageDisplayed(){
        webDriverWait.until(ExpectedConditions.visibilityOf(productSection));
        actions.moveToElement(productSection).perform();
        return productSection.isDisplayed();
    }

    public ProductPage clickAddToCart(int productNumber) {
        actions.moveToElement(productContainer.get(productNumber)).click().perform();
        addToCartButton.get(productNumber).click();
        return new ProductPage(driver, webDriverWait);
    }

    public String getProductPrice(int productNumber){
        actions.moveToElement(productContainer.get(productNumber)).click().perform();
       return productPrice.get(productNumber).getText();
    }

    public String getProductName(int productNumber){
        actions.moveToElement(productContainer.get(productNumber)).click().perform();
       return productName.get(productNumber).getText();
    }

    public HomePage clickContinueShopping() {
        webDriverWait.until(ExpectedConditions.visibilityOf(continueShoppingButton));
        continueShoppingButton.click();
        return this;
    }

}
