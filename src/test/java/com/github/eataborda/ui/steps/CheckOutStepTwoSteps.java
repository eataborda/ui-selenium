package com.github.eataborda.ui.steps;

import com.github.eataborda.ui.pages.CheckOutStepTwoPage;
import io.qameta.allure.Step;

public class CheckOutStepTwoSteps {
    private CheckOutStepTwoPage checkOutStepTwoPage;

    public CheckOutStepTwoSteps(CheckOutStepTwoPage checkOutStepTwoPage) {
        this.checkOutStepTwoPage = checkOutStepTwoPage;
    }

    @Step("Submit Finish")
    public void submitFinish() {
        checkOutStepTwoPage.submitFinish();
    }
}
