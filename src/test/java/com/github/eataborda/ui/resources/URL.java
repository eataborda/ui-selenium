package com.github.eataborda.ui.resources;

public enum URL {
    LOGIN("https://www.saucedemo.com/"),
    INVENTORY("https://www.saucedemo.com/inventory.html"),
    CART("https://www.saucedemo.com/cart.html"),
    CHECKOUT_STEP_ONE("https://www.saucedemo.com/checkout-step-one.html"),
    CHECKOUT_STEP_TWO("https://www.saucedemo.com/checkout-step-two.html"),
    CHECKOUT_COMPLETE("https://www.saucedemo.com/checkout-complete.html");

    private final String value;

    URL(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
