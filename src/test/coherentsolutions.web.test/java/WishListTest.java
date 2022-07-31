import Pages.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Utils.ConstantUtils.*;
import static org.junit.Assert.*;

public class WishListTest extends BaseTest {

    @Test
    @DisplayName("Checking adding auto-created Wishlist")
    public void addAutoWishlistTest() {
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver, webDriverWait);
        assertTrue("Account Creation Page is not displayed", loginPage.isLoginPageDisplay());
        log.info("Account Creation Page opened");

        loginPage.enterLoginEmail(SIGN_IN_EMAIL);
        loginPage.enterLoginPassword("TestPassword");
        AccountPage accountPage = loginPage.clickSignInButton();
        assertTrue("User is not logged in, Account page is not opened", accountPage.isPageLoaded());
        log.info("Account Page opened");

        WishListPage wishListPage = accountPage.clickWishListButton();
        assertTrue("Wishlist Page is not displayed", wishListPage.isWishlistFormDisplayed());
        log.info("Wishlist Page opened");

        assertFalse("Wishlist Present", wishListPage.isWishlistTableDisplayed());
        log.info("Wishlist not present initially");

        HomePage homePage = wishListPage.clickHomeButton();
        assertTrue("Home Page is not displayed", homePage.isHomePageDisplayed());
        log.info("Home Page opened");

        ProductPage productPage = homePage.clickMoreButton(0);
        assertTrue("Product Page is not displayed", productPage.isProductPageDisplayed());
        log.info("Product Page opened");

        productPage.clickWishlistButton();
        assertEquals("Success message is not displayed", productPage.getSuccessMessage(), "Added to your wishlist.");
        log.info("Wishlist Button clicked");

        driver.get(WISHLIST_URL);
        assertTrue("Wishlist is not displayed", wishListPage.isWishlistFormDisplayed());
        log.info("Wishlist is displayed");

        wishListPage.deleteWishList();
        assertFalse("Wishlist Present", wishListPage.isWishlistTableDisplayed());
        log.info("Wishlist deleted");
    }

    @Test
    @DisplayName("Checking adding Wishlist and product to it")
    public void createWishlistTest() {
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver, webDriverWait);
        assertTrue("Account Creation Page is not displayed", loginPage.isLoginPageDisplay());
        log.info("Account Creation Page opened");

        loginPage.enterLoginEmail(SIGN_IN_EMAIL);
        loginPage.enterLoginPassword("TestPassword");
        AccountPage accountPage = loginPage.clickSignInButton();
        assertTrue("User is not logged in, Account page is not opened", accountPage.isPageLoaded());
        log.info("Account Page opened");

        WishListPage wishListPage = accountPage.clickWishListButton();
        assertTrue("Wishlist Page is not displayed", wishListPage.isWishlistFormDisplayed());
        log.info("Wishlist Page opened");

        wishListPage.enterName("Test Wishlist");
        wishListPage.clickSave();
        assertTrue("Wishlist Present", wishListPage.isWishlistTableDisplayed());
        log.info("Wishlist table displayed");

        assertEquals("Wrong name", wishListPage.getWishlistName(), "Test Wishlist");
        assertEquals("Wrong quantity", wishListPage.getProductsQuantity(), "0");


        HomePage homePage = wishListPage.clickHomeButton();
        assertTrue("Home Page is not displayed", homePage.isHomePageDisplayed());
        log.info("Home Page opened");

        ProductPage productPage = homePage.clickMoreButton(0);
        assertTrue("Product Page is not displayed", productPage.isProductPageDisplayed());
        log.info("Product Page opened");

        productPage.clickWishlistButton();
        assertEquals("Success message is not displayed", productPage.getSuccessMessage(), "Added to your wishlist.");
        log.info("Wishlist Button clicked");

        driver.get(WISHLIST_URL);
        assertTrue("Wishlist is not displayed", wishListPage.isWishlistFormDisplayed());
        log.info("Wishlist is displayed");
        assertEquals("Wrong quantity", wishListPage.getProductsQuantity(), "1");

        wishListPage.deleteWishList();
        assertFalse("Wishlist Present", wishListPage.isWishlistTableDisplayed());
        log.info("Wishlist deleted");
    }
}
