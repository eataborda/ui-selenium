package com.github.eataborda.ui.features;

import com.github.eataborda.ui.driver.WebDriverConfig;
import com.github.eataborda.ui.pages.CartPage;
import com.github.eataborda.ui.pages.CheckOutStepOnePage;
import com.github.eataborda.ui.pages.InventoryPage;
import com.github.eataborda.ui.pages.LoginPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Tags(value = {@Tag("problem-user"), @Tag("regression")})
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
    @Tag("inventory-item-src-issues")
    @DisplayName("Inventory items with issues test")
    public void inventoryItemsWithIssuesTest() {
        loginPage.loginValidUser("problem_user", "secret_sauce");
        assertNotEquals(0, inventoryPage.getNumberOfRepeatedImageSrc("/static/media/sl-404.168b1cce.jpg"), "Inventory items have the correct image src");
        assertEquals(6, inventoryPage.getNumberOfRepeatedImageSrc("/static/media/sl-404.168b1cce.jpg"), "Some inventory images have repeated values");
    }

    @Test
    @Tag("inventory-filter-issues")
    @DisplayName("Inventory filter issues test")
    public void inventoryFilterIssuesTest() {
        loginPage.loginValidUser("problem_user", "secret_sauce");
        // initial list of items
        List<String> initialNameList = inventoryPage.getItemNameList();
        SoftAssertions softAssertions = new SoftAssertions();

        // inventory order name z to a
        inventoryPage.orderItemsByCriteria("za");
        List<String> zToANameList = inventoryPage.getItemNameList();
        softAssertions.assertThat(zToANameList)
                .as("Compare Z to A item list with initial item list").isEqualTo(initialNameList);

        // order price low to high and verify
        inventoryPage.orderItemsByCriteria("lohi");
        List<String> loHiNameList = inventoryPage.getItemNameList();
        softAssertions.assertThat(loHiNameList)
                .as("Compare Low to High item list with initial item list").isEqualTo(initialNameList);

        // order name high to low and verify
        inventoryPage.orderItemsByCriteria("hilo");
        List<String> hiLoNameList = inventoryPage.getItemNameList();
        softAssertions.assertThat(hiLoNameList)
                .as("Compare High to Low item list with initial item list").isEqualTo(initialNameList);

        // inventory order name a to z and verify
        inventoryPage.orderItemsByCriteria("az");
        List<String> atoZNameList = inventoryPage.getItemNameList();
        softAssertions.assertThat(atoZNameList)
                .as("Compare A to Z item list with initial item list").isEqualTo(initialNameList);

        softAssertions.assertAll();
    }

}
