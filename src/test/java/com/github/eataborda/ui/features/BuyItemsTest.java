package com.github.eataborda.ui.features;

import com.github.eataborda.ui.driver.WebDriverConfig;
import com.github.eataborda.ui.enums.URLs;
import com.github.eataborda.ui.pages.*;
import com.github.eataborda.ui.steps.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@Narrative(text={"In order to choose the best flight for my travels",
        "As a traveller",
        "I want to be able to search for flights between specific destinationss"})
@RunWith(SerenityRunner.class)
@WithTagValuesOf({"smoke", "regression", "workflow"})
public class BuyItemsTest {

    @Managed
    public WebDriver driver;

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

    @BeforeClass
    public static void setupClass() {
        WebDriverConfig.setup();
    }

    @Before
    public void setupTest() {
        driver = WebDriverConfig.setupTest(driver);
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
        InventoryPage inventoryPage = loginSteps.loginValidUser(new LoginPage(driver));
        CartPage cartPage = inventorySteps.addItemsAndGoToCart(inventoryPage);
        CheckOutStepOnePage checkOutStepOnePage = cartSteps.submitCheckout(cartPage);
        CheckOutStepTwoPage checkOutStepTwoPage = checkOutStepOneSteps.fillInFormAndContinue(checkOutStepOnePage);
        CheckOutCompletePage checkOutCompletePage = checkOutStepTwoSteps.submitFinish(checkOutStepTwoPage);
        InventoryPage inventoryPageAfterPurchase = checkOutCompleteSteps.submitBackHome(checkOutCompletePage);
        LoginPage loginPageAfterLogout = inventorySteps.logout(inventoryPageAfterPurchase);
        Assert.assertEquals("Page doesn't have the expected URL", URLs.LOGIN.getValue(), loginPageAfterLogout.getCurrentUrl());
    }
}
