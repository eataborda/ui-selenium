package com.github.eataborda.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutStepOnePage extends BasePage {
    private final WebDriver driver;

    @FindBy(id = "first-name")
    @CacheLookup
    WebElement firstNameTextBox;

    @FindBy(id = "last-name")
    @CacheLookup
    WebElement lastNameTextBox;

    @FindBy(id = "postal-code")
    @CacheLookup
    WebElement postalCodeTextBox;

    @FindBy(id = "cancel")
    @CacheLookup
    WebElement cancelButton;

    @FindBy(id = "continue")
    @CacheLookup
    WebElement continueButton;

    public CheckOutStepOnePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void fillInFormAndContinue() {
        sendKeys(firstNameTextBox, "Alexander");
        sendKeys(lastNameTextBox, "Kepler");
        sendKeys(postalCodeTextBox, "1234");
        click(continueButton);
    }
}
