package com.github.eataborda.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    private final WebDriver driver;

    private final By fieldErrorMessage = By.xpath("//h3[@data-test='error']");

    @FindBy(id = "user-name")
    WebElement userNameTextBox;

    @FindBy(id = "password")
    WebElement passwordTextBox;

    @FindBy(id = "login-button")
    WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void loginValidUser(String userName, String password) {
        sendKeys(userNameTextBox,userName);
        sendKeys(passwordTextBox,password);
        click(loginButton);
    }
    public String getFieldErrorMessage() {
        return driver.findElement(fieldErrorMessage).getText();
    }
}
