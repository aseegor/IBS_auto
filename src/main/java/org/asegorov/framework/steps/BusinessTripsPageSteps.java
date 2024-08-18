package org.asegorov.framework.steps;

import io.cucumber.java.ru.И;
import org.asegorov.framework.managers.PageManager;


public class BusinessTripsPageSteps {
    PageManager pageManager = PageManager.getPageManager();

    @И("нажимает кнопку создания командировки")
    public void createBusinessTrip() {
        pageManager.getBusinessTripsPage().createBusinessTrip();
    }

    @И("заполняет поле 'Подразделение' значением {string}")
    public void fillBusinessUnit(String value) {
        pageManager.getBusinessTripsPage().fillBusinessUnit(value);
    }

    @И("заполняет поле 'Принимающая организация' значением {string}")
    public void fillOrganisation(String value) {
        pageManager.getBusinessTripsPage().fillOrganisation(value);
    }

    @И("заполняет чекбокс {string}")
    public void selectCheckBox(String name) {
        pageManager.getBusinessTripsPage().selectCheckBox(name);
    }

    @И("заполняет город выбытия и прибытия значениями {string}, {string}")
    public void fillDepartureAndArrivalCity(String departureCity, String arrivalCity) {
        pageManager.getBusinessTripsPage().fillDepartureAndArrivalCity(departureCity, arrivalCity);
    }

    @И("заполняет дату вылета и возвращения значениями {string}, {string}")
    public void fillDepartureAndArrivalDate(String departureDate, String arrivalDate) {
        pageManager.getBusinessTripsPage().fillDepartureAndArrivalDate(departureDate, arrivalDate);
    }

    @И("нажимает 'Сохранить и закрыть'")
    public void saveAndClose() {
        pageManager.getBusinessTripsPage().saveAndClose();
    }

    @И("в блоке {string} проверяет наличие сообщения об ошибке {string}")
    public void checkErrorMessageAtField(String nameField, String errMessage) {
        pageManager.getBusinessTripsPage().checkErrorMessageAtField(nameField, errMessage);
    }

    @И("ожидает исчезовение лоадера")
    public void loading() {
        pageManager.getBusinessTripsPage().loading();
    }
}
