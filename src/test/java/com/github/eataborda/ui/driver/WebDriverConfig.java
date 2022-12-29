package com.github.eataborda.ui.driver;

import com.github.eataborda.ui.enums.URLs;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

public class WebDriverConfig {

    public static void setupTest(WebDriver driver) {
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(250));
            driver.get(URLs.LOGIN.getValue());
            driver.manage().window().maximize();
    }

    private static String getParameterValue(String key) {
        return System.getProperty(key);
    }
}
