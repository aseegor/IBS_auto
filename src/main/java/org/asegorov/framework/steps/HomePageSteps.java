package org.asegorov.framework.steps;

import io.cucumber.java.ru.И;
import org.asegorov.framework.managers.PageManager;

public class HomePageSteps {
    PageManager pageManager = PageManager.getPageManager();

    @И("нажимает на пункт меню {string}")
    public void selectBaseMenu(String nameBaseMenu) {
        pageManager.getHomePage().selectBaseMenu(nameBaseMenu);
    }
    @И("проверяет что основная страница загрузилась")
    public void checkOpenHomePage() {
        pageManager.getHomePage().checkOpenHomePage();
    }
}
