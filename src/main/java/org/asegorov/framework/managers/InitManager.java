package org.asegorov.framework.managers;

import java.time.Duration;

import static org.asegorov.framework.utils.PropConst.IMPLICITLY_WAIT;
import static org.asegorov.framework.utils.PropConst.PAGE_LOAD_TIMEOUT;

public class InitManager {

    private static final TestPropManager props = TestPropManager.getTestPropManager();


    private static final DriverManager driverManager = DriverManager.getDriverManager();


    public static void initFramework() {
        driverManager.getDriver().manage().window().maximize();
        driverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT))));
        driverManager.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(props.getProperty(PAGE_LOAD_TIMEOUT))));
    }

    public static void quitFramework() {
        driverManager.quitDriver();
    }
}
