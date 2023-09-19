package com.github.eataborda.ui.steps;

import com.github.eataborda.ui.pages.LoginPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractions;

public class LoginSteps extends UIInteractions {
    @Step("Login valid user")
    public void loginValidUser(LoginPage loginPage) {
        loginPage.loginValidUser("standard_user", "secret_sauce");
    }
}
