package com.github.eataborda.ui.features;

import com.github.eataborda.ui.driver.WebDriverConfig;
import com.github.eataborda.ui.resources.*;
import com.github.eataborda.ui.pages.*;
import com.github.eataborda.ui.steps.*;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Tags(value = {@Tag(AnnotationValues.STANDARD_USER_TAG),
        @Tag(AnnotationValues.SMOKE_TAG), @Tag(AnnotationValues.REGRESSION_TAG)})
@DisplayName("Standard User")
@Epic("Shopping")
public class StandardUserTest {
    public WebDriver driver;
    private LoginSteps loginSteps;
    private InventorySteps inventorySteps;
    private CartSteps cartSteps;
    private CheckOutStepOneSteps checkOutStepOneSteps;
    private CheckOutStepTwoSteps checkOutStepTwoSteps;
    private CheckOutCompleteSteps checkOutCompleteSteps;

    ArrayList<String> itemList = new ArrayList<String>(Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket"));

    @BeforeEach
    public void setupTest() {
        driver = WebDriverConfig.setupBrowser(driver);
        WebDriverConfig.setupTest(driver);

        loginSteps = new LoginSteps(new LoginPage(driver));
        inventorySteps = new InventorySteps(new InventoryPage(driver));
        cartSteps = new CartSteps(new CartPage(driver));
        checkOutStepOneSteps = new CheckOutStepOneSteps(new CheckOutStepOnePage(driver));
        checkOutStepTwoSteps = new CheckOutStepTwoSteps(new CheckOutStepTwoPage(driver));
        checkOutCompleteSteps = new CheckOutCompleteSteps(new CheckOutCompletePage(driver));
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
    @Description("A complete shopping flow")
    @Feature("Buy Items")
    public void buyItemsTest() {
        loginSteps.login(LoginUser.STANDARD_USER.getUser(), LoginUser.STANDARD_USER.getPassword());
        inventorySteps.addItemsAndGoToCart(itemList);
        cartSteps.checkout();
        checkOutStepOneSteps.fillInFormAndContinueShopping(Client.EXAMPLE_CLIENT.getFirstName(),
                Client.EXAMPLE_CLIENT.getLastName(), Client.EXAMPLE_CLIENT.getPostalCode());
        checkOutStepTwoSteps.submitFinish();
        checkOutCompleteSteps.submitBackHome();
        inventorySteps.logout();
        AssertSteps.hardAssertEquals(URL.LOGIN.getValue(), loginSteps.getCurrentUrl(), AssertDescription.BUY_ITEMS_COMPARE_URL_MESSAGE, ErrorMessage.NOT_EXPECTED_URL.getMessage());
    }

    @Test
    @Tag(AnnotationValues.REMOVE_CART_ITEMS_TAG)
    @DisplayName(AnnotationValues.REMOVE_CART_DISPLAY_NAME)
    @Description("Verification of the number of items into the cart ")
    @Feature("Cart")
    public void removeCartItemsTest() {
        loginSteps.login(LoginUser.STANDARD_USER.getUser(), LoginUser.STANDARD_USER.getPassword());
        inventorySteps.addItemsAndGoToCart(itemList);
        cartSteps.removeAllItems(itemList);
        cartSteps.continueShopping();
        inventorySteps.goToCart();
        AssertSteps.hardAssertEquals(0, cartSteps.getNumberOfItems(), AssertDescription.REMOVE_CART_ITEMS_MESSAGE, ErrorMessage.ITEMS_NOT_COMPLETELY_REMOVED.getMessage());
    }

    @Test
    @Tag(AnnotationValues.CART_ITEMS_PERSISTENCE_TAG)
    @DisplayName(AnnotationValues.CART_ITEMS_PERSISTENCE_DISPLAY_NAME)
    @Description("Verification of the persistence of the items cart ")
    @Feature("Cart")
    public void cartItemsPersistenceTest() {
        loginSteps.login(LoginUser.STANDARD_USER.getUser(), LoginUser.STANDARD_USER.getPassword());
        inventorySteps.addItemsAndGoToCart(itemList);
        cartSteps.continueShopping();
        inventorySteps.goToCart();
        AssertSteps.hardAssertEquals(3, cartSteps.getNumberOfItems(), AssertDescription.CART_ITEMS_PERSISTENCE_MESSAGE, ErrorMessage.ITEMS_INCORRECTLY_PERSISTED_IN_CART.getMessage());
    }

    @Test
    @Tag(AnnotationValues.ORDER_INVENTORY_ITEMS_TAG)
    @DisplayName(AnnotationValues.ORDER_INVENTORY_ITEMS_DISPLAY_NAME)
    @Description("Verification of the inventory order options")
    @Feature("Inventory")
    public void orderInventoryItemsTest() {
        SoftAssertions softAssertions = new SoftAssertions();
        loginSteps.login(LoginUser.STANDARD_USER.getUser(), LoginUser.STANDARD_USER.getPassword());

        //// initial list of item
        List<String> initialNameList = inventorySteps.getItemNameList();

        // inventory order name z to a
        inventorySteps.sortItemsByVisibleText(ProductSortOption.Z_TO_A_OPTION.getVisibleText());
        List<String> zToANameList = inventorySteps.getItemNameList();
        AssertSteps.softAssertIsNotEqualTo(zToANameList, initialNameList, AssertDescription.COMPARE_ZA_LIST_TO_INITIAL_LIST_NOT_EQUALS, softAssertions);

        // order price low to high and verify
        inventorySteps.sortItemsByVisibleText(ProductSortOption.LO_TO_HI_OPTION.getVisibleText());
        List<String> loHiNameList = inventorySteps.getItemNameList();
        AssertSteps.softAssertIsNotEqualTo(loHiNameList, initialNameList, AssertDescription.COMPARE_LOHI_LIST_TO_INITIAL_LIST_NOT_EQUALS, softAssertions);
        AssertSteps.softAssertIsNotEqualTo(loHiNameList, zToANameList, AssertDescription.COMPARE_LOHI_LIST_TO_ZA_LIST_NOT_EQUAL, softAssertions);

        // order name high to low and verify
        inventorySteps.sortItemsByVisibleText(ProductSortOption.HI_TO_LO_OPTION.getVisibleText());
        List<String> hiLoNameList = inventorySteps.getItemNameList();
        AssertSteps.softAssertIsNotEqualTo(hiLoNameList, initialNameList, AssertDescription.COMPARE_HILO_LIST_TO_INITIAL_LIST_NOT_EQUALS, softAssertions);
        AssertSteps.softAssertIsNotEqualTo(hiLoNameList, zToANameList, AssertDescription.COMPARE_HILO_LIST_TO_ZA_LIST_NOT_EQUALS, softAssertions);
        AssertSteps.softAssertIsNotEqualTo(hiLoNameList, loHiNameList, AssertDescription.COMPARE_HILO_LIST_TO_LOHI_LIST_NOT_EQUALS, softAssertions);

        // inventory order name a to z and verify
        inventorySteps.sortItemsByVisibleText(ProductSortOption.A_TO_Z_OPTION.getVisibleText());
        List<String> atoZNameList = inventorySteps.getItemNameList();
        AssertSteps.softAssertIsEqualTo(atoZNameList, initialNameList, AssertDescription.COMPARE_AZ_LIST_TO_INITIAL_LIST_EQUALS, softAssertions);
        AssertSteps.softAssertIsNotEqualTo(atoZNameList, zToANameList, AssertDescription.COMPARE_AZ_LIST_TO_ZA_LIST, softAssertions);
        AssertSteps.softAssertIsNotEqualTo(atoZNameList, loHiNameList, AssertDescription.COMPARE_AZ_LIST_TO_LOHI_LIST, softAssertions);
        AssertSteps.softAssertIsNotEqualTo(atoZNameList, hiLoNameList, AssertDescription.COMPARE_AZ_LIST_TO_HILO_LIST, softAssertions);

        softAssertions.assertAll();
    }

    @Test
    @Tag(AnnotationValues.VERIFY_CHECKOUT_DATA_TAG)
    @DisplayName(AnnotationValues.VERIFY_CHECKOUT_DATA_DISPLAY_NAME)
    @Description("Verification of the user information before checkout")
    @Feature("User information verification")
    public void verifyCheckoutUserDataTest() {
        SoftAssertions softAssertions = new SoftAssertions();

        loginSteps.login(LoginUser.STANDARD_USER.getUser(), LoginUser.STANDARD_USER.getPassword());
        inventorySteps.addItemsAndGoToCart(itemList);
        cartSteps.checkout();

        // add nothing (verify firstName)
        checkOutStepOneSteps.continueShopping();
        AssertSteps.softAssertIsEqualTo(checkOutStepOneSteps.getFieldErrorMessage(), ErrorMessage.FIRST_NAME_REQUIRED.getMessage(), AssertDescription.COMPARE_FIRST_NAME_ERROR_MESSAGE, softAssertions);

        // put only FirstName (verify lastName)
        checkOutStepOneSteps.putFirstName(Client.EXAMPLE_CLIENT.getFirstName());
        checkOutStepOneSteps.continueShopping();
        AssertSteps.softAssertIsEqualTo(checkOutStepOneSteps.getFieldErrorMessage(), ErrorMessage.SECOND_NAME_REQUIRED.getMessage(), AssertDescription.COMPARE_LAST_NAME_ERROR_MESSAGE, softAssertions);

        // put only LastName (verify firstName) pe
        checkOutStepOneSteps.putLastName(Client.EXAMPLE_CLIENT.getLastName());
        checkOutStepOneSteps.continueShopping();
        AssertSteps.softAssertIsEqualTo(checkOutStepOneSteps.getFieldErrorMessage(), ErrorMessage.POSTAL_CODE_REQUIRED.getMessage(), AssertDescription.COMPARE_POSTAL_CODE_ERROR_MESSAGE, softAssertions);

        softAssertions.assertAll();
    }
}
