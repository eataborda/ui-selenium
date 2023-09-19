package com.github.eataborda.ui.steps;

import com.github.eataborda.ui.pages.CheckOutCompletePage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractions;

public class CheckOutCompleteSteps extends UIInteractions {
    @Step("Submit back home")
    public void submitBackHome(CheckOutCompletePage checkOutCompletePage) {
        checkOutCompletePage.submitBackHome();
    }
}
