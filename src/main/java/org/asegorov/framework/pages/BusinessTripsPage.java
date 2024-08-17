package org.asegorov.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class BusinessTripsPage extends BasePage {
    @FindBy(xpath = "//a[@title = 'Создать командировку']")
    private WebElement createBusinessTripButton;
    @FindBy(xpath = "//h1[text() = 'Создать командировку']")
    private WebElement businessTripHeader;
    @FindBy(xpath = "//select[@data-name = 'field__business-unit']")
    private WebElement businessUnitDropdpwn;
    @FindBy(xpath = "//a[@id = 'company-selector-show']")
    private WebElement hostOrganisationsListButton;
    @FindBy(xpath = "//span[@class = 'select2-chosen' and text() = 'Укажите организацию']")
    private WebElement hostOrganisationsListDropdown;
    @FindBy(xpath = "//input[contains(@class, 'select2-input')]")
    private WebElement hostOrganisationsListSearchInputField;
    @FindBy(xpath = "//li[@class = 'select2-searching']")
    private WebElement hostOrganisationsListDropdownSearchIndicator;
    @FindBy(xpath = "//span[@class = 'select2-match']")
    private WebElement hostOrganisationsListSearchResult;
    @FindBy(xpath = "//input[contains(@id, 'crm_business_trip_company')]")
    private WebElement hostOrganisationInputField;
    @FindBy(xpath ="//div[contains(@id, 'crm_business_trip_tasks')]")
    private WebElement tasksCheckBoxesBlock;
    @FindBy(xpath = "//input[@data-name = 'field__departure-city']")
    private WebElement departureCityField;
    @FindBy(xpath = "//input[@data-name = 'field__arrival-city']")
    private WebElement arrivalCityField;
    @FindBy(xpath = "//input[contains(@id, 'date_selector_crm_business_trip_departureDatePlan')]")
    private WebElement departureDateField;
    @FindBy(xpath = "//input[contains(@id, 'date_selector_crm_business_trip_returnDatePlan')]")
    private WebElement arrivalDateField;
    @FindBy(xpath = "//button[@type = 'submit' and contains(text(), 'Сохранить и закрыть')]")
    private WebElement saveAndCloseButton;
    @FindBy(xpath = "//html")
    private WebElement root;


    public BusinessTripsPage createBusinessTrip() {
        createBusinessTripButton.click();
        this.loading();
        waitUtilElementToBeVisible(businessTripHeader);
        return this;
    }

    public BusinessTripsPage fillBusinessUnit(String value) {
        Select select = new Select(businessUnitDropdpwn);
        select.selectByVisibleText(value);
        Assert.assertEquals(value, select.getFirstSelectedOption().getText());
        return this;
    }

    public BusinessTripsPage fillOrganisation(String value) {
        hostOrganisationsListButton.click();
        hostOrganisationsListDropdown.click();
        wait.until(ExpectedConditions.invisibilityOf(hostOrganisationsListDropdownSearchIndicator));
        hostOrganisationsListSearchInputField.sendKeys(value);
        wait.until(ExpectedConditions.invisibilityOf(hostOrganisationsListDropdownSearchIndicator));
        hostOrganisationsListSearchResult.click();
        Assert.assertEquals(value, hostOrganisationInputField.getAttribute("value"));
        return this;
    }

    public BusinessTripsPage selectCheckBox(String name) {
        WebElement checkBox = tasksCheckBoxesBlock.findElement(By.xpath("./descendant::label[contains(text(), '"+ name +"')]/preceding-sibling::input"));
        if (!checkBox.isSelected()) {
            checkBox.click();
        }
        Assert.assertTrue(checkBox.isSelected());
        return this;
    }

    public BusinessTripsPage fillDepartureAndArrivalCity(String departureCity, String arrivalCity) {
        clearInputField(departureCityField);
        fillInputField(departureCityField, departureCity);
        clearInputField(arrivalCityField);
        fillInputField(arrivalCityField, arrivalCity);
        Assert.assertEquals(departureCity, departureCityField.getAttribute("value"));
        Assert.assertEquals(arrivalCity, arrivalCityField.getAttribute("value"));
        return this;
    }

    public BusinessTripsPage fillDepartureAndArrivalDate(String departureDate, String arrivalDate) {
        fillDateField(departureDateField, departureDate);
        fillDateField(arrivalDateField, arrivalDate);
        Assert.assertEquals(departureDate, departureDateField.getAttribute("value"));
        Assert.assertEquals(arrivalDate, arrivalDateField.getAttribute("value"));
        return this;
    }
    public BusinessTripsPage saveAndClose() {
        saveAndCloseButton.click();
        return this;
    }


    public BusinessTripsPage checkErrorMessageAtField(String nameField, String errMessage) {
        WebElement element = root.findElement(By.xpath("//span[contains(text(), '" + nameField + "')]/ancestor::div[@class = 'responsive-cell responsive-cell-no-blocks']//descendant::span[contains(text(), '" + errMessage + "')]"));
        Assert.assertTrue(element.isDisplayed());
        return this;
    }
}



