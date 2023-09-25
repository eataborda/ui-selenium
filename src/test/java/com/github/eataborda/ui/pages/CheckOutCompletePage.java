package com.github.eataborda.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutCompletePage extends BasePage {
    private final WebDriver driver;

    @FindBy(id = "back-to-products")
    WebElement backHomeButton;

    public CheckOutCompletePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void submitBackHome() {
        longWaitAndClick(backHomeButton);
    }
}
