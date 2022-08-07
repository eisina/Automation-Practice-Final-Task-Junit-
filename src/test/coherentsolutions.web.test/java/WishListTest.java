import Pages.*;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class WishListTest extends BaseTest {

    @Test
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
        Assert.assertEquals(productPage.getSuccessMessage(), "Added to your wishlist.", "Success message is not displayed");

        driver.get(propertiesUtils.getProperty("wishlistIrl"));
        Assert.assertTrue(wishListPage.isWishlistFormDisplayed(), "Wishlist is not displayed");

        wishListPage.deleteWishList();
        wishListPage.waitWishlistTableNotDisplayed();
        Assert.assertFalse(wishListPage.isWishlistTableDisplayed(), "Wishlist Present");
    }

    @Test
    @Description("Checking adding Wishlist and product to it")
    public void createWishlistTest() throws IOException {
        driver.get(propertiesUtils.getProperty("loginUrl"));
        LoginPage loginPage = new LoginPage(driver, webDriverWait);
        Assert.assertTrue(loginPage.isLoginPageDisplay(), "Account Creation Page is not displayed");

        AccountPage accountPage = loginPage.login(propertiesUtils.getProperty("email"), propertiesUtils.getProperty("password"));
        Assert.assertTrue(accountPage.isPageLoaded(), "User is not logged in, Account page is not opened");

        WishListPage wishListPage = accountPage.clickWishListButton();
        Assert.assertTrue(wishListPage.isWishlistFormDisplayed(), "Wishlist Page is not displayed");

        wishListPage.enterName("Test Wishlist");
        wishListPage.clickSave();
        Assert.assertTrue(wishListPage.isWishlistTableDisplayed(), "Wishlist Present");

        Assert.assertEquals(wishListPage.getWishlistName(), "Test Wishlist", "Wrong name");
        Assert.assertEquals(wishListPage.getProductsQuantity(), "0", "Wrong quantity");

        HomePage homePage = wishListPage.clickHomeButton();
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home Page is not displayed");

        ProductPage productPage = homePage.clickMoreButton(0);
        Assert.assertTrue(productPage.isProductPageDisplayed(), "Product Page is not displayed");

        productPage.clickWishlistButton();
        Assert.assertEquals(productPage.getSuccessMessage(), "Added to your wishlist.", "Success message is not displayed");

        driver.get(propertiesUtils.getProperty("wishlistUrl"));
        Assert.assertTrue(wishListPage.isWishlistFormDisplayed(), "Wishlist is not displayed");
        Assert.assertEquals(wishListPage.getProductsQuantity(), "1", "Wrong quantity");

        wishListPage.deleteWishList();
        wishListPage.waitWishlistTableNotDisplayed();
        Assert.assertFalse(wishListPage.isWishlistTableDisplayed(), "Wishlist Present");
    }
}
