package com.github.eataborda.ui.steps;

import com.github.eataborda.ui.pages.CheckOutStepOnePage;
import io.qameta.allure.Step;

public class CheckOutStepOneSteps {
    private CheckOutStepOnePage checkOutStepOnePage;

    public CheckOutStepOneSteps(CheckOutStepOnePage checkOutStepOnePage) {
        this.checkOutStepOnePage = checkOutStepOnePage;
    }
    @Step("Fill in form and continue shopping")
    public void fillInFormAndContinueShopping(String firstName, String lastName, String postalCode) {
        checkOutStepOnePage.fillInFormAndContinueShopping(firstName, lastName, postalCode);
    }

    @Step("Continue Shopping")
    public void continueShopping() {
        checkOutStepOnePage.continueShopping();
    }

    @Step("Put first name")
    public void putFirstName(String firstName) {
        checkOutStepOnePage.putFirstName(firstName);
    }

    @Step("Put last name")
    public void putLastName(String lastName) {
        checkOutStepOnePage.putLastName(lastName);
    }
}
