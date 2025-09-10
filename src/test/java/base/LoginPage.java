package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page Object representing the Login Page.
 * Encapsulates locators and actions related to login functionality.
 */
public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // ========= Locators =========

    // Resilient locator for User ID / Email input
    // Covers multiple attribute variations commonly used in login forms
    private final By userIdInput = By.xpath(
            "//input[@name='email' or @id='email' or @type='email' " +
            "or contains(@placeholder,'Email') or contains(@placeholder,'User')]"
    );

    // Locator for Password input field
    private final By passwordInput = By.xpath(
            "//input[@name='password' or @id='password' or @type='password' " +
            "or contains(@placeholder,'Password')]"
    );

    // Locator for Login button
    // Matches submit buttons and buttons containing "Login" text
    private final By loginButton = By.xpath(
            "//button[@type='submit' or contains(.,'Log in') or contains(.,'Login')]"
    );

    // Locator for password visibility toggle (eye icon button)
    // Captures common UI patterns (aria-labels, CSS classes, icons)
    private final By eyeToggle = By.xpath(
            "(" +
            " //input[@name='password' or @id='password' or @type='password']" +
            "   /following::button[contains(@aria-label,'show') or contains(@aria-label,'hide') " +
            "   or contains(@aria-label,'toggle') or contains(@class,'eye')][1]" +
            ") | (" +
            " //span[contains(@class,'eye') or contains(@data-icon,'eye')]/ancestor::button[1]" +
            ")"
    );

    // Locator for error messages (toast, inline error, or aria-alerts)
    private final By errorMessage = By.xpath(
            "//*[contains(@class,'error') or contains(@class,'alert') " +
            "or @role='alert' or contains(.,'invalid') " +
            "or contains(.,'Incorrect') or contains(.,'wrong')]"
    );

    // ========= Constructor =========

    /**
     * Constructor initializes LoginPage with driver and wait.
     * Ensures page is loaded by waiting for either UserID or Password field.
     */
    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(userIdInput),
                ExpectedConditions.visibilityOfElementLocated(passwordInput)
        ));
    }

    // ========= Page Interactions =========

    // Checks if the User ID input is visible
    public boolean isUserIdVisible() {
        return isDisplayed(userIdInput);
    }

    // Checks if the Password input is visible
    public boolean isPasswordVisible() {
        return isDisplayed(passwordInput);
    }

    // Checks if the eye toggle (password show/hide) is visible
    public boolean isEyeToggleVisible() {
        return isDisplayed(eyeToggle);
    }

    // Checks if the Login button is visible
    public boolean isLoginButtonVisible() {
        return isDisplayed(loginButton);
    }

    // Types text into the User ID field
    public void typeUserId(String text) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(userIdInput));
        el.clear(); // Clear any pre-filled value
        el.sendKeys(text);
    }

    // Types text into the Password field
    public void typePassword(String text) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        el.clear(); // Clear any pre-filled value
        el.sendKeys(text);
    }

    // Clicks the Login button
    public void clickLogin() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        btn.click();
    }

    // Checks if the Login button is enabled (not disabled)
    public boolean isLoginButtonEnabled() {
        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        String disabled = btn.getAttribute("disabled");
        return btn.isEnabled() && (disabled == null || disabled.equals("false"));
    }

    // Verifies if the password field is masked (input type = "password")
    public boolean isPasswordMasked() {
        WebElement pwd = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        return "password".equalsIgnoreCase(pwd.getAttribute("type"));
    }

    // Toggles the password visibility using the eye icon
    public void togglePasswordVisibility() {
        WebElement toggle = wait.until(ExpectedConditions.elementToBeClickable(eyeToggle));
        toggle.click();
    }

    // Captures and returns error message text if present, otherwise returns empty string
    public String captureErrorMessageIfAny() {
        try {
            WebElement err = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return err.getText().trim();
        } catch (TimeoutException ex) {
            return "";
        }
    }

    // ========= Utility =========

    // Safely checks if an element is displayed without throwing NoSuchElementException
    private boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
