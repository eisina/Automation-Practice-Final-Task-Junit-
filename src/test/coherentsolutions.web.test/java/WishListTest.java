import Pages.*;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static Pages.WishListPage.WISHLIST_SUCCESS_MESSAGE;


public class WishListTest extends BaseTest {

    @Test
    @Feature("WishList Test")
    @Description("Checking adding auto-created Wishlist")
    public void addAutoWishlistTest() throws IOException {
        driver.get(propertiesUtils.getProperty("loginUrl"));
        LoginPage loginPage = new LoginPage(driver, webDriverWait);
        Assert.assertTrue(loginPage.isLoginPageDisplay(), "Account Creation Page is not displayed");

        AccountPage accountPage = loginPage.login(propertiesUtils.getProperty("email"), propertiesUtils.getProperty("password"));
        Assert.assertTrue(accountPage.isPageLoaded(), "User is not logged in, Account page is not opened");

        WishListPage wishListPage = accountPage.clickWishListButton();
        Assert.assertTrue(wishListPage.isWishlistFormDisplayed(), "Wishlist Page is not displayed");

        Assert.assertFalse(wishListPage.isWishlistTableDisplayed(), "Wishlist Present");

        HomePage homePage = wishListPage.clickHomeButton();
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home Page is not displayed");

        ProductPage productPage = homePage.clickMoreButton(0);
        Assert.assertTrue(productPage.isProductPageDisplayed(), "Product Page is not displayed");

        productPage.clickWishlistButton();
        Assert.assertEquals(productPage.getSuccessMessage(), WISHLIST_SUCCESS_MESSAGE, "Success message is not displayed");

        driver.get(propertiesUtils.getProperty("wishlistUrl"));
        Assert.assertTrue(wishListPage.isWishlistFormDisplayed(), "Wishlist is not displayed");

        wishListPage.deleteWishList();
        wishListPage.waitWishlistTableNotDisplayed();
        Assert.assertFalse(wishListPage.isWishlistTableDisplayed(), "Wishlist Present");
    }

    @Test
    @Feature("WishList Test")
    @Description("Checking adding Wishlist and product to it")
    public void createWishlistTest() throws IOException {
        driver.get(propertiesUtils.getProperty("loginUrl"));
        LoginPage loginPage = new LoginPage(driver, webDriverWait);
        Assert.assertTrue(loginPage.isLoginPageDisplay(), "Account Creation Page is not displayed");

        AccountPage accountPage = loginPage.login(propertiesUtils.getProperty("email"), propertiesUtils.getProperty("password"));
        Assert.assertTrue(accountPage.isPageLoaded(), "User is not logged in, Account page is not opened");

        WishListPage wishListPage = accountPage.clickWishListButton();
        Assert.assertTrue(wishListPage.isWishlistFormDisplayed(), "Wishlist Page is not displayed");

        String wishlistName = "Test Wishlist";
        wishListPage.enterName(wishlistName);
        wishListPage.clickSave();
        Assert.assertTrue(wishListPage.isWishlistTableDisplayed(), "Wishlist Present");

        Assert.assertEquals(wishListPage.getWishlistName(), wishlistName, "Wrong name");
        Assert.assertEquals(wishListPage.getProductsQuantity(), "0", "Wrong quantity");

        HomePage homePage = wishListPage.clickHomeButton();
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home Page is not displayed");

        ProductPage productPage = homePage.clickMoreButton(0);
        Assert.assertTrue(productPage.isProductPageDisplayed(), "Product Page is not displayed");

        productPage.clickWishlistButton();
        Assert.assertEquals(productPage.getSuccessMessage(), WISHLIST_SUCCESS_MESSAGE, "Success message is not displayed");

        driver.get(propertiesUtils.getProperty("wishlistUrl"));
        Assert.assertTrue(wishListPage.isWishlistFormDisplayed(), "Wishlist is not displayed");
        Assert.assertEquals(wishListPage.getProductsQuantity(), "1", "Wrong quantity");

        wishListPage.deleteWishList();
        wishListPage.waitWishlistTableNotDisplayed();
        Assert.assertFalse(wishListPage.isWishlistTableDisplayed(), "Wishlist Present");
    }
}
