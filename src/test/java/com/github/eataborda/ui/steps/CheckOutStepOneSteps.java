package com.github.eataborda.ui.steps;

import com.github.eataborda.ui.pages.CheckOutStepOnePage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractions;

public class CheckOutStepOneSteps extends UIInteractions {
    @Step("Fill in form and continue")
    public void fillInFormAndContinue(CheckOutStepOnePage checkOutStepOnePage) {
        checkOutStepOnePage.fillInFormAndContinue();
    }
}
