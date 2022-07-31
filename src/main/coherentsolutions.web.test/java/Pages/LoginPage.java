package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@id = 'email_create']")
    private WebElement emailCreateAccountField;

    @FindBy(xpath = "//button[@id='SubmitCreate']")
    private WebElement createAccountButton;

    @FindBy(xpath = "//button[@id='SubmitLogin']")
    private WebElement signInButton;

    @FindBy(xpath = "//input[@id = 'email']")
    private WebElement emailLoginField;

    @FindBy(xpath = "//input[@id = 'passwd']")
    private WebElement passwordField;

    @FindBy(xpath = "//form[@id = 'create-account_form']")
    private WebElement loginPageCreateAccountForm;

    public LoginPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public AccountCreationPage clickCreateAccount() {
        createAccountButton.click();
        return new AccountCreationPage(driver, webDriverWait);
    }

    public LoginPage enterEmail(String email) {
        emailCreateAccountField.sendKeys(email);
        return this;
    }

    public boolean isLoginPageDisplay() {
        webDriverWait.until(ExpectedConditions.visibilityOf(loginPageCreateAccountForm));
        return loginPageCreateAccountForm.isDisplayed();
    }

    public LoginPage enterLoginEmail(String email) {
        emailLoginField.sendKeys(email);
        return this;
    }

    public LoginPage enterLoginPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public AccountPage clickSignInButton() {
        signInButton.click();
        return new AccountPage(driver, webDriverWait);
    }
}
