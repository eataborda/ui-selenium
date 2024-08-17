package com.github.eataborda.ui.steps;

import com.github.eataborda.ui.pages.CartPage;
import io.qameta.allure.Step;

import java.util.ArrayList;

public class CartSteps {
    private CartPage cartPage;

    public CartSteps(CartPage cartPage) {
        this.cartPage = cartPage;
    }

    @Step("Checkout")
    public void checkout() {
        cartPage.checkout();
    }

    @Step("Remove all items")
    public void removeAllItems(ArrayList<String> itemNameList) {
        for (String itemName : itemNameList) {
            removeItem(itemName);
        }
    }

    @Step("Remove item")
    public void removeItem(String itemName) {
        cartPage.removeItem(itemName);
    }

    @Step("Continue Shopping")
    public void continueShopping() {
        cartPage.continueShopping();
    }

    public Integer getNumberOfItems() {
        return cartPage.getNumberOfItems();
    }
}
