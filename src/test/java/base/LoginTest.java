package tests;

import base.BaseTest; // Make sure package path matches your BaseTest location
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.UUID;

/**
 * Test class for verifying Login Page functionality.
 * Uses Page Object (LoginPage) and BaseTest setup.
 */
public class LoginTest extends BaseTest {

    /**
     * Test: Login button should be disabled when fields are empty.
     * - Clears User ID and Password fields
     * - Checks whether the Login button is disabled
     * - If not disabled, clicks it and expects an error message
     */
    @Test(description = "Login button should be disabled with empty fields")
    public void testLoginButtonDisabledW
