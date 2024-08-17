package com.github.eataborda.ui.features;

import com.github.eataborda.ui.driver.WebDriverConfig;
import com.github.eataborda.ui.pages.LoginPage;
import com.github.eataborda.ui.resources.AnnotationValues;
import com.github.eataborda.ui.resources.AssertDescription;
import com.github.eataborda.ui.resources.ErrorMessage;
import com.github.eataborda.ui.resources.LoginUser;
import com.github.eataborda.ui.steps.AssertSteps;
import com.github.eataborda.ui.steps.LoginSteps;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

@Tags(value = {@Tag(AnnotationValues.LOCKED_OUT_USER_TAG), @Tag(AnnotationValues.REGRESSION_TAG)})
@DisplayName("Locked Out User")
@Epic("Login")
public class LockedOutUserTest {
    public WebDriver driver;
    private LoginSteps loginSteps;

    @BeforeEach
    public void setupTest() {
        driver = WebDriverConfig.setupBrowser(driver);
        WebDriverConfig.setupTest(driver);
        loginSteps = new LoginSteps(new LoginPage(driver));
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
        loginSteps.login(LoginUser.LOCKED_OUT_USER.getUser(), LoginUser.LOCKED_OUT_USER.getPassword());
        AssertSteps.hardAssertEquals(ErrorMessage.LOCKED_OUT_USER.getMessage(), loginSteps.getFieldErrorMessage(), AssertDescription.LOGIN_LOCKED_USER_MESSAGE, ErrorMessage.NOT_EXPECTED_MESSAGE.getMessage());
    }

    @Test
    @Tag(AnnotationValues.LOGIN_WRONG_PASSWORD_TAG)
    @DisplayName(AnnotationValues.LOGIN_WRONG_PASSWORD_DISPLAY_NAME)
    @Description("Verification of the error messages for a correct user and wrong password")
    @Feature("Error handling")
    public void wrongPasswordTest() {
        loginSteps.login(LoginUser.STANDARD_USER.getUser(), LoginUser.WRONG_USER.getPassword());
        AssertSteps.hardAssertEquals(ErrorMessage.USER_PASSWORD_NOT_MATCH.getMessage(), loginSteps.getFieldErrorMessage(), AssertDescription.LOGIN_WRONG_PASSWORD_MESSAGE, ErrorMessage.NOT_EXPECTED_MESSAGE.getMessage());
    }
}
