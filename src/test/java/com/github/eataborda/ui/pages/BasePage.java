package com.github.eataborda.ui.pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageComponent;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;

public class BasePage extends PageComponent {
    private final WebDriver driver;
    private final String screenshots = getParameterValue("screenshots");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(300));
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(element),
                ExpectedConditions.elementToBeClickable(element)
        ));
    }

    public void takeScreenshot() {
        switch (screenshots) {
            case "no":
                // Do nothing
                break;
            case "selenium":
                takeSeleniumScreenshot();
                break;
            case "serenity":
                Serenity.takeScreenshot();
                break;
            default:
                break;
        }
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
        String filePath = Paths.get("").toAbsolutePath().toString().concat("/target/site/evidence");
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
