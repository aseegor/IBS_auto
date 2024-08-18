package org.asegorov.framework.managers;


import org.asegorov.framework.pages.BasePage;
import org.asegorov.framework.pages.BusinessTripsPage;
import org.asegorov.framework.pages.HomePage;
import org.asegorov.framework.pages.LoginPage;

public class PageManager {

    private static PageManager pageManager;
    private LoginPage loginPage;
    private HomePage homePage;
    private BusinessTripsPage businessTripsPage;


    private PageManager() {
    }

    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }
    public BusinessTripsPage getBusinessTripsPage() {
        if (businessTripsPage == null) {
            businessTripsPage = new BusinessTripsPage();
        }
        return businessTripsPage;
    }
}
