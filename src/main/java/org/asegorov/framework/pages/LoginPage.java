package org.asegorov.framework.pages;

import org.asegorov.framework.managers.TestPropManager;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.asegorov.framework.utils.PropConst.*;


public class LoginPage extends BasePage {
    @FindBy(xpath = "//form[@id='login-form']")
    public WebElement loginForm;

    @FindBy(xpath = "//input[@name='_username']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@name='_password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;


    public LoginPage checkOpenLoginPage() {
        Assert.assertTrue(loginForm.isDisplayed());
        return this;
    }
    public LoginPage enterUsernameAndPassword() {
        usernameField.sendKeys(TestPropManager.getTestPropManager().getProperty(USERNAME));
        passwordField.sendKeys(TestPropManager.getTestPropManager().getProperty(PASSWORD));
        return pageManager.getLoginPage();
    }
    public HomePage submitClick() {
        submitButton.click();
        return pageManager.getHomePage().checkOpenHomePage();
    }
}
