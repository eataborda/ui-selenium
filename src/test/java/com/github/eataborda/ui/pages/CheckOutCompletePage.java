package com.github.eataborda.ui.pages;

import com.github.eataborda.ui.enums.URLs;
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
        if (!driver.getCurrentUrl().equals(URLs.CHECKOUT_COMPLETE.getValue())) {
            throw new IllegalStateException("Incorrect Cart page of logged in user," +
                    " current page is: " + driver.getCurrentUrl());
        }
        PageFactory.initElements(this.driver, this);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public InventoryPage submitBackHome() {
        takeScreenshot();
        backHomeButton.click();
        return new InventoryPage(driver);
    }
}
