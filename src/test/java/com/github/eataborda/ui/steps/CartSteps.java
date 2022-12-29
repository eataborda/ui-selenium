package com.github.eataborda.ui.steps;

import com.github.eataborda.ui.pages.CartPage;
import com.github.eataborda.ui.pages.CheckOutStepOnePage;
import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;

public class CartSteps extends UIInteractions {
    @Step("Submit checkout")
    public CheckOutStepOnePage submitCheckout(CartPage cartPage) {
        return cartPage.submitCheckout();
    }
}
