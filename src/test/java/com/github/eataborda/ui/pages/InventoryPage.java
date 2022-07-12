package com.github.eataborda.ui.pages;

import com.github.eataborda.ui.enums.URLs;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage extends BasePage {
    private final WebDriver driver;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    @CacheLookup
    WebElement backPackAddToCartButton;

    @FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
    @CacheLookup
    WebElement boltTShirtAddToCartButton;

    @FindBy(id = "add-to-cart-sauce-labs-fleece-jacket")
    @CacheLookup
    WebElement fleeceJacketAddToCartButton;

    public InventoryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        if (!driver.getCurrentUrl().equals(URLs.INVENTORY.getValue())) {
            throw new IllegalStateException("Incorrect Inventory page of logged in user," +
                    " current page is: " + driver.getCurrentUrl());
        }
        PageFactory.initElements(this.driver, this);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void addItemToCart(WebElement element) {
        element.click();
    }

    public CartPage gotoCart() {
        return new HeaderElement(driver).submitShoppingCart();
    }

    public CartPage addItemsAndGotoCart() {
        addItemToCart(backPackAddToCartButton);
        addItemToCart(boltTShirtAddToCartButton);
        addItemToCart(fleeceJacketAddToCartButton);
        takeScreenshot();
        return gotoCart();
    }

    public LoginPage logOut() {
        return new HeaderElement(driver).selectLogOutOption();
    }
}
