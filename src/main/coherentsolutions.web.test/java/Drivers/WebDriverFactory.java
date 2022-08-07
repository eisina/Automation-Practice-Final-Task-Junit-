package Drivers;

import Utils.PropertiesUtils;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {

    private static WebDriver driver;
    private static Map<String, Class> drivers;

    static {
        drivers = new HashMap<>();
        drivers.put("local-chrome", GoogleChromeDriverCreator.class);
        drivers.put("local-firefox", FirefoxDriverCreator.class);
        drivers.put("remote-chrome", RemoteGoogleChromeDriverCreator.class);
        drivers.put("remote-firefox", RemoteFirefoxDriverCreator.class);
    }

    public static WebDriver get() throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (driver != null) {
            return driver;
        }
        PropertiesUtils propertiesUtils = new PropertiesUtils();

        String environment = propertiesUtils.getProperty("environment");
        String browser = propertiesUtils.getProperty("browser");

        Class driverCreatorClass = drivers.get(environment + '-' + browser);
        WebDriverCreator driverCreator = (WebDriverCreator) driverCreatorClass.getDeclaredConstructor().newInstance();
        driver = driverCreator.create();
        return driver;
    }
}
