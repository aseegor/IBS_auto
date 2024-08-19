package org.asegorov.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class MainPage extends BasePage {
    @FindBy(xpath = "/html")
    private WebElement root;
    @FindBy(xpath = "//a[contains(@class, 'Header_logo')]")
    private WebElement headerLogo;
    @FindBy(xpath = "//input[@id = 'searchInput']")
    private WebElement mainSearchInput;

    @FindBy(xpath = "//span[contains(text(), 'Каталог')]/parent::button")
    private WebElement catalogMenuButton;

    @FindBy(xpath = "//ul[contains(@class, 'Catalog_mainList')]/li")
    private List<WebElement> catalogMenuList;

    @FindBy(xpath = "//div[contains(@class, 'CategoryLayout_categories')]/descendant::p[contains(@class, 'CardCategory_title')]")
    private List<WebElement> subcategoriesGridList;

    @FindBy(xpath = "//span[contains(text(), 'Цена')]/ancestor::section/descendant::input")
    private List<WebElement> priceFilters;

    @FindBy(xpath = "//span[text() = 'Производитель']/ancestor::section/descendant::input[@type = 'text' and @placeholder = 'Поиск']")
    private WebElement manufacturerSearchInput;

    @FindBy(xpath = "//div[contains(@class, 'CardImageSlider')]/descendant::span[contains(@class, 'skeleton')]")
    private List<WebElement> searchIndicatorSkeleton;

    @FindBy(xpath = "//div[contains(@class, 'CardImageSlider_listing')]")
    private List<WebElement> itemsOnPageList;

    @FindBy(xpath = "//div[contains(text(), 'Товаров на странице')]/span")
    private WebElement itemsOnPageCount;

    @FindBy(xpath = "//div[contains(@class, 'CardImageSlider_listing')]/ancestor::div[contains(@class, 'Card_row')]/descendant::div[contains(@class, 'CardText_title')]")
    private List<WebElement> itemsTitlesList;

    private String titleOfElement = "";

    public MainPage clickOnElement(WebElement element) {
        waitUtilElementToBeInVisible(element);
        element.click();
        Assert.fail("Элемент '" + element + "' не найден");
        return this;
    }

    public MainPage clickOnCatalogButton() {
        waitUtilElementToBeVisible(headerLogo);
        catalogMenuButton.click();
        return this;
    }

    public MainPage selectCatalogItem(String nameItem) {
        for (WebElement menuItem : catalogMenuList) {
            if (menuItem.getText().trim().equalsIgnoreCase(nameItem)) {
                waitUtilElementToBeClickable(menuItem).click();
                return this;
            }
        }
        Assert.fail("Элемент '" + nameItem + "' не найден");
        return this;
    }

    public MainPage selectSubategoryFromGrid(String nameItem) {
        for (WebElement menuItem : subcategoriesGridList) {
            if (menuItem.getText().trim().contains(nameItem)) {
                menuItem.click();
                return this;
            }
        }
        Assert.fail("Элемент '" + nameItem + "' не найден");
        return this;
    }

    public MainPage fillPriceFilter(String type, String value) {
        for (WebElement menuItem : priceFilters) {
            if (((String) (menuItem.getAttribute("name"))).equals(type)) {
                menuItem.sendKeys(value);
                return this;
            }
        }
        Assert.fail("Элемент '" + priceFilters + "' не найден");
        return this;
    }

    public MainPage selectManufacturer(String name) {
        manufacturerSearchInput.sendKeys(name);
        WebElement checkbox = root.findElement(By.xpath(
                "//span[text() = 'Производитель']/ancestor::section/descendant::label[text()= '" + name + "']/parent::span/preceding-sibling::input[@type = 'checkbox']"));
        if (!checkbox.isSelected()) root.findElement(By.xpath(
                "//span[text() = 'Производитель']/ancestor::section/descendant::label[text()= '" + name + "']/parent::span")).click();
        return this;
    }

    //    public MainPage elementsOnPageCountAssert() {
//        Assert.assertEquals("Количество элементов не совпадает с ожидаемым значением",
//                Integer.parseInt(itemsOnPageCount.getText().replaceAll("по ", "")), itemsOnPageList.size());
//        return this;
//    }
    public MainPage elementsOnPageCountAssert(Integer count) throws InterruptedException {
            waitUtilElementToBeInVisible(searchIndicatorSkeleton.get(0));
            waitUtilElementToBeClickable(itemsOnPageList.get(0));
            Assert.assertTrue("Количество элементов не совпадает с ожидаемым значением",
                    count == itemsOnPageList.size());
        return this;
    }

    public MainPage saveFirstElementTitle() throws InterruptedException {
        waitUtilSearchEnds();
        Thread.sleep(5000);
        this.titleOfElement = itemsTitlesList.get(0).getText();
        return this;
    }

    public MainPage checkSearchResultName() throws InterruptedException {
//        Thread.sleep(5000);
        Assert.assertTrue("Название элемента не соответствует заданному", this.titleOfElement.equals(itemsTitlesList.get(0).getText()));
        return this;
    }

    public MainPage checkOpenMainPage() {
        Assert.assertTrue("Страница не загрузилась", headerLogo.isDisplayed());
        return this;
    }

    public WebElement waitUtilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public MainPage waitUtilSearchEnds() throws InterruptedException {
        wait.until(ExpectedConditions.invisibilityOf(searchIndicatorSkeleton.get(0)));
        waitUntilDocumentToBeLoadedJs();
        waitUtilElementToBeClickable(itemsTitlesList.get(0));
        return this;
    }

    public MainPage makeSearch(String searchText) {
        mainSearchInput.sendKeys(searchText);
        return this;
    }

    public MainPage makeSearch() throws InterruptedException {
        Thread.sleep(5000);
        mainSearchInput.sendKeys(this.titleOfElement);
        mainSearchInput.sendKeys(Keys.ENTER);
        return this;
    }

    public MainPage waitUtilElementToBeInVisible(WebElement element) {
        if (wait.until(ExpectedConditions.invisibilityOf(element))) {
            return this;
        }
        Assert.fail("Элемент '" + element + "' не пропал");
        return this;
    }

    protected void fillInputField(WebElement field, String value) {
        scrollToElementJs(field);
        waitUtilElementToBeClickable(field).click();
        field.sendKeys(value);
        field.sendKeys(Keys.ENTER);
    }

    protected void clearInputField(WebElement field) {
        scrollToElementJs(field);
        waitUtilElementToBeClickable(field).click();
        field.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
    }
}
