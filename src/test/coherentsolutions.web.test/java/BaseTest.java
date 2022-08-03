import Managers.WebDriverManager;
import Utils.PropertiesUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    protected static WebDriver driver;
    protected static Logger log = LogManager.getLogger();
    protected static WebDriverWait webDriverWait;

    @BeforeAll
    public static void addSettings() throws IOException {
        driver = WebDriverManager.initDriver();
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
