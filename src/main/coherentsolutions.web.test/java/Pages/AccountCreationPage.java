package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountCreationPage extends BasePage {

    @FindBy(xpath = "//input[@id = 'email_create']")
    private WebElement emailCreateAccountField;

    @FindBy(xpath = "//form[@id = 'account-creation_form']")
    private WebElement createAccountForm;

    @FindBy(xpath = "//input[@id = 'email']")
    private WebElement emailLoginField;

    @FindBy(xpath = "//input[@id = 'passwd']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@name = 'customer_firstname']")
    private WebElement firstnameField;

    @FindBy(xpath = "//input[@name = 'customer_lastname']")
    private WebElement lastnameField;

    @FindBy(xpath = "//input[@name = 'passwd']")
    private WebElement passwordAccountField;

    @FindBy(xpath = "//input[@name = 'address1']")
    private WebElement addressField;

    @FindBy(xpath = "//input[@name = 'city']")
    private WebElement cityField;

    @FindBy(xpath = "//select[@name = 'id_state']")
    private WebElement stateField;

    @FindBy(xpath = "//input[@name = 'postcode']")
    private WebElement zipCodeField;

    @FindBy(xpath = "//select[@name = 'id_country']")
    private WebElement countryField;

    @FindBy(xpath = "//input[@name = 'phone_mobile']")
    private WebElement mobileField;

    @FindBy(xpath = "//select[@name = 'alias']")
    private WebElement addressAliasField;

    @FindBy(xpath = "//form[@id = 'create-account_form']")
    private WebElement loginPageCreateAccountForm;

    @FindBy(xpath = "//button[@name = 'submitAccount']")
    private WebElement registerButton;

    private Logger log = LogManager.getLogger(AccountCreationPage.class);

    public AccountCreationPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public AccountCreationPage enterFirstName(String firstName) {
        firstnameField.sendKeys(firstName);
        log.info("First Name entered");
        return this;
    }

    public AccountCreationPage enterLastName(String lastName) {
        lastnameField.sendKeys(lastName);
        log.info("Last Name entered: " + lastName);
        return this;
    }

    public AccountCreationPage enterPassword(String password) {
        passwordAccountField.sendKeys(password);
        log.info("Password entered: " + password);
        return this;
    }

    public AccountCreationPage enterAddress(String address) {
        addressField.sendKeys(address);
        log.info("Address entered: " + address);
        return this;
    }

    public AccountCreationPage enterCity(String city) {
        cityField.sendKeys(city);
        log.info("City entered: " + city);
        return this;
    }

    public AccountCreationPage chooseState(String state) {
        stateField.sendKeys(state);
        log.info("State entered: " + state);
        return this;
    }

    public AccountCreationPage enterZipCode(String zipCode) {
        zipCodeField.sendKeys(zipCode);
        log.info("Zip Code entered: " + zipCode);
        return this;
    }

    public AccountCreationPage enterMobilePhone(String mobile) {
        mobileField.sendKeys(mobile);
        log.info("Mobile entered: " + mobile);
        return this;
    }

    public boolean isAccountCreationFormDisplay() {
        webDriverWait.until(ExpectedConditions.visibilityOf(createAccountForm));
        log.info("Account Page opened");
        return createAccountForm.isDisplayed();
    }

    public AccountPage clickRegisterButton() {
        registerButton.click();
        log.info("Register Button clicked");
        return new AccountPage(driver, webDriverWait);
    }


    public void populateRequiredFields(String firstName, String lastName, String password, String address, String city, String state, String zipCode, String mobile) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPassword(password);
        enterAddress(address);
        enterCity(city);
        chooseState(state);
        enterZipCode(zipCode);
        enterMobilePhone(mobile);
        log.info("Required fields populated");
    }
}
