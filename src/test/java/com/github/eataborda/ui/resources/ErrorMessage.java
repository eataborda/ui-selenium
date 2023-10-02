package com.github.eataborda.ui.resources;

public enum ErrorMessage {
    NOT_EXPECTED_URL("Page doesn't have the expected URL"),
    ITEMS_NOT_COMPLETELY_REMOVED("Items were not complete removed form cart"),
    ITEMS_INCORRECTLY_PERSISTED_IN_CART("Items were not persisted correctly in cart"),
    ITEMS_HAVE_EXPECTED_IMAGE_SRC("Inventory items have the correct image src"),
    ITEMS_HAVE_REPEATED_IMAGE_SRC("Some inventory images have repeated values"),
    NOT_EXPECTED_MESSAGE("Message doesn't have the expected value"),
    FIRST_NAME_REQUIRED("Error: First Name is required"),
    SECOND_NAME_REQUIRED("Error: Last Name is required"),
    POSTAL_CODE_REQUIRED("Error: Postal Code is required"),
    LOCKED_OUT_USER("Epic sadface: Sorry, this user has been locked out."),
    USER_PASSWORD_NOT_MATCH("Epic sadface: Username and password do not match any user in this service");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
