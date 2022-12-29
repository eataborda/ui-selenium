package com.github.eataborda.ui.steps;

import com.github.eataborda.ui.pages.CheckOutCompletePage;
import com.github.eataborda.ui.pages.CheckOutStepTwoPage;
import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;

public class CheckOutStepTwoSteps extends UIInteractions {
    @Step("Submit finish")
    public CheckOutCompletePage submitFinish(CheckOutStepTwoPage checkOutStepTwoPage) {
        return checkOutStepTwoPage.submitFinish();
    }
}
