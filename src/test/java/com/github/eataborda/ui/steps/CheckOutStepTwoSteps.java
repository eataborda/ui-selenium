package com.github.eataborda.ui.steps;

import com.github.eataborda.ui.pages.CheckOutCompletePage;
import com.github.eataborda.ui.pages.CheckOutStepTwoPage;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.SoftAssertions;

public class CheckOutStepTwoSteps {
    private SoftAssertions assertions;

    @Step("Submit finish")
    public CheckOutCompletePage submitFinish(CheckOutStepTwoPage checkOutStepTwoPage){
        return checkOutStepTwoPage.submitFinish();
    }
}
