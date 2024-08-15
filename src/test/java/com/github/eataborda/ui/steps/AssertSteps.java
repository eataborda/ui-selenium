package com.github.eataborda.ui.steps;

import com.github.eataborda.ui.pages.*;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertSteps {
    public static void junitAssertEquals(String expectedValue, String actualValue, String description, String errorMessage){
        Allure.step("Assert equals: "+description+" - Expected: "+expectedValue+" - Actual: "+actualValue);
        assertEquals(expectedValue,actualValue,errorMessage);
    }

    public static void junitAssertEquals(int expectedValue, int actualValue, String description, String errorMessage){
        Allure.step("Assert equals: "+description+" - Expected: "+expectedValue+" - Actual: "+actualValue);
        assertEquals(expectedValue,actualValue,errorMessage);
    }
}
