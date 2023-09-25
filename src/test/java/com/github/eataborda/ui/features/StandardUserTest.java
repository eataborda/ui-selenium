package com.github.eataborda.ui.features;

import com.github.eataborda.ui.driver.WebDriverConfig;
import com.github.eataborda.ui.enums.URLs;
import com.github.eataborda.ui.pages.*;
import org.assertj.core.api.SoftAssertions;
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

    /*@Test
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
        SoftAssertions softAssertions = new SoftAssertions();

        // inventory order name z to a
        inventoryPage.orderItemsByCriteria("za");
        List<String> zToANameList = inventoryPage.getItemNameList();
        softAssertions.assertThat(zToANameList)
                .as("Compare Z to A item list with initial item list").isNotEqualTo(initialNameList);

        // order price low to high and verify
        inventoryPage.orderItemsByCriteria("lohi");
        List<String> loHiNameList = inventoryPage.getItemNameList();
        softAssertions.assertThat(loHiNameList)
                .as("Compare Low to High item list with initial item list").isNotEqualTo(initialNameList);
        softAssertions.assertThat(loHiNameList)
                .as("Compare Low to High item list with Z to A item list").isNotEqualTo(zToANameList);

        // order name high to low and verify
        inventoryPage.orderItemsByCriteria("hilo");
        List<String> hiLoNameList = inventoryPage.getItemNameList();
        softAssertions.assertThat(hiLoNameList)
                .as("Compare High to Low item list with initial item list").isNotEqualTo(initialNameList);
        softAssertions.assertThat(hiLoNameList)
                .as("Compare High to Low item list with Z to A item list").isNotEqualTo(zToANameList);
        softAssertions.assertThat(hiLoNameList)
                .as("Compare High to Low item list with Low to High item list").isNotEqualTo(loHiNameList);

        // inventory order name a to z and verify
        inventoryPage.orderItemsByCriteria("az");
        List<String> atoZNameList = inventoryPage.getItemNameList();
        softAssertions.assertThat(atoZNameList)
                .as("Compare A to Z item list with initial item list").isEqualTo(initialNameList);
        softAssertions.assertThat(atoZNameList)
                .as("Compare A to Z item list with Z to A item list").isNotEqualTo(zToANameList);
        softAssertions.assertThat(atoZNameList)
                .as("Compare A to Z item list with Low to High item list").isNotEqualTo(loHiNameList);
        softAssertions.assertThat(atoZNameList)
                .as("Compare A to Z item list with High to Low item list").isNotEqualTo(hiLoNameList);

        softAssertions.assertAll();
    }

    @Test
    @Tag("verify-checkout-data")
    @DisplayName("Verify checkout user data test")
    public void verifyCheckoutUserDataTest() {
        loginPage.loginValidUser("standard_user", "secret_sauce");
        inventoryPage.addItemsAndGoToCart();
        cartPage.checkout();
        SoftAssertions softAssertions = new SoftAssertions();

        // Add nothing (verify firstName)
        checkOutStepOnePage.continueShopping();
        softAssertions.assertThat(checkOutStepOnePage.getFieldErrorMessage())
                .as("Compare first name error message").isEqualTo("Error: First Name is required");

        // put only FirstName (verify lastName)
        checkOutStepOnePage.putFirstName("Alexander");
        checkOutStepOnePage.continueShopping();
        softAssertions.assertThat(checkOutStepOnePage.getFieldErrorMessage())
                .as("Compare last name error message").isEqualTo("Error: Last Name is required");

        // put only LastName (verify firstName)
        checkOutStepOnePage.putLastName("Kepler");
        checkOutStepOnePage.continueShopping();
        softAssertions.assertThat(checkOutStepOnePage.getFieldErrorMessage())
                .as("Compare postal code error message").isEqualTo("Error: Postal Code is required");

        softAssertions.assertAll();
    }*/
}
