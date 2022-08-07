import Pages.AccountCreationPage;
import Pages.AccountPage;
import Pages.LoginPage;
import TestDataTypes.User;
import Utils.TestDataUtils;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class LoginTest extends BaseTest {

    @Test
    @Description("Checking logging with valid credentials")
    public void createAccountTest() throws IOException {
        driver.get(propertiesUtils.getProperty("loginUrl"));
        LoginPage loginPage = new LoginPage(driver, webDriverWait);
        Assert.assertTrue(loginPage.isLoginPageDisplay(), "Account Creation Page is not displayed");

        TestDataUtils testDataUtils = new TestDataUtils();
        User user = testDataUtils.getData();
        String userEmail = String.format(user.getEmail(), Math.random());
        loginPage.enterEmail(userEmail);
        AccountCreationPage accountCreationPage = loginPage.clickCreateAccount();
        Assert.assertTrue(accountCreationPage.isAccountCreationFormDisplay(), "Account Creation Form is not displayed");

        accountCreationPage.populateRequiredFields(user.getFirstName(), user.getLastName(), user.getPassword(), user.getAddress(), user.getCity(), user.getState(), user.getZipCode(), user.getMobilePhone());
        AccountPage accountPage = accountCreationPage.clickRegisterButton();
        Assert.assertTrue(accountPage.isPageLoaded(), "User is not created, Account page is not opened");

        Assert.assertTrue(accountPage.getNameFromLink().equals(user.getFirstName() + " " + user.getLastName()), "Firstname and LastName of the created user is not displayed");

        loginPage = accountPage.clickSignOutButton();
        loginPage.enterLoginEmail(userEmail);
        loginPage.enterLoginPassword(user.getPassword());
        accountPage = loginPage.clickSignInButton();
        Assert.assertTrue(accountPage.isPageLoaded(), "User is not logged in, Account page is not opened");
    }

    @Test
    @Description("Checking logging with valid credentials")
    public void loginTest() throws IOException {
        driver.get(propertiesUtils.getProperty("loginUrl"));
        LoginPage loginPage = new LoginPage(driver, webDriverWait);
        Assert.assertTrue(loginPage.isLoginPageDisplay(), "Account Creation Page is not displayed");

        AccountPage accountPage = loginPage.login(propertiesUtils.getProperty("email"), propertiesUtils.getProperty("password"));
        Assert.assertTrue(accountPage.isPageLoaded(), "User is not logged in, Account page is not opened");
        Assert.assertTrue(accountPage.getNameFromLink().equals("TestFirstName TestLastName"), "Firstname and LastName of the logged in user is not displayed");
    }
}
