package org.asegorov.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class HomePage extends BasePage {

    @FindBy(xpath = "//h1[text() = 'Панель быстрого запуска']")
    private WebElement header;
    @FindBy(xpath = "//li[contains(@class, 'dropdown')]/descendant::span[@class = 'title']")
    private List<WebElement> listBaseMenu;


    public HomePage selectBaseMenu(String nameBaseMenu) {
        for (WebElement menuItem : listBaseMenu) {
            if (menuItem.getText().trim().equalsIgnoreCase(nameBaseMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                return this;
            }
        }
        Assert.fail("Меню '" + nameBaseMenu + "' не было найдено на стартовой странице!");
        return this;
    }

    public HomePage checkOpenHomePage() {
        Assert.assertTrue(header.isDisplayed());
        return this;
    }

}
