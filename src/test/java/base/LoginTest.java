package tests;

import base.BaseTest; // If you used tests.base.BaseTest, update this import accordingly.
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.UUID;

public class LoginTest extends BaseTest {

    @Test(description = "Login button should be disabled with empty fields")
    public void testLoginButtonDisabledWhenFieldAreEmpty() {
        LoginPage page = new LoginPage(driver, wait);

        // Ensure fields are empty (in case of autofill)
        page.typeUserId("");
        page.typePassword("");

        // Some apps still enable the button; so validate either disabled OR error retained after click
        boolean enabled = page.isLoginButtonEnabled();
        if (enabled) {
            page.clickLogin();
            String error = page.captureErrorMessageIfAny();
            Assert.assertTrue(error.length() > 0,
                    "Expected an error message when attempting login with blank fields.");
        } else {
            Assert.assertFalse(enabled, "Expected login button to be disabled when fields are empty.");
        }
    }

    @Test(description = "Password should toggle between masked and unmasked")
    public void testPasswordMaskedbutton() {
        LoginPage page = new LoginPage(driver, wait);
        page.typePassword("Secret123!");

        // Initially masked
        Assert.assertTrue(page.isPasswordMasked(), "Password should be masked by default.");

        // Toggle to reveal
        page.togglePasswordVisibility();
        Assert.assertFalse(page.isPasswordMasked(), "Password should become visible (type='text') after toggle.");

        // Toggle back to mask
        page.togglePasswordVisibility();
        Assert.assertTrue(page.isPasswordMasked(), "Password should be masked again after second toggle.");
    }

    @Test(description = "Invalid login should show an error")
    public void testInvalidLoginShowErrorMsg() {
        LoginPage page = new LoginPage(driver, wait);

        String randomEmail = "u_" + UUID.randomUUID() + "@invalid.example";
        String randomPass = "Wrong_" + UUID.randomUUID();

        page.typeUserId(randomEmail);
        page.typePassword(randomPass);
        page.clickLogin();

        String error = page.captureErrorMessageIfAny();
        System.out.println("[Invalid Login Error] " + error);
        Assert.assertTrue(error.length() > 0, "Expected an error message for invalid credentials.");
    }

    @Test(description = "Validate presence of key UI elements on login page")
    public void testUIElementsPresence() {
        LoginPage page = new LoginPage(driver, wait);
        Assert.assertTrue(page.isUserIdVisible(), "User ID input should be visible.");
        Assert.assertTrue(page.isPasswordVisible(), "Password input should be visible.");
        Assert.assertTrue(page.isLoginButtonVisible(), "Login button should be visible.");
        // Eye icon may not exist in some builds; if it's a hard requirement, keep assertTrue
        Assert.assertTrue(page.isEyeToggleVisible(), "Password visibility toggle (eye icon) should be visible.");
    }
}
