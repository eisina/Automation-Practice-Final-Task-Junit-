package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage{

    @FindBy(xpath = ".product_list")
    private WebElement productSection;

    @FindBy(xpath = "//a[@id='wishlist_button']")
    private WebElement wishListButton;

    @FindBy(xpath = "//form[@id ='buy_block']")
    private WebElement productBox;

    @FindBy(xpath = "//p[@class='fancybox-error']")
    private WebElement successMessage;

    public ProductPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public ProductPage clickWishlistButton() {
        webDriverWait.until(ExpectedConditions.visibilityOf(wishListButton));
        wishListButton.click();
        return this;
    }

    public boolean isProductPageDisplayed(){
        webDriverWait.until(ExpectedConditions.visibilityOf(productBox));
        return productBox.isDisplayed();
    }

    public String getSuccessMessage() {
        return successMessage.getText();
    }
}
