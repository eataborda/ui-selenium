package com.github.eataborda.ui.steps;

import io.qameta.allure.Allure;
import org.assertj.core.api.SoftAssertions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertSteps {
    public static void hardAssertEquals(String expectedValue, String actualValue, String description, String errorMessage) {
        Allure.step("Assert equals: " + description + " - Expected: " + expectedValue + " - Actual: " + actualValue);
        assertEquals(expectedValue, actualValue, errorMessage);
    }

    public static void hardAssertEquals(int expectedValue, int actualValue, String description, String errorMessage) {
        Allure.step("Assert equals: " + description + " - Expected: " + expectedValue + " - Actual: " + actualValue);
        assertEquals(expectedValue, actualValue, errorMessage);
    }

    public static void softAssertIsNotEqualTo(List<String> actualList, List<String> initialList, String assertionDescription, SoftAssertions softAssertions) {
        Allure.step("Assertion Is Not Equal To: " + assertionDescription + " - Actual list: " + actualList + " - Initial list: " + initialList);
        softAssertions.assertThat(actualList)
                .as(assertionDescription).isNotEqualTo(initialList);
    }

    public static void softAssertIsNotEqualTo(int actual, int reference, String assertionDescription, SoftAssertions softAssertions) {
        Allure.step("Assertion Is Not Equal To: " + assertionDescription + " - Actual: " + actual + " - Reference: " + reference);
        softAssertions.assertThat(actual)
                .as(assertionDescription).isEqualTo(reference);
    }

    public static void softAssertIsEqualTo(List<String> actualList, List<String> initialList, String assertionDescription, SoftAssertions softAssertions) {
        Allure.step("Assertion Is Equal To: " + assertionDescription + " - Actual list: " + actualList + " - Initial list: " + initialList);
        softAssertions.assertThat(actualList)
                .as(assertionDescription).isEqualTo(initialList);
    }

    public static void softAssertIsEqualTo(String actual, String expected, String assertionDescription, SoftAssertions softAssertions) {
        Allure.step("Assertion Is Equal To: " + assertionDescription + " - Actual: " + actual + " - Expected: " + expected);
        softAssertions.assertThat(actual)
                .as(assertionDescription).isEqualTo(expected);
    }

    public static void softAssertIsEqualTo(int actual, int expected, String assertionDescription, SoftAssertions softAssertions) {
        Allure.step("Assertion Is Equal To: " + assertionDescription + " - Actual: " + actual + " - Expected: " + expected);
        softAssertions.assertThat(actual)
                .as(assertionDescription).isEqualTo(expected);
    }


}
