package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage extends BasePage {

    @FindBy(xpath = "//a[@title='View my customer account']")
    private WebElement accountLink;

    @FindBy(xpath = ".info-account")
    private WebElement accountPage;

    @FindBy(xpath = "//a[@title='My wishlists']")
    private WebElement myWishlistButton;

    @FindBy(xpath = "//a[@class='home']")
    private WebElement homeButton;

    @FindBy(xpath = "//a[@class='logout']")
    private WebElement sigOutButton;

    private Logger log = LogManager.getLogger(AccountCreationPage.class);

    public AccountPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public String getNameFromLink() {
        log.info("Getting First Name and Last Name from link");
        return accountLink.getText();
    }

    public boolean isPageLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(accountLink));
        log.info("Account Page loaded");
        return accountLink.isDisplayed();
    }

    public WishListPage clickWishListButton() {
        myWishlistButton.click();
        log.info("WishList Button clicked");
        return new WishListPage(driver, webDriverWait);
    }

    public HomePage clickHomeButton() {
        homeButton.click();
        log.info("Home Button clicked");
        return new HomePage(driver, webDriverWait);
    }

    public LoginPage clickSignOutButton() {
        sigOutButton.click();
        log.info("Sign Out Button clicked");
        return new LoginPage(driver, webDriverWait);
    }
}
