package com.github.eataborda.ui.enums;

public enum Comments {
    ERROR_NOT_EXPECTED_URL("Current page URL doesn't have the expected value");

    private final String value;

    Comments(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
