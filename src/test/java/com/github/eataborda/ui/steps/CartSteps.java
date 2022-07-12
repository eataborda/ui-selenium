package com.github.eataborda.ui.steps;

import com.github.eataborda.ui.pages.CartPage;
import com.github.eataborda.ui.pages.CheckOutStepOnePage;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.SoftAssertions;

public class CartSteps {
    private SoftAssertions assertions;

    @Step("Submit checkout")
    public CheckOutStepOnePage submitCheckout(CartPage cartPage){
        return cartPage.submitCheckout();
    }
}
