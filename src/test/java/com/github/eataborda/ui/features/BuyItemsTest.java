package com.github.eataborda.ui.features;

import com.github.eataborda.ui.driver.WebDriverConfig;
import com.github.eataborda.ui.enums.URLs;
import com.github.eataborda.ui.pages.*;
import com.github.eataborda.ui.steps.*;
import net.serenitybdd.annotations.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
@WithTagValuesOf({"smoke", "regression", "workflow"})
public class BuyItemsTest {

    @Managed
    public WebDriver driver;

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckOutStepOnePage checkOutStepOnePage;

    private CheckOutStepTwoPage checkOutStepTwoPage;

    private CheckOutCompletePage checkOutCompletePage;

    private InventoryPage inventoryPageAfterPurchase;

    @Steps
    private LoginSteps loginSteps;

    @Steps
    private InventorySteps inventorySteps;

    @Steps
    private CartSteps cartSteps;

    @Steps
    private CheckOutStepOneSteps checkOutStepOneSteps;

    @Steps
    private CheckOutStepTwoSteps checkOutStepTwoSteps;

    @Steps
    private CheckOutCompleteSteps checkOutCompleteSteps;

    @Before
    public void setupTest() {
        WebDriverConfig.setupTest(driver);
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkOutStepOnePage = new CheckOutStepOnePage(driver);
        checkOutStepTwoPage = new CheckOutStepTwoPage(driver);
        checkOutCompletePage = new CheckOutCompletePage(driver);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Title("General flow - Buy items")
    public void testBuyItems() {
        loginSteps.loginValidUser(loginPage);
        inventorySteps.addItemsAndGoToCart(inventoryPage);
        cartSteps.submitCheckout(cartPage);
        checkOutStepOneSteps.fillInFormAndContinue(checkOutStepOnePage);
        checkOutStepTwoSteps.submitFinish(checkOutStepTwoPage);
        checkOutCompleteSteps.submitBackHome(checkOutCompletePage);
        inventorySteps.logout(inventoryPage);
        Assert.assertEquals("Page doesn't have the expected URL", URLs.LOGIN.getValue(), loginPage.getCurrentUrl());
    }
}
