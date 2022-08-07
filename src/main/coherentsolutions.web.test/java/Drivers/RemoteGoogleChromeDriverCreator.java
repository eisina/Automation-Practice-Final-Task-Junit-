package Drivers;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class RemoteGoogleChromeDriverCreator implements WebDriverCreator {

    @Override
    public WebDriver create() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("goog:chromeOptions", options);
        caps.setCapability("browserName", "chrome");
        caps.setCapability("browserVersion", "latest");
        caps.setCapability("platformName", "Windows 8.1");
        URL url = new URL("https://oauth-isinacath378-f829a:eb59a0a6-bb06-4b1c-82ab-2592e883a227@ondemand.us-west-1.saucelabs.com:443/wd/hub");

        WebDriver driver = new RemoteWebDriver(url, caps);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
}
