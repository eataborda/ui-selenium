package com.github.eataborda.ui.steps;

import com.github.eataborda.ui.pages.LoginPage;
import io.qameta.allure.Step;

public class LoginSteps {
    private LoginPage loginPage;

    public LoginSteps(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    @Step("Login with valid user")
    public void loginValidUser(String userName, String password) {
        loginPage.loginValidUser(userName,password);
    }
}
