package com.github.eataborda.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage extends BasePage {
    private final WebDriver driver;
    private final HeaderElement headerElement;

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
        headerElement = new HeaderElement(this.driver);
        PageFactory.initElements(this.driver, this);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void addItemsAndGotoCart() {
        click(backPackAddToCartButton);
        click(boltTShirtAddToCartButton);
        click(fleeceJacketAddToCartButton);
        headerElement.submitShoppingCart();
    }

    public void logOut() {
        headerElement.selectLogOutOption();
    }
}
