package org.asegorov.framework.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.asegorov.framework.managers.DriverManager;
import org.asegorov.framework.managers.InitManager;
import org.asegorov.framework.managers.TestPropManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static org.asegorov.framework.utils.PropConst.BASE_URL;

public class Hooks {
    WebDriver driver = DriverManager.getDriverManager().getDriver();

    @Before
    public void before() {
        InitManager.initFramework();
        driver.get(TestPropManager.getTestPropManager().getProperty(BASE_URL));
    }

    @After
    public void tearDown(Scenario scenario) {
        String screenshotName = scenario.getName().replace(" ", "_");
        try {
            if (scenario.isFailed()) {
                scenario.log("Failed");
                TakesScreenshot ts = (TakesScreenshot) driver;
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "img/png", screenshotName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        InitManager.quitFramework();
    }
}
