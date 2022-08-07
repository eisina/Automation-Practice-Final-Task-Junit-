package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WishListPage extends BasePage {

    @FindBy(xpath = "//input[@id = 'name']")
    private WebElement nameField;

    @FindBy(xpath = "//button[@id = 'submitWishlist']")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@id = 'block-history']")
    private WebElement wishlistTable;

    @FindBy(xpath = "//form[@id = 'form_wishlist']")
    private WebElement wishlistForm;

    @FindBy(xpath = "//a[@class='home']")
    private WebElement homeButton;

    @FindBy(xpath = "//i[@class='icon-remove']")
    private WebElement deleteButton;

    @FindBy(xpath = "(//td[count(//th[text()='Name']/preceding-sibling::th)+1])[1]")
    private WebElement nameCell;

    @FindBy(xpath = "(//td[count(//th[text()='Qty']/preceding-sibling::th)+1])[1]")
    private WebElement quantityCell;

    private Logger log = LogManager.getLogger(WishListPage.class);

    public WishListPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public boolean isWishlistTableDisplayed() {
        try {
            wishlistTable.isDisplayed();
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void waitWishlistTableNotDisplayed() {
        webDriverWait.until(ExpectedConditions.invisibilityOf(wishlistTable));
        log.info("Waited until Wishlist Table disappear");
    }

    public boolean isWishlistFormDisplayed() {
        webDriverWait.until(ExpectedConditions.visibilityOf(wishlistForm));
        log.info("Wishlist Page displayed");
        return wishlistForm.isDisplayed();
    }

    public HomePage clickHomeButton() {
        homeButton.click();
        log.info("Home Page clicked");
        return new HomePage(driver, webDriverWait);
    }

    public WishListPage deleteWishList() {
        deleteButton.click();
        log.info("Delete Button clicked");
        driver.switchTo().alert().accept();
        log.info("Alert accepted");
        return this;
    }

    public WishListPage enterName(String name) {
        nameField.sendKeys(name);
        log.info("Wishlist Name entered: " + name);
        return this;
    }

    public WishListPage clickSave() {
        saveButton.click();
        log.info("Save button clicked");
        return this;
    }

    public String getWishlistName() {
        log.info("Getting Wishlist Name");
        return nameCell.getText();
    }

    public String getProductsQuantity() {
        log.info("Getting Product Quantity");
        return quantityCell.getText();
    }
}
