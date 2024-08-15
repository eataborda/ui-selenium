package com.github.eataborda.ui.pages;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

public class BasePage {
    private final WebDriver driver;

    private final Logger logger = LoggerFactory.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public FluentWait<WebDriver> getFluentWait() {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(10));
        fluentWait.pollingEvery(Duration.ofMillis(300));
        fluentWait.ignoring(NoSuchElementException.class);
        return fluentWait;
    }

    public FluentWait<WebDriver> getLongFluentWait() {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(10));
        fluentWait.pollingEvery(Duration.ofMillis(400));
        fluentWait.ignoring(NoSuchElementException.class);
        return fluentWait;
    }

    public void waitUntilElementIsVisible(WebElement webElement){
        getFluentWait().until(ExpectedConditions.visibilityOf(webElement));
    }

    public void sendKeys(WebElement webElement, String text) {
        getFluentWait().until(ExpectedConditions.visibilityOf(webElement));
        webElement.sendKeys(text);
        String message = "Send '" + text + "' to " + webElement.getAttribute("placeholder");
        logger.info(message);
        Allure.step(message);
    }

    public void click(WebElement webElement) {
        getFluentWait().until(ExpectedConditions.elementToBeClickable(webElement));
        String message = "Click on " + webElement.getAttribute("id");
        logger.info(message);
        Allure.step(message);
        webElement.click();
    }

    public void selectByValue(WebElement webElement, String value) {
        getFluentWait().until(ExpectedConditions.elementToBeClickable(webElement));
        Select select = new Select(webElement);
        String message = "Select '" + value + "' from " + webElement.getAttribute("class");
        logger.info(message);
        Allure.step(message);
        select.selectByValue(value);
    }

    public void selectByVisibleText(WebElement webElement, String visibleText){
        getFluentWait().until(ExpectedConditions.elementToBeClickable(webElement));
        Select select = new Select(webElement);
        String message = "Select '" + visibleText + "' from " + webElement.getAttribute("class");
        logger.info(message);
        Allure.step(message);
        select.selectByVisibleText(visibleText);
    }

    public void scrollPageDown() {
        new Actions(driver)
                .sendKeys(Keys.PAGE_DOWN).build().perform();
    }

    public void scrollPageEnd() {
        new Actions(driver)
                .sendKeys(Keys.END).perform();
    }

    private void takeScreenshot() {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-hhmm-sS");
        String currentDate = dateFormat.format(date);
        String fileExtension = ".png";
        String filePath = Paths.get("").toAbsolutePath().toString().concat("/evidence");
        String fileName = "\\evidence_".concat(currentDate).concat(fileExtension);
        try {
            FileUtils.copyFile(scrFile, new File(filePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    private String getEnvironmentVariable(String key) {
        return System.getenv(key);
    }

    private String getParameterValue(String key) {
        return System.getProperty(key);
    }

}
