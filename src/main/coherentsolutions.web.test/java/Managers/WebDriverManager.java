package Managers;

import Utils.PropertiesUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.time.Duration;

public class WebDriverManager {

    private static WebDriver driver;
    private static PropertiesUtils propertiesUtils;

    public static WebDriver initDriver(String browser) throws IOException {
        if (driver == null) {
            propertiesUtils = new PropertiesUtils();
            switch (propertiesUtils.getProperty("browser")) {
                case "chrome": {
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;
                }
                case "firefox": {
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;
                }
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
