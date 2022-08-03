package Pages;

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
    }

    public boolean isWishlistFormDisplayed() {
        webDriverWait.until(ExpectedConditions.visibilityOf(wishlistForm));
        return wishlistForm.isDisplayed();
    }

    public HomePage clickHomeButton() {
        homeButton.click();
        return new HomePage(driver, webDriverWait);
    }

    public WishListPage deleteWishList() {
        deleteButton.click();
        driver.switchTo().alert().accept();
        return this;
    }

    public WishListPage enterName(String name) {
        nameField.sendKeys(name);
        return this;
    }

    public WishListPage clickSave() {
        saveButton.click();
        return this;
    }

    public String getWishlistName() {
        return nameCell.getText();
    }

    public String getProductsQuantity() {
        return quantityCell.getText();
    }
}
