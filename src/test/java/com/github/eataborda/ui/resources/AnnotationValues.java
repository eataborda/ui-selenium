package com.github.eataborda.ui.resources;

public final class AnnotationValues {
    // Test class annotations
    public static final String REGRESSION_TAG = "regression";
    public static final String SMOKE_TAG = "smoke";
    public static final String STANDARD_USER_TAG = "standard-user";
    public static final String LOCKED_OUT_USER_TAG = "locked-out-user";
    public static final String PROBLEM_USER_TAG = "problem-user";

    // Test method annotations
    // standard user
    public static final String BUY_ITEMS_TAG = "buy-items";
    public static final String BUY_ITEMS_DISPLAY_NAME = "Buy items test";
    public static final String REMOVE_CART_ITEMS_TAG = "remove-cart-items";
    public static final String REMOVE_CART_DISPLAY_NAME = "Remove cart items test";
    public static final String CART_ITEMS_PERSISTENCE_TAG = "cart-items-persistence";
    public static final String CART_ITEMS_PERSISTENCE_DISPLAY_NAME = "Cart items persistence test";
    public static final String ORDER_INVENTORY_ITEMS_TAG = "order-inventory-items";
    public static final String ORDER_INVENTORY_ITEMS_DISPLAY_NAME = "Order inventory items test";
    public static final String VERIFY_CHECKOUT_DATA_TAG = "verify-checkout-data";
    public static final String VERIFY_CHECKOUT_DATA_DISPLAY_NAME = "Verify checkout user data test";
    // locked out user
    public static final String LOGIN_LOCKED_USER_TAG = "login-locked-user";
    public static final String LOGIN_LOCKED_USER_DISPLAY_NAME = "Login locked out user test";
    public static final String LOGIN_WRONG_PASSWORD_TAG = "login-wrong-password";
    public static final String LOGIN_WRONG_PASSWORD_DISPLAY_NAME = "Login wrong password test";
    // problem user
    public static final String INVENTORY_ITEM_SRC_ISSUES_TAG = "inventory-item-src-issues";
    public static final String INVENTORY_ITEM_SRC_ISSUES_DISPLAY_NAME = "Inventory items with src issues test";
    public static final String INVENTORY_FILTER_ISSUES_TAG = "inventory-filter-issues";
    public static final String INVENTORY_FILTER_ISSUES_DISPLAY_NAME = "Inventory filter issues test";
    
}
