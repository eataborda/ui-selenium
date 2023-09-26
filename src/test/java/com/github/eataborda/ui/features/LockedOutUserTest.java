package com.github.eataborda.ui.features;

import com.github.eataborda.ui.driver.WebDriverConfig;
import com.github.eataborda.ui.pages.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tags(value = {@Tag("locked-out-user"), @Tag("regression")})
public class LockedOutUserTest {
    public WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setupTest() {
        driver = WebDriverConfig.setupBrowser(driver);
        WebDriverConfig.setupTest(driver);
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Tag("login-locked-user")
    @DisplayName("Login locked out user test")
    public void lockedOutUserTest() {
        loginPage.loginValidUser("locked_out_user", "secret_sauce");
        assertEquals("Epic sadface: Sorry, this user has been locked out.",loginPage.getFieldErrorMessage(),"Message doesn't have the expected value");
    }

    @Test
    @Tag("login-wrong-password")
    @DisplayName("Login wrong password test")
    public void wrongPasswordTest() {
        loginPage.loginValidUser("standard_user", "wrong_sauce");
        assertEquals("Epic sadface: Username and password do not match any user in this service",loginPage.getFieldErrorMessage(),"Message doesn't have the expected value");
    }
}
