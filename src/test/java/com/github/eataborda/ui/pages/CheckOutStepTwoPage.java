package com.github.eataborda.ui.pages;

import com.github.eataborda.ui.enums.URLs;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutStepTwoPage extends BasePage {
    private final WebDriver driver;

    @FindBy(id = "finish")
    WebElement finishButton;

    public CheckOutStepTwoPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        if (!driver.getCurrentUrl().equals(URLs.CHECKOUT_STEP_TWO.getValue())) {
            throw new IllegalStateException("Incorrect Cart page of logged in user," +
                    " current page is: " + driver.getCurrentUrl());
        }
        PageFactory.initElements(this.driver, this);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public CheckOutCompletePage submitFinish() {
        takeScreenshot();
        scrollPageEnd();
        waitForElement(finishButton);
        takeScreenshot();
        finishButton.click();
        return new CheckOutCompletePage(driver);
    }
}
