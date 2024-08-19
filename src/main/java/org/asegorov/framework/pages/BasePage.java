package org.asegorov.framework.pages;

import org.asegorov.framework.managers.DriverManager;
import org.asegorov.framework.managers.PageManager;
import org.asegorov.framework.managers.TestPropManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

import static org.openqa.selenium.support.ui.ExpectedConditions.jsReturnsValue;


public class BasePage {

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

    public void waitUntilDocumentToBeLoadedJs() {
        while (!js.executeScript("return document.readyState;").toString().equals("complete")) {
            System.out.println(js.executeScript("return document.readyState;").toString().equals("complete"));
        }
        System.out.println(js.executeScript("return document.readyState;").toString().equals("complete"));
    }

    public WebElement scrollWithOffset(WebElement element, int x, int y) {
        String code = "window.scroll(" + (element.getLocation().x + x) + ","
                + (element.getLocation().y + y) + ");";
        ((JavascriptExecutor) driverManager.getDriver()).executeScript(code, element, x, y);
        return element;
    }

    protected WebElement waitUtilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }


}
