package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {

    @FindBy(xpath = ".product_list")
    private WebElement productSection;

    @FindBy(xpath = "//a[@id='wishlist_button']")
    private WebElement wishListButton;

    @FindBy(xpath = "//form[@id ='buy_block']")
    private WebElement productBox;

    @FindBy(xpath = "//p[@class='fancybox-error']")
    private WebElement successMessage;

    private Logger log = LogManager.getLogger(ProductPage.class);

    public ProductPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public ProductPage clickWishlistButton() {
        webDriverWait.until(ExpectedConditions.visibilityOf(wishListButton));
        wishListButton.click();
        log.info("WishList button clicked");
        return this;
    }

    public boolean isProductPageDisplayed() {
        webDriverWait.until(ExpectedConditions.visibilityOf(productBox));
        log.info("Product Page loaded");
        return productBox.isDisplayed();
    }

    public String getSuccessMessage() {
        log.info("Getting success message");
        return successMessage.getText();
    }
}
