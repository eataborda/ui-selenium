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

    public void selectAllItemsOption() {
        click(burgerMenuButton);
        click(allItemsLink);
    }

    public void selectLogOutOption() {
        click(burgerMenuButton);
        click(logoutLink);
    }

    public void selectResetAppStateOption() {
        click(burgerMenuButton);
        click(resetAppStateLink);
    }

    public void submitShoppingCart() {
        click(shoppingCartLink);
    }

}
