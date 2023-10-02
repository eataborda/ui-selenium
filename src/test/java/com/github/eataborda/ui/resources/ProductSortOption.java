package com.github.eataborda.ui.resources;

public enum ProductSortOption {
    A_TO_Z_OPTION("az","Name (A to Z)"),
    Z_TO_A_OPTION("za","Name (Z to A)"),
    LO_TO_HI_OPTION("lohi","Price (low to high)"),
    HI_TO_LO_OPTION("hilo","Price (high to low)");
    private final String value;
    private final String visibleText;

    ProductSortOption(String optionValue, String optionVisibleText) {
        this.value = optionValue;
        this.visibleText = optionVisibleText;
    }

    public String getValue() {
        return value;
    }

    public String getVisibleText() {
        return visibleText;
    }
}
