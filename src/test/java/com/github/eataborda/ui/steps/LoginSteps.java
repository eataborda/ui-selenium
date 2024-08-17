package com.github.eataborda.ui.steps;

import com.github.eataborda.ui.pages.LoginPage;
import io.qameta.allure.Step;

public class LoginSteps {
    private LoginPage loginPage;

    public LoginSteps(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    @Step("Login with valid user")
    public void login(String userName, String password) {
        loginPage.login(userName,password);
    }

    public String getCurrentUrl() {
        return loginPage.getCurrentUrl();
    }

    public String getFieldErrorMessage(){
        return loginPage.getFieldErrorMessage();
    }
}
