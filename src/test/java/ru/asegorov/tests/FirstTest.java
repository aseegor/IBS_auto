package ru.asegorov.tests;

import org.junit.Test;
import ru.asegorov.basetestclass.BaseTests;

public class FirstTest extends BaseTests {

    @Test
    public void test() {
        trainingAppline.getLoginPage()
                .enterUsernameAndPassword()
                .submitClick()
                .selectBaseMenu("Расходы")
                .selectBaseMenu("Командировки")
                .loading()
                .createBusinessTrip()
                .fillBusinessUnit("Центр разработки и сопровождения")
                .fillOrganisation("(Хром) Призрачная Организация Охотников")
                .selectCheckBox("Заказ билетов")
                .fillDepartureAndArrivalCity(
                        "Россия, Ярославь",
                        "Россия, Саратов")
                .fillDepartureAndArrivalDate(
                        "30.09.2024",
                        "30.10.2024")
                .saveAndClose()
                .loading()
                .checkErrorMessageAtField("Командированные сотрудники", "Список командируемых сотрудников не может быть пустым")
                .checkErrorMessageAtField("Внештатные сотрудники", "Список командируемых сотрудников не может быть пустым");
    }
}
