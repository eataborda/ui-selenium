package com.github.eataborda.ui.pages;

import com.github.eataborda.ui.enums.URLs;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage {
    private final WebDriver driver;

    @FindBy(id = "checkout")
    @CacheLookup
    WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        if (!driver.getCurrentUrl().equals(URLs.CART.getValue())) {
            throw new IllegalStateException("Incorrect Cart page of logged in user," +
                    " current page is: " + driver.getCurrentUrl());
        }
        PageFactory.initElements(this.driver, this);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public CheckOutStepOnePage submitCheckout() {
        takeScreenshot();
        checkoutButton.click();
        return new CheckOutStepOnePage(driver);
    }
}
