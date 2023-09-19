package com.github.eataborda.ui.steps;

import com.github.eataborda.ui.pages.InventoryPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractions;

public class InventorySteps extends UIInteractions {
    @Step("Add items and go to cart")
    public void addItemsAndGoToCart(InventoryPage inventoryPage) {
        inventoryPage.addItemsAndGotoCart();
    }

    @Step("Logout")
    public void logout(InventoryPage inventoryPage) {
        inventoryPage.logOut();
    }
}
