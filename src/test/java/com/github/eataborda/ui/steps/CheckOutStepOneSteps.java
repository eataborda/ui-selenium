package com.github.eataborda.ui.steps;

import com.github.eataborda.ui.pages.CheckOutStepOnePage;
import com.github.eataborda.ui.pages.CheckOutStepTwoPage;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.SoftAssertions;

public class CheckOutStepOneSteps {
    private SoftAssertions assertions;

    @Step("Fill in form and continue")
    public CheckOutStepTwoPage fillInFormAndContinue(CheckOutStepOnePage checkOutStepOnePage){
        return checkOutStepOnePage.fillInFormAndContinue();
    }
}
