package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage extends BasePage{

    @FindBy(xpath = "//a[@title='View my customer account']")
    private WebElement accountLink;

    @FindBy(xpath = ".info-account")
    private WebElement accountPage;

    @FindBy(xpath = "//a[@title='My wishlists']")
    private WebElement myWishlistButton;

    @FindBy(xpath = "//a[@class='home']")
    private WebElement homeButton;

    public AccountPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public String getNameFromLink(){
        return accountLink.getText();
    }

    public boolean isPageLoaded(){
        webDriverWait.until(ExpectedConditions.visibilityOf(accountLink));
        return accountLink.isDisplayed();
    }

    public WishListPage clickWishListButton(){
        myWishlistButton.click();
        return new WishListPage(driver, webDriverWait);
    }


    public HomePage clickHomeButton(){
        homeButton.click();
        return new HomePage(driver, webDriverWait);
    }

}
