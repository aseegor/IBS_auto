package org.asegorov.framework.managers;


import org.asegorov.framework.pages.MainPage;

public class PageManager {

    private static PageManager pageManager;
    private MainPage mainPage;


    private PageManager() {
    }

    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }


    public MainPage getMainPage() {
        if (mainPage == null) {
            mainPage = new MainPage();
        }
        return mainPage;
    }

}
