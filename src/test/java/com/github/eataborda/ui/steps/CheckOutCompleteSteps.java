package com.github.eataborda.ui.steps;

import com.github.eataborda.ui.pages.CheckOutCompletePage;
import io.qameta.allure.Step;

public class CheckOutCompleteSteps {
    private CheckOutCompletePage checkOutCompletePage;

    public CheckOutCompleteSteps(CheckOutCompletePage checkOutCompletePage) {
        this.checkOutCompletePage = checkOutCompletePage;
    }

    @Step("Submit Back Home")
    public void submitBackHome() {
        checkOutCompletePage.submitBackHome();
    }
}
