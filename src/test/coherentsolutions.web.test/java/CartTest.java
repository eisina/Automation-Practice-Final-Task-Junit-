import Pages.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Utils.ConstantUtils.*;
import static org.junit.Assert.*;

public class CartTest extends BaseTest{

    @Test
    @DisplayName("Checking adding product to cart")
    public void addProductsTest() {
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

        String firstProductPrice = homePage.getProductPrice(0);
        String firstProductName = homePage.getProductName(0);

        homePage.clickAddToCart(0);
        homePage.clickContinueShopping();
    }
}
