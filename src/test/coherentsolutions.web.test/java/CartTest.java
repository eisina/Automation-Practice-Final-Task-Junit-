import Pages.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Utils.ConstantUtils.*;
import static org.junit.Assert.*;

public class CartTest extends BaseTest{

    @Test
    @DisplayName("Checking adding product to cart")
    public void addProductsTest() throws InterruptedException {
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver, webDriverWait);
        assertTrue("Account Creation Page is not displayed", loginPage.isLoginPageDisplay());
        log.info("Account Creation Page opened");

        loginPage.enterLoginEmail(SIGN_IN_EMAIL);
        loginPage.enterLoginPassword("TestPassword");
        AccountPage accountPage = loginPage.clickSignInButton();
        assertTrue("User is not logged in, Account page is not opened", accountPage.isPageLoaded());
        log.info("Account Page opened");

        HomePage homePage = accountPage.clickHomeButton();
        assertTrue("Home Page is not displayed", homePage.isHomePageDisplayed());
        log.info("Home Page opened");

        double firstProductPrice = homePage.getProductPrice(0);
        String firstProductName = homePage.getProductName(0);
        log.info("Information on first product got");
        homePage.clickAddToCart(0);
        homePage.clickContinueShopping();
        log.info("First Product Added to cart");
       // Thread.sleep(2000);

        double secondProductPrice = homePage.getProductPrice(1);
        String secondProductName = homePage.getProductName(1);
        log.info("Information on second product got");
        homePage.clickAddToCart(1);
        homePage.clickContinueShopping();
        log.info("Second Product Added to cart");

    //    Thread.sleep(2000);
        double thirdProductPrice = homePage.getProductPrice(2);
        String thirdProductName = homePage.getProductName(2);
        homePage.clickAddToCart(2);
        homePage.clickContinueShopping();
        log.info("Information on third product got");

        double expectedTotalPrice = firstProductPrice + secondProductPrice + thirdProductPrice;

        CartPage cartPage = homePage.clickCartButton();
        String actualFirstProductName = cartPage.getProductName(0);
        double actualFirstPrice = cartPage.getProductPrice(0);
        String actualSecondProductName = cartPage.getProductName(1);
        double actualSecondPrice = cartPage.getProductPrice(1);
        String actualThirdProductName = cartPage.getProductName(2);
        double actualThirdPrice = cartPage.getProductPrice(2);
        log.info("Information in Cart products got");

        double actualTotalPrice = actualFirstPrice + actualSecondPrice + actualThirdPrice;
        assertEquals( expectedTotalPrice, actualTotalPrice, 1e-8);
        log.info("Total price compared");

        assertEquals("Product name wrong",firstProductName , actualFirstProductName);
        assertEquals("Product name wrong",secondProductName , actualSecondProductName);
        assertEquals("Product name wrong",thirdProductName , actualThirdProductName);
        log.info("Name compared");
    }
}
