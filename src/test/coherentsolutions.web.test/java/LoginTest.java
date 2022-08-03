import Pages.AccountCreationPage;
import Pages.AccountPage;
import Pages.LoginPage;
import TestDataTypes.User;
import Utils.TestDataUtils;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utils.ConstantUtils.*;

public class LoginTest extends BaseTest {

    @Test
    @Description("Checking logging with valid credentials")
    public void createAccountTest() {
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver, webDriverWait);
        Assert.assertTrue(loginPage.isLoginPageDisplay(), "Account Creation Page is not displayed");
        log.info("Account Creation Page opened");

        TestDataUtils testDataUtils = new TestDataUtils();
        User user = testDataUtils.getUserData();
        String userEmail = String.format(user.email, Math.random());
        loginPage.enterEmail(userEmail);
        AccountCreationPage accountCreationPage = loginPage.clickCreateAccount();
        Assert.assertTrue(accountCreationPage.isAccountCreationFormDisplay(), "Account Creation Form is not displayed");
        log.info("Account Creation Form displayed");

        accountCreationPage.enterFirstName(user.firstName);
        accountCreationPage.enterLastName(user.lastName);
        accountCreationPage.enterPassword(user.password);
        accountCreationPage.enterAddress(user.address);
        accountCreationPage.enterCity(user.city);
        accountCreationPage.chooseState(user.state);
        accountCreationPage.enterZipCode(user.zipCode);
        accountCreationPage.enterMobilePhone(user.mobilePhone);
        AccountPage accountPage = accountCreationPage.clickRegisterButton();
        Assert.assertTrue(accountPage.isPageLoaded(), "User is not created, Account page is not opened");
        log.info("Account Page is displayed");

        Assert.assertTrue(accountPage.getNameFromLink().equals(user.firstName + " " + user.lastName), "Firstname and LastName of the created user is not displayed");
        log.info("Firstname and LastName checked at the Account link");

        loginPage = accountPage.clickSignOutButton();
        loginPage.enterLoginEmail(userEmail);
        loginPage.enterLoginPassword(user.password);
        accountPage = loginPage.clickSignInButton();
        Assert.assertTrue(accountPage.isPageLoaded(), "User is not logged in, Account page is not opened");
        log.info("Account Page is displayed");
    }

    @Test
    @Description("Checking logging with valid credentials")
    public void loginTest() {
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver, webDriverWait);
        Assert.assertTrue(loginPage.isLoginPageDisplay(), "Account Creation Page is not displayed");
        log.info("Account Creation Page opened");

        loginPage.enterLoginEmail(SIGN_IN_EMAIL);
        loginPage.enterLoginPassword(SIGN_IN_PASSWORD);
        AccountPage accountPage = loginPage.clickSignInButton();
        Assert.assertTrue(accountPage.isPageLoaded(), "User is not logged in, Account page is not opened");
        log.info("Account Page is displayed");
        Assert.assertTrue(accountPage.getNameFromLink().equals("TestFirstName TestLastName"), "Firstname and LastName of the logged in user is not displayed");
        log.info("Firstname and LastName checked at the Account link");
    }
}
