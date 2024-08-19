package org.asegorov.framework.steps;

import io.cucumber.java.ru.И;
import org.asegorov.framework.managers.PageManager;

public class MainPageSteps {
    PageManager pageManager = PageManager.getPageManager();

    @И("нажимает на 'Каталог'")
    public void clickOnCatalogButton() {
        pageManager.getMainPage().clickOnCatalogButton();
    }

    @И("выбирает раздел {string} каталога")
    public void selectCatalogItem(String string) {
        pageManager.getMainPage().selectCatalogItem(string);
    }

    @И("выбирает подраздел {string}")
    public void selectSubategoryFromGrid(String string) {
        pageManager.getMainPage().selectSubategoryFromGrid(string);
    }

    @И("задает параметр поиска по ( max | min ) цене от {string} рублей")
    public void fillPriceFilter(String type, String value) {
        pageManager.getMainPage().fillPriceFilter(type, value);
    }
    @И("задает параметр поиска по min цене от {string} рублей")
    public void fillMinPrice(String value) {
        pageManager.getMainPage().fillPriceFilter("min", value);
    }
    @И("задает параметр поиска по max цене от {string} рублей")
    public void fillMaxPrice(String value) {
        pageManager.getMainPage().fillPriceFilter("max", value);
    }

    @И("выбрает производителя {string}")
    public void selectManufacturer(String manufacturer) {
        pageManager.getMainPage().selectManufacturer(manufacturer);
    }

    @И("дожидается окончания поиска с фильтрами")
    public void waitUtilFilterWorks() {
        pageManager.getMainPage().waitUtilFilterWorks();
    }

    @И("проверяет, что количество товаров в выдаче совпадает с со значением {int}")
    public void elementsOnPageCountAssert(Integer count) {
        pageManager.getMainPage().elementsOnPageCountAssert(count);
    }

    @И("cохраняет наименование первого товара в списке")
    public void saveFirstElementTitle() throws InterruptedException {
        pageManager.getMainPage().saveFirstElementTitle();
    }

    @И("выполняет поиск по сохраненному значению")
    public void makeSearch() throws InterruptedException {
        pageManager.getMainPage().makeSearch();
    }
    @И("проверяет название элемента в результатах выдачи")
    public void checkSearchResultName() {
        pageManager.getMainPage().checkSearchResultName();
    }

}
