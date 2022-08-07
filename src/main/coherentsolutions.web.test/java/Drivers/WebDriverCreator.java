package Drivers;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public interface WebDriverCreator {

    public WebDriver create() throws MalformedURLException;

}
