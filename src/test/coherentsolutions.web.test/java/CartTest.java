import Pages.*;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CartTest extends BaseTest {

    @Test
    @Description("Checking adding product to cart")
    public void addProductsTest() throws IOException {
        driver.get(propertiesUtils.getProperty("loginUrl"));
        LoginPage loginPage = new LoginPage(driver, webDriverWait);
        Assert.assertTrue(loginPage.isLoginPageDisplay(), "Account Creation Page is not displayed");

        AccountPage accountPage = loginPage.login(propertiesUtils.getProperty("email"), propertiesUtils.getProperty("password"));
        Assert.assertTrue(accountPage.isPageLoaded(), "User is not logged in, Account page is not opened");

        HomePage homePage = accountPage.clickHomeButton();
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home Page is not displayed");

        double firstProductPrice = homePage.getProductPrice(0);
        String firstProductName = homePage.getProductName(0);
        homePage.clickAddToCart(0);
        homePage.clickContinueShopping();

        double secondProductPrice = homePage.getProductPrice(1);
        String secondProductName = homePage.getProductName(1);
        homePage.clickAddToCart(1);
        homePage.clickContinueShopping();

        double thirdProductPrice = homePage.getProductPrice(2);
        String thirdProductName = homePage.getProductName(2);
        homePage.clickAddToCart(2);
        homePage.clickContinueShopping();
        double expectedTotalPrice = firstProductPrice + secondProductPrice + thirdProductPrice;

        CartPage cartPage = homePage.clickCartButton();
        Assert.assertTrue(cartPage.isCartPageDisplay(), "Cart Page is not displayed");

        String actualFirstProductName = cartPage.getProductName(0);
        double actualFirstPrice = cartPage.getProductPrice(0);
        String actualSecondProductName = cartPage.getProductName(1);
        double actualSecondPrice = cartPage.getProductPrice(1);
        String actualThirdProductName = cartPage.getProductName(2);
        double actualThirdPrice = cartPage.getProductPrice(2);

        double actualTotalPrice = actualFirstPrice + actualSecondPrice + actualThirdPrice;
        Assert.assertEquals(expectedTotalPrice, actualTotalPrice);

        Assert.assertEquals(actualFirstProductName, firstProductName, "Product name wrong");
        Assert.assertEquals(actualSecondProductName, secondProductName, "Product name wrong");
        Assert.assertEquals(actualThirdProductName, thirdProductName, "Product name wrong");
    }
}
