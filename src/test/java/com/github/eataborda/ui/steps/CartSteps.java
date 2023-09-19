package com.github.eataborda.ui.steps;

import com.github.eataborda.ui.pages.CartPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractions;

public class CartSteps extends UIInteractions {
    @Step("Submit checkout")
    public void submitCheckout(CartPage cartPage) {
        cartPage.submitCheckout();
    }
}
