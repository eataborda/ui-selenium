package com.github.eataborda.ui.features;

import com.github.eataborda.ui.driver.WebDriverConfig;
import com.github.eataborda.ui.resources.*;
import com.github.eataborda.ui.pages.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Tags(value = {@Tag(AnnotationValues.STANDARD_USER_TAG),
        @Tag(AnnotationValues.SMOKE_TAG), @Tag(AnnotationValues.REGRESSION_TAG)})
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
    @Tag(AnnotationValues.BUY_ITEMS_TAG)
    @DisplayName(AnnotationValues.BUY_ITEMS_DISPLAY_NAME)
    public void buyItemsTest() {
        loginPage.loginValidUser(LoginUser.STANDARD_USER.getUser(), LoginUser.STANDARD_USER.getPassword());
        inventoryPage.addItemsAndGoToCart();
        cartPage.checkout();
        checkOutStepOnePage.fillInFormAndContinueShopping(Client.EXAMPLE_CLIENT.getFirstName(),
                Client.EXAMPLE_CLIENT.getLastName(), Client.EXAMPLE_CLIENT.getPostalCode());
        checkOutStepTwoPage.submitFinish();
        checkOutCompletePage.submitBackHome();
        inventoryPage.logout();
        assertEquals(URL.LOGIN.getValue(), loginPage.getCurrentUrl(), ErrorMessage.NOT_EXPECTED_URL.getMessage());
    }

    @Test
    @Tag(AnnotationValues.REMOVE_CART_ITEMS_TAG)
    @DisplayName(AnnotationValues.REMOVE_CART_DISPLAY_NAME)
    public void removeCartItemsTest() {
        loginPage.loginValidUser(LoginUser.STANDARD_USER.getUser(), LoginUser.STANDARD_USER.getPassword());
        inventoryPage.addItemsAndGoToCart();
        cartPage.removeAllItems();
        cartPage.continueShopping();
        inventoryPage.goToCart();
        assertEquals(0, cartPage.getNumberOfItems(), ErrorMessage.ITEMS_NOT_COMPLETELY_REMOVED.getMessage());
    }

    @Test
    @Tag(AnnotationValues.CART_ITEMS_PERSISTENCE_TAG)
    @DisplayName(AnnotationValues.CART_ITEMS_PERSISTENCE_DISPLAY_NAME)
    public void cartItemsPersistenceTest() {
        loginPage.loginValidUser(LoginUser.STANDARD_USER.getUser(), LoginUser.STANDARD_USER.getPassword());
        inventoryPage.addItemsAndGoToCart();
        cartPage.continueShopping();
        inventoryPage.goToCart();
        assertEquals(3, cartPage.getNumberOfItems(), ErrorMessage.ITEMS_INCORRECTLY_PERSISTED_IN_CART.getMessage());
    }

    @Test
    @Tag(AnnotationValues.ORDER_INVENTORY_ITEMS_TAG)
    @DisplayName(AnnotationValues.ORDER_INVENTORY_ITEMS_DISPLAY_NAME)
    public void orderInventoryItemsTest() {
        loginPage.loginValidUser(LoginUser.STANDARD_USER.getUser(), LoginUser.STANDARD_USER.getPassword());
        // initial list of items
        List<String> initialNameList = inventoryPage.getItemNameList();
        SoftAssertions softAssertions = new SoftAssertions();

        // inventory order name z to a
        inventoryPage.sortItemsByValue(ProductSortOption.Z_TO_A_OPTION.getValue());
        List<String> zToANameList = inventoryPage.getItemNameList();
        softAssertions.assertThat(zToANameList)
                .as(AssertDescription.COMPARE_ZA_LIST_TO_INITIAL_LIST).isNotEqualTo(initialNameList);

        // order price low to high and verify
        inventoryPage.sortItemsByValue(ProductSortOption.LO_TO_HI_OPTION.getValue());
        List<String> loHiNameList = inventoryPage.getItemNameList();
        softAssertions.assertThat(loHiNameList)
                .as(AssertDescription.COMPARE_LOHI_LIST_TO_INITIAL_LIST).isNotEqualTo(initialNameList);
        softAssertions.assertThat(loHiNameList)
                .as(AssertDescription.COMPARE_LOHI_LIST_TO_ZA_LIST).isNotEqualTo(zToANameList);

        // order name high to low and verify
        inventoryPage.sortItemsByValue(ProductSortOption.HI_TO_LO_OPTION.getValue());
        List<String> hiLoNameList = inventoryPage.getItemNameList();
        softAssertions.assertThat(hiLoNameList)
                .as(AssertDescription.COMPARE_HILO_LIST_TO_INITIAL_LIST).isNotEqualTo(initialNameList);
        softAssertions.assertThat(hiLoNameList)
                .as(AssertDescription.COMPARE_HILO_LIST_TO_ZA_LIST).isNotEqualTo(zToANameList);
        softAssertions.assertThat(hiLoNameList)
                .as(AssertDescription.COMPARE_HILO_LIST_TO_LOHI_LIST).isNotEqualTo(loHiNameList);

        // inventory order name a to z and verify
        inventoryPage.sortItemsByValue(ProductSortOption.A_TO_Z_OPTION.getValue());
        List<String> atoZNameList = inventoryPage.getItemNameList();
        softAssertions.assertThat(atoZNameList)
                .as(AssertDescription.COMPARE_AZ_LIST_TO_INITIAL_LIST).isEqualTo(initialNameList);
        softAssertions.assertThat(atoZNameList)
                .as(AssertDescription.COMPARE_AZ_LIST_TO_ZA_LIST).isNotEqualTo(zToANameList);
        softAssertions.assertThat(atoZNameList)
                .as(AssertDescription.COMPARE_AZ_LIST_TO_LOHI_LIST).isNotEqualTo(loHiNameList);
        softAssertions.assertThat(atoZNameList)
                .as(AssertDescription.COMPARE_AZ_LIST_TO_HILO_LIST).isNotEqualTo(hiLoNameList);

        softAssertions.assertAll();
    }

    @Test
    @Tag(AnnotationValues.VERIFY_CHECKOUT_DATA_TAG)
    @DisplayName(AnnotationValues.VERIFY_CHECKOUT_DATA_DISPLAY_NAME)
    public void verifyCheckoutUserDataTest() {
        loginPage.loginValidUser(LoginUser.STANDARD_USER.getUser(), LoginUser.STANDARD_USER.getPassword());
        inventoryPage.addItemsAndGoToCart();
        cartPage.checkout();
        SoftAssertions softAssertions = new SoftAssertions();

        // add nothing (verify firstName)
        checkOutStepOnePage.continueShopping();
        softAssertions.assertThat(checkOutStepOnePage.getFieldErrorMessage())
                .as(AssertDescription.COMPARE_FIRST_NAME_ERROR_MESSAGE)
                .isEqualTo(ErrorMessage.FIRST_NAME_REQUIRED.getMessage());

        // put only FirstName (verify lastName)
        checkOutStepOnePage.putFirstName(Client.EXAMPLE_CLIENT.getFirstName());
        checkOutStepOnePage.continueShopping();
        softAssertions.assertThat(checkOutStepOnePage.getFieldErrorMessage())
                .as(AssertDescription.COMPARE_LAST_NAME_ERROR_MESSAGE)
                .isEqualTo(ErrorMessage.SECOND_NAME_REQUIRED.getMessage());

        // put only LastName (verify firstName)
        checkOutStepOnePage.putLastName(Client.EXAMPLE_CLIENT.getLastName());
        checkOutStepOnePage.continueShopping();
        softAssertions.assertThat(checkOutStepOnePage.getFieldErrorMessage())
                .as(AssertDescription.COMPARE_POSTAL_CODE_ERROR_MESSAGE)
                .isEqualTo(ErrorMessage.POSTAL_CODE_REQUIRED.getMessage());

        softAssertions.assertAll();

    }
}
