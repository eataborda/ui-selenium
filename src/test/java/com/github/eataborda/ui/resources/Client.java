package com.github.eataborda.ui.resources;

public enum Client {
    EXAMPLE_CLIENT("Alexander","Kepler","1234");

    private final String firstName;
    private final String lastName;
    private final String postalCode;

    Client(String firstName, String lastName, String postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
