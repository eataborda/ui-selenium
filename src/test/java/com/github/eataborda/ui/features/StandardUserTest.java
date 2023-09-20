package com.github.eataborda.ui.features;

import com.github.eataborda.ui.driver.WebDriverConfig;
import com.github.eataborda.ui.enums.URLs;
import com.github.eataborda.ui.pages.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Tags(value = {@Tag("smoke"), @Tag("regression"), @Tag("workflow")})
public class StandardUserTest {
    public WebDriver driver;

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckOutStepOnePage checkOutStepOnePage;
    private CheckOutStepTwoPage checkOutStepTwoPage;
    private CheckOutCompletePage checkOutCompletePage;

    @BeforeEach
    public void setupTest() {
        driver = WebDriverConfig.setupBrowser(driver);
        WebDriverConfig.setupTest(driver);
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkOutStepOnePage = new CheckOutStepOnePage(driver);
        checkOutStepTwoPage = new CheckOutStepTwoPage(driver);
        checkOutCompletePage = new CheckOutCompletePage(driver);
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Tag("buy-items")
    @DisplayName("Buy items test")
    public void buyItemsTest() {
        loginPage.loginValidUser("standard_user", "secret_sauce");
        inventoryPage.addItemsAndGoToCart();
        cartPage.checkout();
        checkOutStepOnePage.fillInFormAndContinueShopping("Alexander", "Kepler", "1234");
        checkOutStepTwoPage.submitFinish();
        checkOutCompletePage.submitBackHome();
        inventoryPage.logout();
        assertEquals(URLs.LOGIN.getValue(), loginPage.getCurrentUrl(), "Page doesn't have the expected URL");
    }

    @Test
    @Tag("remove-cart-items")
    @DisplayName("Remove cart items test")
    public void removeCartItemsTest() {
        loginPage.loginValidUser("standard_user", "secret_sauce");
        inventoryPage.addItemsAndGoToCart();
        cartPage.removeAllItems();
        cartPage.continueShopping();
        inventoryPage.goToCart();
        assertEquals(0, cartPage.getNumberOfItems(), "Items were not complete removed form cart");
    }

    @Test
    @Tag("cart-items-persistence")
    @DisplayName("Cart items persistence test")
    public void cartItemsPersistenceTest() {
        loginPage.loginValidUser("standard_user", "secret_sauce");
        inventoryPage.addItemsAndGoToCart();
        cartPage.continueShopping();
        inventoryPage.goToCart();
        assertEquals(3, cartPage.getNumberOfItems(), "Items were not persisted correctly in cart");
    }

    @Test
    @Tag("order-inventory-items")
    @DisplayName("Order inventory items test")
    public void orderInventoryItemsTest() {
        loginPage.loginValidUser("standard_user", "secret_sauce");
        // initial list of items
        List<String> initialNameList = inventoryPage.getItemNameList();
        // inventory order name z to a
        inventoryPage.orderItemsByCriteria("za");
        List<String> zToANameList = inventoryPage.getItemNameList();
        assertNotEquals(initialNameList.toString(), zToANameList.toString(), "The current list doesn't have the expected order");
        // order price low to high and verify
        inventoryPage.orderItemsByCriteria("lohi");
        List<String> loHiNameList = inventoryPage.getItemNameList();
        assertNotEquals(initialNameList.toString(), loHiNameList.toString(), "The current list doesn't have the expected order");
        assertNotEquals(zToANameList.toString(), loHiNameList.toString(), "The current list doesn't have the expected order");
        // order name high to low and verify
        inventoryPage.orderItemsByCriteria("hilo");
        List<String> hiLoNameList = inventoryPage.getItemNameList();
        assertNotEquals(initialNameList.toString(), hiLoNameList.toString(), "The current list doesn't have the expected order");
        assertNotEquals(zToANameList.toString(), hiLoNameList.toString(), "The current list doesn't have the expected order");
        assertNotEquals(loHiNameList.toString(), hiLoNameList.toString(), "The current list doesn't have the expected order");
        // inventory order name a to z and verify
        inventoryPage.orderItemsByCriteria("az");
        List<String> atoZNameList = inventoryPage.getItemNameList();
        assertEquals(initialNameList.toString(), atoZNameList.toString(), "The current list doesn't have the expected order");
        assertNotEquals(zToANameList.toString(), atoZNameList.toString(), "The current list doesn't have the expected order");
        assertNotEquals(loHiNameList.toString(), atoZNameList.toString(), "The current list doesn't have the expected order");
        assertNotEquals(hiLoNameList.toString(), atoZNameList.toString(), "The current list doesn't have the expected order");
    }

    @Test
    @Tag("verify-checkout-data")
    @DisplayName("Verify checkout user data test")
    public void verifyCheckoutUserDataTest() {
        loginPage.loginValidUser("standard_user", "secret_sauce");
        inventoryPage.addItemsAndGoToCart();
        cartPage.checkout();
        // Add nothing (verify firstName)
        checkOutStepOnePage.continueShopping();
        assertEquals("Error: First Name is required", checkOutStepOnePage.getFieldErrorMessage(), "Not expected error message for the current scenario");
        // put only FirstName (verify lastName)
        checkOutStepOnePage.putFirstName("Alexander");
        checkOutStepOnePage.continueShopping();
        assertEquals("Error: Last Name is required", checkOutStepOnePage.getFieldErrorMessage(), "Not expected error message for the current scenario");
        // put only LastName (verify firstName)
        checkOutStepOnePage.putLastName("Kepler");
        checkOutStepOnePage.continueShopping();
        assertEquals("Error: Postal Code is required", checkOutStepOnePage.getFieldErrorMessage(), "Not expected error message for the current scenario");
    }
}
