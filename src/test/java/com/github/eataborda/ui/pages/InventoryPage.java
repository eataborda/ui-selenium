package com.github.eataborda.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends BasePage {
    private final WebDriver driver;

    private HeaderElement headerElement;

    private final By productSortContainer = By.className("product_sort_container");
    private final By inventoryItemName = By.xpath("//div[@data-test='inventory-item-name']");

    public InventoryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        headerElement = new HeaderElement(this.driver);
        PageFactory.initElements(this.driver, this);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void addItem(String itemName) {
        addItemByNameToCart(itemName);
    }

    public void addItemByNameToCart(String itemName) {
        click(driver.findElement(By.xpath("//div[@data-test='inventory-item-name' and text()='" + itemName + "']//ancestor::div[@data-test='inventory-item-description']//button[text()=\"Add to cart\"]")));
    }

    // Options: az, za, lohi, hilo
    public void sortItemsByValue(String value) {
        selectByValue(driver.findElement(productSortContainer), value);
    }

    public void sortItemsByVisibleText(String visibleText) {
        selectByVisibleText(driver.findElement(productSortContainer), visibleText);
    }

    public List<String> getItemNameList() {
        List<String> itemNameList = new ArrayList<>();
        List<WebElement> nameList = driver.findElements(inventoryItemName);
        for (WebElement itemName : nameList) {
            itemNameList.add(itemName.getText());
        }
        return itemNameList;
    }

    public Integer getNumberOfRepeatedImageSrc(String src) {
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
