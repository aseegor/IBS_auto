package org.asegorov.framework.pages;

import io.qameta.allure.Step;
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

    @Step("Проверяет, что страница авторизации загрузилась")
    public LoginPage checkOpenLoginPage() {
        Assert.assertTrue(loginForm.isDisplayed());
        return this;
    }
@Step("Вводит логин и пароль")
    public LoginPage enterUsernameAndPassword(){
        checkOpenLoginPage();
        usernameField.sendKeys(TestPropManager.getTestPropManager().getProperty(USERNAME));
        passwordField.sendKeys(TestPropManager.getTestPropManager().getProperty(PASSWORD));
        return pageManager.getLoginPage();
    }
    @Step("Нажимает кнопку 'Войти'")
    public HomePage submitClick() {
        submitButton.click();
        return pageManager.getHomePage().checkOpenHomePage();
    }
}
