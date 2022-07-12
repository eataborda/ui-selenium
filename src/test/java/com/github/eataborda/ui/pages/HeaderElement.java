package com.github.eataborda.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderElement extends BasePage {
    private final WebDriver driver;

    @FindBy(id = "react-burger-menu-btn")
    @CacheLookup
    WebElement burgerMenuButton;

    @FindBy(id = "shopping_cart_container")
    @CacheLookup
    WebElement shoppingCartLink;

    @FindBy(id = "inventory_sidebar_link")
    @CacheLookup
    WebElement allItemsLink;

    @FindBy(id = "logout_sidebar_link")
    @CacheLookup
    WebElement logoutLink;

    @FindBy(id = "about_sidebar_link")
    @CacheLookup
    WebElement resetAppStateLink;

    public HeaderElement(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void clickBurgerMenu() {
        burgerMenuButton.click();
    }

    public InventoryPage selectAllItemsOption() {
        clickBurgerMenu();
        allItemsLink.click();
        return new InventoryPage(driver);
    }

    public LoginPage selectLogOutOption() {
        takeScreenshot();
        clickBurgerMenu();
        waitForElement(logoutLink);
        takeScreenshot();
        logoutLink.click();
        return new LoginPage(driver);
    }

    public LoginPage selectResetAppStateOption() {
        clickBurgerMenu();
        resetAppStateLink.click();
        return new LoginPage(driver);
    }

    public CartPage submitShoppingCart() {
        shoppingCartLink.click();
        return new CartPage(driver);
    }

}
