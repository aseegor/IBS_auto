package ru.asegorov.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FirstTest {
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    String username = "Taraskina Valeriya";
    String password = "testing";


    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        //   System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://training.appline.ru/ ");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//form[@id='login-form']"))));
    }

    @Test
    public void test() throws InterruptedException {
        // Авторизуется
        driver.findElement(By.xpath("//input[@name='_username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@name='_password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[text() = 'Панель быстрого запуска']"))));

        // В командировки
        WebElement costList = driver.findElement(By.xpath(
                "//ul[contains(@class, 'main-menu')]/li/a/span[text()='Расходы']"));
        costList.click();
        wait.until(ExpectedConditions.visibilityOf(costList.findElement(By.xpath(
                "./ancestor::li//ul[@class = 'dropdown-menu menu_level_1']"))));
        driver.findElement(By.xpath("//span[text()= 'Командировки']")).click();
        loading();

        // Создает командировку
        driver.findElement(By.xpath("//a[@title = 'Создать командировку']")).click();
        loading();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[text() = 'Создать командировку']"))));

        // Заполняет поля
        Select select = new Select(driver.findElement(By.xpath("//select[@data-name = 'field__business-unit']")));
        select.selectByVisibleText("Центр разработки и сопровождения");
        driver.findElement(By.xpath("//a[@id = 'company-selector-show']")).click();
        driver.findElement(By.xpath("//span[@class = 'select2-chosen' and text() = 'Укажите организацию']")).click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//li[@class = 'select2-searching']"))));
        driver.findElement(By.xpath("//input[contains(@class, 'select2-input')]")).sendKeys("(Хром)");
        driver.findElement(By.xpath("//span[@class = 'select2-match' and contains(text(), 'Хром')]")).click();
        driver.findElement(By.xpath("//label[text() = 'Заказ билетов']")).click();
        driver.findElement(By.xpath("//input[@data-name = 'field__departure-city']")).sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        driver.findElement(By.xpath("//input[@data-name = 'field__departure-city']")).sendKeys("Россия, Ярославь");
        driver.findElement(By.xpath("//input[@data-name = 'field__arrival-city']")).sendKeys("Россия, Саратов");
        driver.findElement(By.xpath("//input[contains(@id, 'date_selector_crm_business_trip_departureDatePlan')]")).sendKeys("30.09.2024");
        driver.findElement(By.xpath("//input[contains(@id, 'date_selector_crm_business_trip_returnDatePlan')]")).sendKeys("30.10.2024");
        driver.findElement(By.xpath("//input[contains(@id, 'date_selector_crm_business_trip_returnDatePlan')]")).sendKeys(Keys.ESCAPE);

        // Проверяет корректность заполнения
        Assert.assertEquals("Центр разработки и сопровождения", select.getFirstSelectedOption().getText());
        Assert.assertEquals("(Хром) Призрачная Организация Охотников", driver.findElement(By.xpath("//input[contains(@id, 'crm_business_trip_company')]")).getAttribute("value"));
        Assert.assertTrue(driver.findElement(By.xpath("//input[contains(@id, 'crm_business_trip_tasks_1')]")).isSelected());
        Assert.assertEquals("Россия, Ярославь", driver.findElement(By.xpath("//input[@data-name = 'field__departure-city']")).getAttribute("value"));
        Assert.assertEquals("Россия, Саратов", driver.findElement(By.xpath("//input[@data-name = 'field__arrival-city']")).getAttribute("value"));
        Assert.assertEquals("30.09.2024", driver.findElement(By.xpath("//input[contains(@id, 'date_selector_crm_business_trip_departureDatePlan')]")).getAttribute("value"));
        Assert.assertEquals("30.10.2024", driver.findElement(By.xpath("//input[contains(@id, 'date_selector_crm_business_trip_returnDatePlan')]")).getAttribute("value"));

        // Сохраняет
        driver.findElement(By.xpath("//button[@type = 'submit' and contains(text(), 'Сохранить и закрыть')]")).click();
        loading();

        // Проверяет наличие ошибки
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(), 'Командированные сотрудники')]/ancestor::div[@class = 'responsive-cell responsive-cell-no-blocks']//descendant::span[contains(text(), 'Список командируемых сотрудников не может быть пустым')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(), 'Внештатные сотрудники')]/ancestor::div[@class = 'responsive-cell responsive-cell-no-blocks']//descendant::span[contains(text(), 'Список командируемых сотрудников не может быть пустым')]")).isDisplayed());



//        driver.findElement(By.xpath("//div[@class='filter-item oro-drop']/div[contains(text(), 'Стадия')]")).click();
//        wait.until(ExpectedConditions.visibilityOf(
//                driver.findElement(By.xpath("//div[contains(@class, 'ui-multiselect-menu ui-corner-all')]"))));
//        driver.findElement(By.xpath(
//                        "//div[contains(@class, 'ui-multiselect-menu ui-corner-all')]//input[@type='search']"))
//                .sendKeys("Согласование с ОСР");
//        driver.findElement(By.xpath("//label[@title='Согласование с ОСР']")).click();
//        loading();
//        String id = driver.findElement(By.xpath("//td[text() = 'Кашира']/parent::tr/td[contains(@class, 'name')]")).getText();
//        driver.findElement(By.xpath("//div[@class = 'filter-item oro-drop']/div[contains(text(), 'Номер')]")).click();
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name = 'value']")))).sendKeys(id, Keys.ENTER);
//        loading();

        // to trip
    }

    public void loading() {
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='loader-mask shown']"))));
    }

    @After
    public void after() {
        driver.quit();
    }
}
