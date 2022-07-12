package com.github.eataborda.ui.steps;

import com.github.eataborda.ui.pages.InventoryPage;
import com.github.eataborda.ui.pages.LoginPage;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.SoftAssertions;

public class LoginSteps {
    private SoftAssertions assertions;

    @Step("Login valid user")
    public InventoryPage loginValidUser(LoginPage loginPage){
        return loginPage.loginValidUser("standard_user","secret_sauce");
    }
}
