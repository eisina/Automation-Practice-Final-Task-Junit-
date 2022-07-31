package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{

    @FindBy(xpath = "//input[@id = 'email_create']")
    private WebElement emailCreateAccountField;

    @FindBy(xpath = "//button[@id='SubmitCreate']")
    private WebElement createAccountButton;

    @FindBy(xpath = "//form[@id = 'account-creation_form']")
    private WebElement createAccountForm;

    @FindBy(xpath = "//button[@id='SubmitLogin']")
    private WebElement signInButton;

    @FindBy(xpath = "//input[@id = 'email']")
    private WebElement emailLoginField;

    @FindBy(xpath = "//input[@id = 'passwd']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@name = 'customer_firstname']")
    private WebElement firstnameField;

    @FindBy(xpath = "//input[@name = 'firstname']")
    private WebElement addressFirstnameField;

    @FindBy(xpath = "//input[@name = 'lastname']")
    private WebElement addressLastnameField;

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

    public LoginPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    //return boolean
    public LoginPage clickCreateAccount(){
        createAccountButton.click();
        return this;
    }

    public LoginPage enterEmail(String email){
        emailCreateAccountField.sendKeys(email);
        return this;
    }

    public LoginPage enterFirstName(String firstName){
        firstnameField.sendKeys(firstName);
        return this;
    }

    public LoginPage enterLastName(String lastName){
        lastnameField.sendKeys(lastName);
        return this;
    }

    public LoginPage enterPassword(String password){
        passwordAccountField.sendKeys(password);
        return this;
    }

    public LoginPage enterAddress(String address){
        addressField.sendKeys(address);
        return this;
    }

    public LoginPage enterCity(String city){
        cityField.sendKeys(city);
        return this;
    }

    public LoginPage chooseState(String state){
        stateField.sendKeys(state);
        return this;
    }

    public LoginPage enterZipCode(String zipCode){
        zipCodeField.sendKeys(zipCode);
        return this;
    }

    public LoginPage enterMobilePhone(String mobile){
        mobileField.sendKeys(mobile);
        return this;
    }

    public boolean isLoginPageDisplay(){
        webDriverWait.until(ExpectedConditions.visibilityOf(loginPageCreateAccountForm));
        return loginPageCreateAccountForm.isDisplayed();
    }
    public boolean isAccountCreationFormDisplay(){
        webDriverWait.until(ExpectedConditions.visibilityOf(createAccountForm));
        return createAccountForm.isDisplayed();
    }

    public LoginPage clickRegisterButton(){
        registerButton.click();
        return this;
    }





}
