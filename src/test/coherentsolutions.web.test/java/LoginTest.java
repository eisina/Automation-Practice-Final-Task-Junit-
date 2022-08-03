import Pages.AccountCreationPage;
import Pages.AccountPage;
import Pages.LoginPage;
import TestDataTypes.User;
import Utils.TestDataUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.Result;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import static Utils.ConstantUtils.*;
import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    @Description("Checking logging with valid credentials")
    public void createAccountTest() throws Exception {
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver, webDriverWait);
        assertTrue("Account Creation Page is not displayed", loginPage.isLoginPageDisplay());
        log.info("Account Creation Page opened");

        TestDataUtils testDataUtils = new TestDataUtils();
        User user = testDataUtils.getUserData();
        loginPage.enterEmail(String.format(user.email, Math.random()));
        AccountCreationPage accountCreationPage = loginPage.clickCreateAccount();
        assertTrue("Account Creation Form is not displayed", accountCreationPage.isAccountCreationFormDisplay());
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
        assertTrue("User is not created, Account page is not opened", accountPage.isPageLoaded());
        log.info("Account Page is displayed");
        assertTrue("Firstname and LastName of the created user is not displayed", accountPage.getNameFromLink().equals("TestFirstName TestLadfdfstName"));
        log.info("Firstname and LastName checked at the Account link");
    }

    @Test
    @Description("Checking logging with valid credentials")
    public void loginTest() {
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver, webDriverWait);
        assertTrue("Account Creation Page is not displayed", loginPage.isLoginPageDisplay());
        log.info("Account Creation Page opened");

        loginPage.enterLoginEmail(SIGN_IN_EMAIL);
        loginPage.enterLoginPassword(SIGN_IN_PASSWORD);
        AccountPage accountPage = loginPage.clickSignInButton();
        assertTrue("User is not logged in, Account page is not opened", accountPage.isPageLoaded());
        log.info("Account Page is displayed");
        assertTrue("Firstname and LastName of the logged in user is not displayed", accountPage.getNameFromLink().equals("TestFirstName TestLastName"));
        log.info("Firstname and LastName checked at the Account link");
    }
//
//    @Attachment (value = "Page screenshot", type = "image/png")
//    public byte[] saveFailureScreenShot(WebDriver driver) {
//        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//    }
//
   @Attachment(value = "Page screenshot", type = "image/png")
    public void saveScreenshot() throws IOException {
           File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
       ByteArrayInputStream fileAsButeArray = new ByteArrayInputStream(FileUtils.readFileToByteArray(screenshotAs));
       Allure.addAttachment("Screenshot",fileAsButeArray );
       }



//    @Attachment
//    public String getBrowserDetails() {
//        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
//        String browserName = cap.getBrowserName().toLowerCase();
//        String os = cap.getPlatform().toString();
//        String version = cap.getVersion().toString();
//        return browserName + " " + os + " " + version;
//    }

    @AfterEach
     protected void addFailInfo(Result result) throws IOException {
        if (!result.wasSuccessful()) {
           saveScreenshot();
         //   saveFailureScreenShot(driver);
            //getBrowserDetails();
        }
    }

}
