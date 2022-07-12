package com.github.eataborda.ui.pages;

import com.github.eataborda.ui.enums.URLs;
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
        if (!driver.getCurrentUrl().equals(URLs.CHECKOUT_STEP_ONE.getValue())) {
            throw new IllegalStateException("Incorrect Cart page of logged in user," +
                    " current page is: " + driver.getCurrentUrl());
        }
        PageFactory.initElements(this.driver,this);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void typeFirstName() {
        firstNameTextBox.sendKeys("Alexander");
    }

    public void typeLastName() {
        lastNameTextBox.sendKeys("Kepler");
    }

    public void typePostalCode() {
        postalCodeTextBox.sendKeys("Kepler");
    }

    public CheckOutStepTwoPage submitContinue() {
        continueButton.click();
        return new CheckOutStepTwoPage(driver);
    }

    public CheckOutStepTwoPage fillInFormAndContinue() {
        typeFirstName();
        typeLastName();
        typePostalCode();
        takeScreenshot();
        return submitContinue();
    }
}
