package com.github.eataborda.ui.steps;

import com.github.eataborda.ui.pages.CheckOutCompletePage;
import com.github.eataborda.ui.pages.InventoryPage;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.SoftAssertions;

public class CheckOutCompleteSteps {
    private SoftAssertions assertions;

    @Step("Submit back home")
    public InventoryPage submitBackHome(CheckOutCompletePage checkOutCompletePage) {
        return checkOutCompletePage.submitBackHome();
    }
}
