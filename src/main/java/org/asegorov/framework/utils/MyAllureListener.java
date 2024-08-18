package org.asegorov.framework.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.junit4.AllureJunit4;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StatusDetails;
import io.qameta.allure.util.ResultsUtils;
import org.asegorov.framework.managers.DriverManager;
import org.junit.runner.notification.Failure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class MyAllureListener extends AllureJunit4 {
    public void testFailure(Failure failure) {
        getScreenshot();
        super.testFailure(failure);

    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] getScreenshot() {
        return ((TakesScreenshot) (DriverManager.getDriverManager().getDriver())).getScreenshotAs(OutputType.BYTES);
    }
}

