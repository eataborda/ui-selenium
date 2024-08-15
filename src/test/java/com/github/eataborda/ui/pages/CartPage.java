package com.github.eataborda.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage {
    private final WebDriver driver;
    private final By cartItem = By.xpath("//div[@class='cart_item']");

    @FindBy(id = "continue-shopping")
    @CacheLookup
    WebElement continueShopping;

    @FindBy(id = "checkout")
    @CacheLookup
    WebElement checkoutButton;

    @FindBy(id = "remove-sauce-labs-backpack")
    @CacheLookup
    WebElement removeSauceLabsBackpack;

    @FindBy(id = "remove-sauce-labs-fleece-jacket")
    @CacheLookup
    WebElement removeSauceLabsFleeceJacket;

    @FindBy(id = "remove-sauce-labs-bolt-t-shirt")
    @CacheLookup
    WebElement removeSauceLabsBoltTShirt;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void checkout() {
        click(checkoutButton);
    }

    public void removeAllItems() {
        click(removeSauceLabsBackpack);
        click(removeSauceLabsBoltTShirt);
        click(removeSauceLabsFleeceJacket);
    }

    public void removeItem(String itemName) {
        click(driver.findElement(By.xpath("//div[@data-test='inventory-item-name' and text()='" + itemName + "']//ancestor::div[@class='cart_item_label']//button[text()=\"Remove\"]")));
    }

    public Integer getNumberOfItems() {
        return driver.findElements(cartItem).size();
    }

    public void continueShopping() {
        click(continueShopping);
    }
}
