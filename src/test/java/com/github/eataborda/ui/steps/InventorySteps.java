package com.github.eataborda.ui.steps;

import com.github.eataborda.ui.pages.InventoryPage;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

public class InventorySteps {
    private InventoryPage inventoryPage;

    public InventorySteps(InventoryPage inventoryPage) {
        this.inventoryPage = inventoryPage;
    }

    @Step("Add items and go to Cart")
    public void addItemsAndGoToCart(ArrayList<String> itemNameList) {
        for (String itemName : itemNameList) {
            addItem(itemName);
        }
        goToCart();
    }

    @Step("Add item to cart")
    public void addItem(String itemName) {
        inventoryPage.addItem(itemName);
    }

    @Step("Go to Cart")
    public void goToCart() {
        inventoryPage.goToCart();
    }


    public List<String> getItemNameList() {
        return inventoryPage.getItemNameList();
    }

    @Step("Sort items by value: {0}")
    public void sortItemsByValue(String value) {
        inventoryPage.sortItemsByValue(value);
    }

    @Step("Sort items by visible text: {0}")
    public void sortItemsByVisibleText(String visibleText) {
        inventoryPage.sortItemsByVisibleText(visibleText);
    }

    @Step("Logout")
    public void logout() {
        inventoryPage.logout();
    }
}
