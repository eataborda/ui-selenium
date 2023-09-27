package com.github.eataborda.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends BasePage {
    private final WebDriver driver;

    private HeaderElement headerElement;

    private final By productSortContainer = By.className("product_sort_container");
    private final By inventoryItemName = By.xpath("//div[@class='inventory_item_name']");
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

    public void addItemsAndGoToCart() {
        addItems();
        goToCart();
    }

    public void addItems() {
        click(backPackAddToCartButton);
        click(boltTShirtAddToCartButton);
        click(fleeceJacketAddToCartButton);
    }

    // Options: az, za, lohi, hilo
    public void sortItemsByValue(String value) {
        selectByValue(driver.findElement(productSortContainer), value);
    }

    public List<String> getItemNameList() {
        List<String> itemNameList = new ArrayList<>();
        List<WebElement> nameList = driver.findElements(inventoryItemName);
        for (WebElement itemName : nameList) {
            itemNameList.add(itemName.getText());
        }
        return itemNameList;
    }

    public Integer getNumberOfRepeatedImageSrc(String src){
        String inventoryItemImageXpath = "//img[@src='".concat(src).concat("']");
        return driver.findElements(By.xpath(inventoryItemImageXpath)).size();
    }

    public void goToCart() {
        headerElement.submitShoppingCart();
    }

    public void logout() {
        headerElement.selectLogOutOption();
    }
}
