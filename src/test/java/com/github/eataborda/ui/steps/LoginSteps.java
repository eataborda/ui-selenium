package com.github.eataborda.ui.steps;

import com.github.eataborda.ui.pages.InventoryPage;
import com.github.eataborda.ui.pages.LoginPage;
import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;

public class LoginSteps extends UIInteractions {
    @Step("Login valid user")
    public InventoryPage loginValidUser(LoginPage loginPage) {
        return loginPage.loginValidUser("standard_user", "secret_sauce");
    }
}
