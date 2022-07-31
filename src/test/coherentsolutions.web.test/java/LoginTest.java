import Managers.WebDriverManager;
import Pages.LoginPage;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileNotFoundException;
import java.time.Duration;


import static Managers.WebDriverManager.initDriver;
import static org.junit.Assert.assertTrue;

public class LoginTest {

    private static WebDriver driver;
    private static Logger log = LogManager.getLogger();
    // private PropertiesUtils propertiesUtils;
    private static WebDriverWait webDriverWait;

    @BeforeAll
    public static void addSettings(){
        driver = WebDriverManager.initDriver("chrome");
        //   propertiesUtils = new PropertiesUtils();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    @DisplayName("Checking login and log out with valid credentials")
    public void loginTest() throws Exception {
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        LoginPage loginPage = new LoginPage(driver, webDriverWait);
        assertTrue("Account Creation Page is not displayed", loginPage.isLoginPageDisplay());
        //    takeSnapShot(driver, String.format("screenshots/Step1_%s.png", now()));
        log.info("Account Creation Page opened");

        loginPage.enterEmail("emailtesttik@mailik.ru");
        loginPage.clickCreateAccount();
        assertTrue("Account Creation Form is not displayed", loginPage.isAccountCreationFormDisplay());
        log.info("Account Creation Form displayed");

        loginPage.enterFirstName("TestFirstName");
        loginPage.enterLastName("TestLastName");
        loginPage.enterPassword("TestPassword");
        loginPage.enterAddress("TestAddress");
        loginPage.enterCity("TestCity");
        loginPage.chooseState("Alabama");
        loginPage.enterZipCode("00000");
        loginPage.enterMobilePhone("01234567");
        loginPage.clickRegisterButton();
    }
}
