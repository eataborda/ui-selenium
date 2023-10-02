package com.github.eataborda.ui.pages;

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
        fluentWait.pollingEvery(Duration.ofMillis(250));
        fluentWait.ignoring(NoSuchElementException.class);
        return fluentWait;
    }

    public FluentWait<WebDriver> getLongFluentWait() {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(10));
        fluentWait.pollingEvery(Duration.ofMillis(350));
        fluentWait.ignoring(NoSuchElementException.class);
        return fluentWait;
    }

    public void sendKeys(WebElement webElement, String text) {
        getFluentWait().until(ExpectedConditions.visibilityOf(webElement));
        webElement.sendKeys(text);
        logger.info("Send '" + text + "' to " + webElement.getAttribute("id"));
    }

    public void click(WebElement webElement) {
        getFluentWait().until(ExpectedConditions.elementToBeClickable(webElement));
        logger.info("Click on " + webElement.getAttribute("id"));
        webElement.click();
    }

    public void selectByValue(WebElement webElement, String value) {
        getFluentWait().until(ExpectedConditions.elementToBeClickable(webElement));
        Select select = new Select(webElement);
        logger.info("Select '" + value + "' from " + webElement.getAttribute("class"));
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

    private String getEnvironmentVariable(String key) {
        return System.getenv(key);
    }

    private String getParameterValue(String key) {
        return System.getProperty(key);
    }

}
