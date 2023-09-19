package com.github.eataborda.ui.steps;

import com.github.eataborda.ui.pages.CheckOutStepTwoPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractions;

public class CheckOutStepTwoSteps extends UIInteractions {
    @Step("Submit finish")
    public void submitFinish(CheckOutStepTwoPage checkOutStepTwoPage) {
        checkOutStepTwoPage.submitFinish();
    }
}
