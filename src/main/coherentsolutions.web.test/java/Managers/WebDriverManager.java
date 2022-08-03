package Managers;

import Utils.PropertiesUtils;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

public class WebDriverManager {

    private static WebDriver driver;
    private static PropertiesUtils propertiesUtils;

    public static WebDriver initDriver() throws IOException {
        if (driver == null) {
            propertiesUtils = new PropertiesUtils();
            switch (propertiesUtils.getProperty("environment")) {
                case "local": {
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
                case "remote": {
                    if (driver == null) {
                        ChromeOptions options = new ChromeOptions();
                        MutableCapabilities caps = new MutableCapabilities();
                        caps.setCapability("goog:chromeOptions", options);
                        caps.setCapability("browserName", "chrome");
                        caps.setCapability("browserVersion", "latest");
                        caps.setCapability("platformName", "Windows 8.1");
                        URL url = new URL("https://oauth-isinacath378-f829a:eb59a0a6-bb06-4b1c-82ab-2592e883a227@ondemand.us-west-1.saucelabs.com:443/wd/hub");

                        driver = new RemoteWebDriver(url, caps);
                        driver.manage().window().maximize();
                        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    }
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
