import Managers.WebDriverManager;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    protected static WebDriver driver;
    protected static Logger log = LogManager.getLogger();
    protected static WebDriverWait webDriverWait;

    @BeforeClass
    public static void addSettings() throws IOException {
        driver = WebDriverManager.initDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterClass
    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Attachment
    public byte[] saveFailureScreenShot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment
    public String getBrowserDetails() {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        String os = cap.getPlatformName().toString();
        String version = cap.getBrowserVersion();
        return browserName + " " + os + " " + version;
    }

    @AfterMethod
    protected void addFailInfo(ITestResult result) {
        if (!result.isSuccess()) {
            saveFailureScreenShot(driver);
            getBrowserDetails();
        }
    }
}
