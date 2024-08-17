package org.asegorov.framework.pages;

import io.qameta.allure.Step;
import org.asegorov.framework.managers.DriverManager;
import org.asegorov.framework.managers.PageManager;
import org.asegorov.framework.managers.TestPropManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BasePage {
    @FindBy(xpath = "//div[@class='loader-mask shown']")
    private WebElement loader;

    protected final DriverManager driverManager = DriverManager.getDriverManager();

    protected PageManager pageManager = PageManager.getPageManager();

    protected Actions action = new Actions(driverManager.getDriver());

    protected JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();

    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), Duration.ofSeconds(15), Duration.ofMillis(2000));

    private final TestPropManager props = TestPropManager.getTestPropManager();

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    protected WebElement scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    public WebElement scrollWithOffset(WebElement element, int x, int y) {
        String code = "window.scroll(" + (element.getLocation().x + x) + ","
                + (element.getLocation().y + y) + ");";
        ((JavascriptExecutor) driverManager.getDriver()).executeScript(code, element, x, y);
        return element;
    }
    @Step("Ожидает кликабельность элемента '{element}'")
    protected WebElement waitUtilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    @Step("Ожидает появление элемента '{element}' на странице")
    protected WebElement waitUtilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    @Step("Заполняет поле '{field}' значением '{value}'")
    protected void fillInputField(WebElement field, String value) {
        scrollToElementJs(field);
        waitUtilElementToBeClickable(field).click();
        field.sendKeys(value);
    }
    @Step("Очищает поле '{field}'")
    protected void clearInputField(WebElement field) {
        scrollToElementJs(field);
        waitUtilElementToBeClickable(field).click();
        field.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
    }

    @Step("Заполняет поле '{field}' датой '{value}'")
    protected void fillDateField(WebElement field, String value) {
        scrollToElementJs(field);
        field.sendKeys(value);
        field.sendKeys(Keys.ESCAPE);

    }
   @Step("Ожидает пропадание лоадера")
    public BusinessTripsPage loading() {
        wait.until(ExpectedConditions.invisibilityOf(loader));
        return pageManager.getBusinessTripsPage();
    }
}
