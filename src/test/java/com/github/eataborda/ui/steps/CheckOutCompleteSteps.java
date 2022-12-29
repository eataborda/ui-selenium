package com.github.eataborda.ui.steps;

import com.github.eataborda.ui.pages.CheckOutCompletePage;
import com.github.eataborda.ui.pages.InventoryPage;
import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;

public class CheckOutCompleteSteps extends UIInteractions {
    @Step("Submit back home")
    public InventoryPage submitBackHome(CheckOutCompletePage checkOutCompletePage) {
        return checkOutCompletePage.submitBackHome();
    }
}
