package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(xpath = "//td[@class='cart_description']/p/a")
    private List<WebElement> productName;

    @FindBy(xpath = "//td[@data-title='Unit price']")
    private List<WebElement> productPrice;

    @FindBy(xpath = "//td[@data-title='Unit price']")
    private WebElement totalPrice;

    @FindBy(xpath = "//h1[@id='cart_title']")
    private WebElement cartTitle;

    private Logger log = LogManager.getLogger(CartPage.class);

    public CartPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public String getProductName(int productNumber) {
        log.info("Getting product name");
        return productName.get(productNumber).getText();
    }

    public Double getProductPrice(int productNumber) {
        log.info("Getting product price");
        String price = productPrice.get(productNumber).getText();
        return parsePrice(price);
    }

    public static Double parsePrice(String value) {
        return Double.valueOf((value.substring(1)));
    }

    public boolean isCartPageDisplay() {
        log.info("Checking the display of Cart page");
        return cartTitle.isDisplayed();
    }
}
