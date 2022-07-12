package com.github.eataborda.ui.steps;

import com.github.eataborda.ui.pages.CartPage;
import com.github.eataborda.ui.pages.InventoryPage;
import com.github.eataborda.ui.pages.LoginPage;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.SoftAssertions;

public class InventorySteps {
    private SoftAssertions assertions;

    @Step("Add items and go to cart")
    public CartPage addItemsAndGoToCart(InventoryPage inventoryPage) {
        return inventoryPage.addItemsAndGotoCart();
    }

    @Step("Logout")
    public LoginPage logout(InventoryPage inventoryPage) {
        return inventoryPage.logOut();
    }
}
