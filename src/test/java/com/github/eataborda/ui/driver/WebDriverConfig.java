package com.github.eataborda.ui.driver;

import com.github.eataborda.ui.enums.URLs;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebDriverConfig {
    private static final String browser = getParameterValue("browser");
    private static final List<String> supportedBrowsers = new ArrayList<>(Arrays.asList("chrome", "firefox", "edge"));

    public static void setup() {
        setupDriver(browser);
    }

    public static WebDriver setupTest(WebDriver driver) {
        if (supportedBrowsers.contains(browser)) {
            switch (browser) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(250));
            driver.get(URLs.LOGIN.getValue());
            driver.manage().window().maximize();
            return driver;
        } else {
            throw new IllegalStateException("Not supported browser");
        }
    }

    private static void setupDriver(String browser) {
        if (supportedBrowsers.contains(browser)) {
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    break;
            }
        } else {
            //Throw an exception not supported browser
        }
    }

    private static String getParameterValue(String key) {
        return System.getProperty(key);
    }
}
