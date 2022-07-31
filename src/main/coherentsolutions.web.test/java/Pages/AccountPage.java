package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage extends BasePage{

    @FindBy(xpath = "//a[@title='View my customer account']")
    private WebElement accountLink;

    public AccountPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public String getNameFromLink(){
        return accountLink.getText();
    }

}
