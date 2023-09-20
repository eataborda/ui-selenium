package com.github.eataborda.ui.pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;

public class BasePage {
    private final WebDriver driver;
    private final String screenshots = getParameterValue("screenshots");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public FluentWait<WebDriver> getFluentWait() {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(10));
        fluentWait.pollingEvery(Duration.ofMillis(250));
        fluentWait.ignoring(NoSuchElementException.class);
        return fluentWait;
    }

    public void sendKeys(WebElement webElement, String text) {
        getFluentWait().until(ExpectedConditions.visibilityOf(webElement));
        webElement.sendKeys(text);
    }

    public void click(WebElement webElement) {
        getFluentWait().until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    public void selectByValue(WebElement webElement, String value) {
        getFluentWait().until(ExpectedConditions.elementToBeClickable(webElement));
        Select select = new Select(webElement);
        select.selectByValue(value);
    }

    public void scrollPageDown() {
        new Actions(driver)
                .sendKeys(Keys.PAGE_DOWN).build().perform();
    }

    public void scrollPageEnd() {
        new Actions(driver)
                .sendKeys(Keys.END).perform();
    }

    private void takeSeleniumScreenshot() {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String currentDate = String.valueOf(System.currentTimeMillis());
        String fileExtension = ".png";
        String filePath = Paths.get("").toAbsolutePath().toString().concat("/evidence");
        String fileName = "\\evidence_".concat(currentDate).concat(fileExtension);
        try {
            FileUtils.copyFile(scrFile, new File(filePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getEnvironmentVariable(String key) {
        return System.getenv(key);
    }

    private String getParameterValue(String key) {
        return System.getProperty(key);
    }

}
