package org.asegorov.framework.steps;

import io.cucumber.java.ru.И;
import org.asegorov.framework.managers.PageManager;

public class LoginPageSteps {
    PageManager pageManager = PageManager.getPageManager();

    @И("проверяет что страница авторизации загрузилась")
    public void checkOpenLoginPage() {
        pageManager.getLoginPage().checkOpenLoginPage();
    }
    @И("вводит логин и пароль")
    public void enterUsernameAndPassword() {
        pageManager.getLoginPage().enterUsernameAndPassword();
    }
    @И("вводит логин {string} и пароль {string}")
    public void enterUsernameAndPassword(String username, String password) {
        pageManager.getLoginPage().enterUsernameAndPassword(username, password);
    }
    @И("нажимает кнопку 'Войти'")
    public void submitClick() {
        pageManager.getLoginPage().submitClick();
    }
}
