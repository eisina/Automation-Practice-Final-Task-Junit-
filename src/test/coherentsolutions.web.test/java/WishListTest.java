import Pages.*;
import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.Test;

import static Utils.ConstantUtils.*;

public class WishListTest extends BaseTest {

    @Test
    @Description("Checking adding auto-created Wishlist")
    public void addAutoWishlistTest() {
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver, webDriverWait);
        Assert.assertTrue("Account Creation Page is not displayed", loginPage.isLoginPageDisplay());
        log.info("Account Creation Page opened");

        loginPage.enterLoginEmail(SIGN_IN_EMAIL);
        loginPage.enterLoginPassword("TestPassword");
        AccountPage accountPage = loginPage.clickSignInButton();
        Assert.assertTrue("User is not logged in, Account page is not opened", accountPage.isPageLoaded());
        log.info("Account Page opened");

        WishListPage wishListPage = accountPage.clickWishListButton();
        Assert.assertTrue("Wishlist Page is not displayed", wishListPage.isWishlistFormDisplayed());
        log.info("Wishlist Page opened");

        Assert.assertFalse("Wishlist Present", wishListPage.isWishlistTableDisplayed());
        log.info("Wishlist not present initially");

        HomePage homePage = wishListPage.clickHomeButton();
        Assert.assertTrue("Home Page is not displayed", homePage.isHomePageDisplayed());
        log.info("Home Page opened");

        ProductPage productPage = homePage.clickMoreButton(0);
        Assert.assertTrue("Product Page is not displayed", productPage.isProductPageDisplayed());
        log.info("Product Page opened");

        productPage.clickWishlistButton();
        Assert.assertEquals("Success message is not displayed", productPage.getSuccessMessage(), "Added to your wishlist.");
        log.info("Wishlist Button clicked");

        driver.get(WISHLIST_URL);
        Assert.assertTrue("Wishlist is not displayed", wishListPage.isWishlistFormDisplayed());
        log.info("Wishlist is displayed");

        wishListPage.deleteWishList();
        Assert.assertFalse("Wishlist Present", wishListPage.isWishlistTableDisplayed());
        log.info("Wishlist deleted");
    }

    @Test
    @Description("Checking adding Wishlist and product to it")
    public void createWishlistTest() {
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver, webDriverWait);
        Assert.assertTrue("Account Creation Page is not displayed", loginPage.isLoginPageDisplay());
        log.info("Account Creation Page opened");

        loginPage.enterLoginEmail(SIGN_IN_EMAIL);
        loginPage.enterLoginPassword("TestPassword");
        AccountPage accountPage = loginPage.clickSignInButton();
        Assert.assertTrue("User is not logged in, Account page is not opened", accountPage.isPageLoaded());
        log.info("Account Page opened");

        WishListPage wishListPage = accountPage.clickWishListButton();
        Assert.assertTrue("Wishlist Page is not displayed", wishListPage.isWishlistFormDisplayed());
        log.info("Wishlist Page opened");

        wishListPage.enterName("Test Wishlist");
        wishListPage.clickSave();
        Assert.assertTrue("Wishlist Present", wishListPage.isWishlistTableDisplayed());
        log.info("Wishlist table displayed");

        Assert.assertEquals("Wrong name", wishListPage.getWishlistName(), "Test Wishlist");
        Assert.assertEquals("Wrong quantity", wishListPage.getProductsQuantity(), "0");


        HomePage homePage = wishListPage.clickHomeButton();
        Assert.assertTrue("Home Page is not displayed", homePage.isHomePageDisplayed());
        log.info("Home Page opened");

        ProductPage productPage = homePage.clickMoreButton(0);
        Assert.assertTrue("Product Page is not displayed", productPage.isProductPageDisplayed());
        log.info("Product Page opened");

        productPage.clickWishlistButton();
        Assert.assertEquals("Success message is not displayed", productPage.getSuccessMessage(), "Added to your wishlist.");
        log.info("Wishlist Button clicked");

        driver.get(WISHLIST_URL);
        Assert.assertTrue("Wishlist is not displayed", wishListPage.isWishlistFormDisplayed());
        log.info("Wishlist is displayed");
        Assert.assertEquals("Wrong quantity", wishListPage.getProductsQuantity(), "1");

        wishListPage.deleteWishList();
        Assert.assertFalse("Wishlist Present", wishListPage.isWishlistTableDisplayed());
        log.info("Wishlist deleted");
    }
}
