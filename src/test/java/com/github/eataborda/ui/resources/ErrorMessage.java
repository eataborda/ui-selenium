package com.github.eataborda.ui.resources;

public enum ErrorMessage {
    NOT_EXPECTED_URL("Page doesn't have the expected URL"),
    ITEMS_NOT_COMPLETELY_REMOVED("Items were not complete removed form cart"),
    ITEMS_INCORRECTLY_PERSISTED_IN_CART("Items were not persisted correctly in cart"),
    FIRST_NAME_REQUIRED("Error: First Name is required"),
    SECOND_NAME_REQUIRED("Error: Last Name is required"),
    POSTAL_CODE_REQUIRED("Error: Postal Code is required");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
