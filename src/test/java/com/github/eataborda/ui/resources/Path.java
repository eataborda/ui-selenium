package com.github.eataborda.ui.resources;

public enum Path {
    WRONG_IMAGE_SRC("/static/media/sl-404.168b1cce.jpg");
    private final String value;

    Path(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
