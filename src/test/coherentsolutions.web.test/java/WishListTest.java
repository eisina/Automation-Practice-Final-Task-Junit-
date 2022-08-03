import Pages.*;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utils.ConstantUtils.*;

public class WishListTest extends BaseTest {

    @Test
    @Description("Checking adding auto-created Wishlist")
    public void addAutoWishlistTest() {
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver, webDriverWait);
        Assert.assertTrue(loginPage.isLoginPageDisplay(), "Account Creation Page is not displayed");
        log.info("Account Creation Page opened");

        loginPage.enterLoginEmail(SIGN_IN_EMAIL);
        loginPage.enterLoginPassword("TestPassword");
        AccountPage accountPage = loginPage.clickSignInButton();
        Assert.assertTrue(accountPage.isPageLoaded(), "User is not logged in, Account page is not opened");
        log.info("Account Page opened");

        WishListPage wishListPage = accountPage.clickWishListButton();
        Assert.assertTrue(wishListPage.isWishlistFormDisplayed(), "Wishlist Page is not displayed");
        log.info("Wishlist Page opened");

        Assert.assertFalse(wishListPage.isWishlistTableDisplayed(), "Wishlist Present");
        log.info("Wishlist not present initially");

        HomePage homePage = wishListPage.clickHomeButton();
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home Page is not displayed");
        log.info("Home Page opened");

        ProductPage productPage = homePage.clickMoreButton(0);
        Assert.assertTrue(productPage.isProductPageDisplayed(), "Product Page is not displayed");
        log.info("Product Page opened");

        productPage.clickWishlistButton();
        Assert.assertEquals(productPage.getSuccessMessage(), "Added to your wishlist.", "Success message is not displayed");
        log.info("Wishlist Button clicked");

        driver.get(WISHLIST_URL);
        Assert.assertTrue(wishListPage.isWishlistFormDisplayed(), "Wishlist is not displayed");
        log.info("Wishlist is displayed");

        wishListPage.deleteWishList();
        wishListPage.waitWishlistTableNotDisplayed();
        Assert.assertFalse(wishListPage.isWishlistTableDisplayed(), "Wishlist Present");
        log.info("Wishlist deleted");
    }

    @Test
    @Description("Checking adding Wishlist and product to it")
    public void createWishlistTest() {
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver, webDriverWait);
        Assert.assertTrue(loginPage.isLoginPageDisplay(), "Account Creation Page is not displayed");
        log.info("Account Creation Page opened");

        loginPage.enterLoginEmail(SIGN_IN_EMAIL);
        loginPage.enterLoginPassword("TestPassword");
        AccountPage accountPage = loginPage.clickSignInButton();
        Assert.assertTrue(accountPage.isPageLoaded(), "User is not logged in, Account page is not opened");
        log.info("Account Page opened");

        WishListPage wishListPage = accountPage.clickWishListButton();
        Assert.assertTrue(wishListPage.isWishlistFormDisplayed(), "Wishlist Page is not displayed");
        log.info("Wishlist Page opened");

        wishListPage.enterName("Test Wishlist");
        wishListPage.clickSave();
        Assert.assertTrue(wishListPage.isWishlistTableDisplayed(), "Wishlist Present");
        log.info("Wishlist table displayed");

        Assert.assertEquals(wishListPage.getWishlistName(), "Test Wishlist", "Wrong name");
        Assert.assertEquals(wishListPage.getProductsQuantity(), "0", "Wrong quantity");


        HomePage homePage = wishListPage.clickHomeButton();
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home Page is not displayed");
        log.info("Home Page opened");

        ProductPage productPage = homePage.clickMoreButton(0);
        Assert.assertTrue(productPage.isProductPageDisplayed(), "Product Page is not displayed");
        log.info("Product Page opened");

        productPage.clickWishlistButton();
        Assert.assertEquals(productPage.getSuccessMessage(), "Added to your wishlist.", "Success message is not displayed");
        log.info("Wishlist Button clicked");

        driver.get(WISHLIST_URL);
        Assert.assertTrue(wishListPage.isWishlistFormDisplayed(), "Wishlist is not displayed");
        log.info("Wishlist is displayed");
        Assert.assertEquals(wishListPage.getProductsQuantity(), "1", "Wrong quantity");

        wishListPage.deleteWishList();
        wishListPage.waitWishlistTableNotDisplayed();
        Assert.assertFalse(wishListPage.isWishlistTableDisplayed(), "Wishlist Present");
        log.info("Wishlist deleted");
    }
}
