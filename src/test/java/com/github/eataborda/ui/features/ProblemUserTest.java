package com.github.eataborda.ui.features;

import com.github.eataborda.ui.driver.WebDriverConfig;
import com.github.eataborda.ui.pages.CartPage;
import com.github.eataborda.ui.pages.CheckOutStepOnePage;
import com.github.eataborda.ui.pages.InventoryPage;
import com.github.eataborda.ui.pages.LoginPage;
import com.github.eataborda.ui.resources.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Tags(value = {@Tag(AnnotationValues.PROBLEM_USER_TAG), @Tag(AnnotationValues.REGRESSION_TAG)})
public class ProblemUserTest {
    public WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckOutStepOnePage checkOutStepOnePage;

    @BeforeEach
    public void setupTest() {
        driver = WebDriverConfig.setupBrowser(driver);
        WebDriverConfig.setupTest(driver);
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkOutStepOnePage = new CheckOutStepOnePage(driver);
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Tag(AnnotationValues.INVENTORY_ITEM_SRC_ISSUES_TAG)
    @DisplayName(AnnotationValues.INVENTORY_ITEM_SRC_ISSUES_DISPLAY_NAME)
    public void inventoryItemsWithIssuesTest() {
        loginPage.loginValidUser(LoginUser.PROBLEM_USER.getUser(), LoginUser.PROBLEM_USER.getPassword());
        assertNotEquals(0, inventoryPage.getNumberOfRepeatedImageSrc(Path.WRONG_IMAGE_SRC.getValue()),
                ErrorMessage.ITEMS_HAVE_EXPECTED_IMAGE_SRC.getMessage());
        assertEquals(6, inventoryPage.getNumberOfRepeatedImageSrc(Path.WRONG_IMAGE_SRC.getValue()),
                ErrorMessage.ITEMS_HAVE_REPEATED_IMAGE_SRC.getMessage());
    }

    @Test
    @Tag(AnnotationValues.INVENTORY_FILTER_ISSUES_TAG)
    @DisplayName(AnnotationValues.INVENTORY_FILTER_ISSUES_DISPLAY_NAME)
    public void inventoryFilterIssuesTest() {
        loginPage.loginValidUser(LoginUser.PROBLEM_USER.getUser(), LoginUser.PROBLEM_USER.getPassword());
        // initial list of items
        List<String> initialNameList = inventoryPage.getItemNameList();
        SoftAssertions softAssertions = new SoftAssertions();

        // inventory order name z to a
        inventoryPage.sortItemsByValue(ProductSortOption.Z_TO_A_OPTION.getValue());
        List<String> zToANameList = inventoryPage.getItemNameList();
        softAssertions.assertThat(zToANameList)
                .as(AssertDescription.COMPARE_ZA_LIST_TO_INITIAL_LIST).isEqualTo(initialNameList);

        // order price low to high and verify
        inventoryPage.sortItemsByValue(ProductSortOption.LO_TO_HI_OPTION.getValue());
        List<String> loHiNameList = inventoryPage.getItemNameList();
        softAssertions.assertThat(loHiNameList)
                .as(AssertDescription.COMPARE_LOHI_LIST_TO_INITIAL_LIST).isEqualTo(initialNameList);

        // order name high to low and verify
        inventoryPage.sortItemsByValue(ProductSortOption.HI_TO_LO_OPTION.getValue());
        List<String> hiLoNameList = inventoryPage.getItemNameList();
        softAssertions.assertThat(hiLoNameList)
                .as(AssertDescription.COMPARE_HILO_LIST_TO_INITIAL_LIST).isEqualTo(initialNameList);

        // inventory order name a to z and verify
        inventoryPage.sortItemsByValue(ProductSortOption.A_TO_Z_OPTION.getValue());
        List<String> atoZNameList = inventoryPage.getItemNameList();
        softAssertions.assertThat(atoZNameList)
                .as(AssertDescription.COMPARE_AZ_LIST_TO_INITIAL_LIST).isEqualTo(initialNameList);

        softAssertions.assertAll();
    }

}
