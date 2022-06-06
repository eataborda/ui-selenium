package com.github.eataborda.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeTest {

    WebDriver driver;

    @BeforeClass
    public static void setupClass(){
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest(){
        driver = new ChromeDriver();
    }

    @After
    public void teardown(){
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test(){
        driver.get("https://google.com");
        String title = driver.getTitle();
        Assert.assertEquals("Google", title);
    }
}
