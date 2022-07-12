package com.github.eataborda.ui.pages;

import com.github.eataborda.ui.enums.URLs;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    private final WebDriver driver;

    @FindBy(id = "user-name")
    @CacheLookup
    WebElement userNameTextBox;

    @FindBy(id = "password")
    @CacheLookup
    WebElement passwordTextBox;

    @FindBy(id = "login-button")
    @CacheLookup
    WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        if (!driver.getCurrentUrl().equals(URLs.LOGIN.getValue())) {
            throw new IllegalStateException("Incorrect Inventory page of logged in user," +
                    " current page is: " + driver.getCurrentUrl());
        }
        PageFactory.initElements(this.driver, this);
        takeScreenshot();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void typeUserName(String userName) {
        userNameTextBox.sendKeys(userName);
    }

    public void typePassword(String password) {
        passwordTextBox.sendKeys(password);
    }

    public InventoryPage submitLogin() {
        loginButton.click();
        return new InventoryPage(driver);
    }

    public InventoryPage loginValidUser(String userName, String password) {
        typeUserName(userName);
        typePassword(password);
        takeScreenshot();
        return submitLogin();
    }
}
