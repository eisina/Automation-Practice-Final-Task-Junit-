import Pages.*;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utils.ConstantUtils.*;

public class CartTest extends BaseTest {

    @Test
    @Description("Checking adding product to cart")
    public void addProductsTest(){
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver, webDriverWait);
        Assert.assertTrue(loginPage.isLoginPageDisplay(), "Account Creation Page is not displayed");
        log.info("Account Creation Page opened");

        loginPage.enterLoginEmail(SIGN_IN_EMAIL);
        loginPage.enterLoginPassword("TestPassword");
        AccountPage accountPage = loginPage.clickSignInButton();
        Assert.assertTrue(accountPage.isPageLoaded(), "User is not logged in, Account page is not opened");
        log.info("Account Page opened");

        HomePage homePage = accountPage.clickHomeButton();
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home Page is not displayed");
        log.info("Home Page opened");

        double firstProductPrice = homePage.getProductPrice(0);
        String firstProductName = homePage.getProductName(0);
        log.info("Information on first product got");
        homePage.clickAddToCart(0);
        homePage.clickContinueShopping();
        log.info("First Product Added to cart");

        double secondProductPrice = homePage.getProductPrice(1);
        String secondProductName = homePage.getProductName(1);
        log.info("Information on second product got");
        homePage.clickAddToCart(1);
        homePage.clickContinueShopping();
        log.info("Second Product Added to cart");

        double thirdProductPrice = homePage.getProductPrice(2);
        String thirdProductName = homePage.getProductName(2);
        homePage.clickAddToCart(2);
        homePage.clickContinueShopping();
        log.info("Information on third product got");
        double expectedTotalPrice = firstProductPrice + secondProductPrice + thirdProductPrice;

        CartPage cartPage = homePage.clickCartButton();
        Assert.assertTrue(cartPage.isCartPageDisplay(), "Cart Page is not displayed");
        log.info("Cart opened");

        String actualFirstProductName = cartPage.getProductName(0);
        double actualFirstPrice = cartPage.getProductPrice(0);
        String actualSecondProductName = cartPage.getProductName(1);
        double actualSecondPrice = cartPage.getProductPrice(1);
        String actualThirdProductName = cartPage.getProductName(2);
        double actualThirdPrice = cartPage.getProductPrice(2);
        log.info("Information in Cart products got");

        double actualTotalPrice = actualFirstPrice + actualSecondPrice + actualThirdPrice;
        Assert.assertEquals(expectedTotalPrice, actualTotalPrice);
        log.info("Total price compared");

        Assert.assertEquals(actualFirstProductName, firstProductName, "Product name wrong");
        Assert.assertEquals(actualSecondProductName, secondProductName, "Product name wrong");
        Assert.assertEquals(actualThirdProductName, thirdProductName, "Product name wrong");
        log.info("Name compared");
    }
}
