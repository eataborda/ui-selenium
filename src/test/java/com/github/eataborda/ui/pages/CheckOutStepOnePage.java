package com.github.eataborda.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutStepOnePage extends BasePage {
    private final WebDriver driver;

    private final By fieldErrorMessage = By.xpath("//h3[@data-test='error']");

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

    public void fillInFormAndContinueShopping(String firstName, String lastName, String postalCode) {
        fillInForm(firstName, lastName, postalCode);
        continueShopping();
    }

    public void fillInForm(String firstName, String lastName, String postalCode) {
        putFirstName(firstName);
        putLastName(lastName);
        putPostalCode(postalCode);
    }

    public void putFirstName(String firstName) {
        sendKeys(firstNameTextBox, firstName);
    }

    public void putLastName(String lastName) {
        sendKeys(lastNameTextBox, lastName);
    }

    public void putPostalCode(String postalCode) {
        sendKeys(postalCodeTextBox, postalCode);
    }

    public void continueShopping() {
        click(continueButton);
    }

    public String getFieldErrorMessage() {
        return driver.findElement(fieldErrorMessage).getText();
    }
}
