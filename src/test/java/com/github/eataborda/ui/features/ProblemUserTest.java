package com.github.eataborda.ui.features;

import com.github.eataborda.ui.driver.WebDriverConfig;
import com.github.eataborda.ui.pages.InventoryPage;
import com.github.eataborda.ui.pages.LoginPage;
import com.github.eataborda.ui.resources.*;
import com.github.eataborda.ui.steps.AssertSteps;
import com.github.eataborda.ui.steps.InventorySteps;
import com.github.eataborda.ui.steps.LoginSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.util.List;

@Tags(value = {@Tag(AnnotationValues.PROBLEM_USER_TAG), @Tag(AnnotationValues.REGRESSION_TAG)})
@DisplayName("Problem User")
@Epic("Inventory Issues")
public class ProblemUserTest {
    public WebDriver driver;
    private LoginSteps loginSteps;
    private InventorySteps inventorySteps;

    @BeforeEach
    public void setupTest() {
        driver = WebDriverConfig.setupBrowser(driver);
        WebDriverConfig.setupTest(driver);
        loginSteps = new LoginSteps(new LoginPage(driver));
        inventorySteps = new InventorySteps(new InventoryPage(driver));
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
    @Description("Verification of the visual error of the inventory using a problem user")
    @Feature("Item issues")
    public void inventoryItemsWithIssuesTest() {
        SoftAssertions softAssertions = new SoftAssertions();
        loginSteps.login(LoginUser.PROBLEM_USER.getUser(), LoginUser.PROBLEM_USER.getPassword());

        AssertSteps.softAssertIsNotEqualTo(inventorySteps.getNumberOfRepeatedImageSrc(Path.WRONG_IMAGE_SRC.getValue()), 0, AssertDescription.INVENTORY_ITEMS_REPEATED_SRC_ISSUE, softAssertions);
        AssertSteps.softAssertIsEqualTo(inventorySteps.getNumberOfRepeatedImageSrc(Path.WRONG_IMAGE_SRC.getValue()), 6, AssertDescription.INVENTORY_ITEMS_REPEATED_SRC_COUNT, softAssertions);
    }

    @Test
    @Tag(AnnotationValues.INVENTORY_FILTER_ISSUES_TAG)
    @DisplayName(AnnotationValues.INVENTORY_FILTER_ISSUES_DISPLAY_NAME)
    @Description("Verification of the inventory filter issues")
    @Feature("Filter issues")
    public void inventoryFilterIssuesTest() {
        SoftAssertions softAssertions = new SoftAssertions();
        loginSteps.login(LoginUser.PROBLEM_USER.getUser(), LoginUser.PROBLEM_USER.getPassword());

        // initial list of items
        List<String> initialNameList = inventorySteps.getItemNameList();

        // inventory order name z to a
        inventorySteps.sortItemsByVisibleText(ProductSortOption.Z_TO_A_OPTION.getVisibleText());
        List<String> zToANameList = inventorySteps.getItemNameList();
        AssertSteps.softAssertIsEqualTo(zToANameList, initialNameList, AssertDescription.COMPARE_ZA_LIST_TO_INITIAL_LIST_EQUALS, softAssertions);

        // order price low to high and verify
        inventorySteps.sortItemsByVisibleText(ProductSortOption.LO_TO_HI_OPTION.getVisibleText());
        List<String> loHiNameList = inventorySteps.getItemNameList();
        AssertSteps.softAssertIsEqualTo(loHiNameList, initialNameList, AssertDescription.COMPARE_LOHI_LIST_TO_INITIAL_LIST_EQUALS, softAssertions);

        // order name high to low and verify
        inventorySteps.sortItemsByVisibleText(ProductSortOption.HI_TO_LO_OPTION.getVisibleText());
        List<String> hiLoNameList = inventorySteps.getItemNameList();
        AssertSteps.softAssertIsEqualTo(hiLoNameList, initialNameList, AssertDescription.COMPARE_HILO_LIST_TO_INITIAL_LIST_EQUALS, softAssertions);

        // inventory order name a to z and verify
        inventorySteps.sortItemsByVisibleText(ProductSortOption.A_TO_Z_OPTION.getVisibleText());
        List<String> atoZNameList = inventorySteps.getItemNameList();
        AssertSteps.softAssertIsEqualTo(atoZNameList, initialNameList, AssertDescription.COMPARE_AZ_LIST_TO_INITIAL_LIST_EQUALS, softAssertions);

        softAssertions.assertAll();
    }

}
