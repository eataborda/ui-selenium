package com.github.eataborda.ui.pages;

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
        PageFactory.initElements(this.driver, this);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void submitFinish() {
        scrollPageEnd();
        longWaitAndClick(finishButton);
    }
}
