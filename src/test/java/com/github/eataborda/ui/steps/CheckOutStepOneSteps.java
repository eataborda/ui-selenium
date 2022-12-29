package com.github.eataborda.ui.steps;

import com.github.eataborda.ui.pages.CheckOutStepOnePage;
import com.github.eataborda.ui.pages.CheckOutStepTwoPage;
import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;

public class CheckOutStepOneSteps extends UIInteractions {
    @Step("Fill in form and continue")
    public CheckOutStepTwoPage fillInFormAndContinue(CheckOutStepOnePage checkOutStepOnePage) {
        return checkOutStepOnePage.fillInFormAndContinue();
    }
}
