import Managers.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {

    protected static WebDriver driver;
    protected static Logger log = LogManager.getLogger();
    // private PropertiesUtils propertiesUtils;
    protected static WebDriverWait webDriverWait;

    @BeforeAll
    public static void addSettings() {
        driver = WebDriverManager.initDriver("chrome");
        //   propertiesUtils = new PropertiesUtils();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterAll
    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
