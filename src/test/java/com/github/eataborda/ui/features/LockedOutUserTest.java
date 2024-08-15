package com.github.eataborda.ui.features;

import com.github.eataborda.ui.driver.WebDriverConfig;
import com.github.eataborda.ui.pages.LoginPage;
import com.github.eataborda.ui.resources.AnnotationValues;
import com.github.eataborda.ui.resources.ErrorMessage;
import com.github.eataborda.ui.resources.LoginUser;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tags(value = {@Tag(AnnotationValues.LOCKED_OUT_USER_TAG), @Tag(AnnotationValues.REGRESSION_TAG)})
@DisplayName("Locked Out User")
@Epic("Login")
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
    @Tag(AnnotationValues.LOGIN_LOCKED_USER_TAG)
    @DisplayName(AnnotationValues.LOGIN_LOCKED_USER_DISPLAY_NAME)
    @Description("Verification of the error messages for a blocked user")
    @Feature("Error handling")
    public void lockedOutUserTest() {
        loginPage.loginValidUser(LoginUser.LOCKED_OUT_USER.getUser(), LoginUser.LOCKED_OUT_USER.getPassword());
        assertEquals(ErrorMessage.LOCKED_OUT_USER.getMessage(),
                loginPage.getFieldErrorMessage(), ErrorMessage.NOT_EXPECTED_MESSAGE.getMessage());
    }

    @Test
    @Tag(AnnotationValues.LOGIN_WRONG_PASSWORD_TAG)
    @DisplayName(AnnotationValues.LOGIN_WRONG_PASSWORD_DISPLAY_NAME)
    @Description("Verification of the error messages for a correct user and wrong password")
    @Feature("Error handling")
    public void wrongPasswordTest() {
        loginPage.loginValidUser(LoginUser.STANDARD_USER.getUser(), LoginUser.WRONG_USER.getPassword());
        assertEquals(ErrorMessage.USER_PASSWORD_NOT_MATCH.getMessage(),
                loginPage.getFieldErrorMessage(), ErrorMessage.NOT_EXPECTED_MESSAGE.getMessage());
    }
}
