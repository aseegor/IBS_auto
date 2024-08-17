package ru.asegorov.basetestclass;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.asegorov.framework.managers.DriverManager;
import org.asegorov.framework.managers.InitManager;
import org.asegorov.framework.managers.PageManager;
import org.asegorov.framework.managers.TestPropManager;

import static org.asegorov.framework.utils.PropConst.BASE_URL;

public class BaseTests {

    protected PageManager trainingAppline = PageManager.getPageManager();
    private final DriverManager driverManager = DriverManager.getDriverManager();

    @BeforeClass
    public static void beforeAll() {
        InitManager.initFramework();
    }

    @Before
    public void beforeEach() {
        driverManager.getDriver().get(TestPropManager.getTestPropManager().getProperty(BASE_URL));
    }
    @AfterClass
    public static void afterAll() {
        InitManager.quitFramework();
    }
}
