package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage extends BasePage{

    @FindBy(xpath = "//td[@class='cart_description']/p/a")
    private List<WebElement> productName;

    @FindBy(xpath = "//td[@data-title='Unit price']")
    private List<WebElement> productPrice;

    @FindBy(xpath = "//td[@data-title='Unit price']")
    private WebElement totalPrice;


    public CartPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public String getProductName(int productNumber){
        return productName.get(productNumber).getText();
    }

    public Double getProductPrice(int productNumber){
        String price= productPrice.get(productNumber).getText();
        return parsePrice(price);
    }

    public double getTotalPrice(){
        String price= totalPrice.getText();
        return parsePrice(price);
    }

    public static Double parsePrice(String value) {
        return Double.valueOf((value.substring(1)));
    }

}
