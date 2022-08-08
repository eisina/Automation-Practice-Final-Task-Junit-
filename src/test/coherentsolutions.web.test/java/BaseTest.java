import Drivers.WebDriverFactory;
import Utils.PropertiesUtils;
import io.qameta.allure.Attachment;
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
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected PropertiesUtils propertiesUtils;

    @BeforeClass
    public void addSettings() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        driver = WebDriverFactory.get();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        propertiesUtils = new PropertiesUtils();
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
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public void quit() {
        driver.quit();
    }
}
