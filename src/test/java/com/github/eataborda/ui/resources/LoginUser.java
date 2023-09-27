package com.github.eataborda.ui.resources;

public enum LoginUser {
    STANDARD_USER("standard_user","secret_sauce");

    private final String user;
    private final String password;

    LoginUser(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }
    public String getPassword(){return password;}
}
