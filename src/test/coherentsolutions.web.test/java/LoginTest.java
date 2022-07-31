import Pages.AccountCreationPage;
import Pages.AccountPage;
import Pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Utils.ConstantUtils.LOGIN_URL;
import static Utils.ConstantUtils.SIGN_IN_EMAIL;
import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Checking creating account")
    public void createAccountTest() throws Exception {
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver, webDriverWait);
        assertTrue("Account Creation Page is not displayed", loginPage.isLoginPageDisplay());
        log.info("Account Creation Page opened");

        loginPage.enterEmail("emailtesttik@mailik.ru");
        AccountCreationPage accountCreationPage = loginPage.clickCreateAccount();
        assertTrue("Account Creation Form is not displayed", accountCreationPage.isAccountCreationFormDisplay());
        log.info("Account Creation Form displayed");

        accountCreationPage.enterFirstName("TestFirstName");
        accountCreationPage.enterLastName("TestLastName");
        accountCreationPage.enterPassword("TestPassword");
        accountCreationPage.enterAddress("TestAddress");
        accountCreationPage.enterCity("TestCity");
        accountCreationPage.chooseState("Alabama");
        accountCreationPage.enterZipCode("00000");
        accountCreationPage.enterMobilePhone("01234567");
        AccountPage accountPage = accountCreationPage.clickRegisterButton();
        assertTrue("User is not created, Account page is not opened", accountPage.isPageLoaded());
        log.info("Account Page is displayed");
        assertTrue("Firstname and LastName of the created user is not displayed", accountPage.getNameFromLink().equals("TestFirstName TestLastName"));
        log.info("Firstname and LastName checked at the Account link");
    }

    @Test
    @DisplayName("Checking logging with valid credentials")
    public void loginTest() {
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver, webDriverWait);
        assertTrue("Account Creation Page is not displayed", loginPage.isLoginPageDisplay());
        log.info("Account Creation Page opened");

        loginPage.enterLoginEmail(SIGN_IN_EMAIL);
        loginPage.enterLoginPassword("TestPassword");
        AccountPage accountPage = loginPage.clickSignInButton();
        assertTrue("User is not logged in, Account page is not opened", accountPage.isPageLoaded());
        log.info("Account Page is displayed");
        assertTrue("Firstname and LastName of the logged in user is not displayed", accountPage.getNameFromLink().equals("TestFirstName TestLastName"));
        log.info("Firstname and LastName checked at the Account link");
    }
}
