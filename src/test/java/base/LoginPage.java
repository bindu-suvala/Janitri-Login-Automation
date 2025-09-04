package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Resilient locators (cover common variants)
    private final By userIdInput = By.xpath(
            "//input[@name='email' or @id='email' or @type='email' or contains(@placeholder,'Email') or contains(@placeholder,'User')]"
    );

    private final By passwordInput = By.xpath(
            "//input[@name='password' or @id='password' or @type='password' or contains(@placeholder,'Password')]"
    );

    private final By loginButton = By.xpath(
            "//button[@type='submit' or contains(.,'Log in') or contains(.,'Login')]"
    );

    // Eye toggle commonly next to password input
    private final By eyeToggle = By.xpath(
            "(" +
            " //input[@name='password' or @id='password' or @type='password']" +
            "   /following::button[contains(@aria-label,'show') or contains(@aria-label,'hide') or contains(@aria-label,'toggle') or contains(@class,'eye')][1]" +
            ") | (" +
            " //span[contains(@class,'eye') or contains(@data-icon,'eye')]/ancestor::button[1]" +
            ")"
    );

    // Common error containers (toast/inline/aria-alert)
    private final By errorMessage = By.xpath(
            "//*[contains(@class,'error') or contains(@class,'alert') or @role='alert' or contains(.,'invalid') or contains(.,'Incorrect') or contains(.,'wrong')]"
    );

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(userIdInput),
                ExpectedConditions.visibilityOfElementLocated(passwordInput)
        ));
    }

    public boolean isUserIdVisible() {
        return isDisplayed(userIdInput);
    }

    public boolean isPasswordVisible() {
        return isDisplayed(passwordInput);
    }

    public boolean isEyeToggleVisible() {
        return isDisplayed(eyeToggle);
    }

    public boolean isLoginButtonVisible() {
        return isDisplayed(loginButton);
    }

    public void typeUserId(String text) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(userIdInput));
        el.clear();
        el.sendKeys(text);
    }

    public void typePassword(String text) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        el.clear();
        el.sendKeys(text);
    }

    public void clickLogin() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        btn.click();
    }

    public boolean isLoginButtonEnabled() {
        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        String disabled = btn.getAttribute("disabled");
        return btn.isEnabled() && (disabled == null || disabled.equals("false"));
    }

    public boolean isPasswordMasked() {
        WebElement pwd = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        return "password".equalsIgnoreCase(pwd.getAttribute("type"));
    }

    public void togglePasswordVisibility() {
        WebElement toggle = wait.until(ExpectedConditions.elementToBeClickable(eyeToggle));
        toggle.click();
    }

    public String captureErrorMessageIfAny() {
        try {
            WebElement err = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return err.getText().trim();
        } catch (TimeoutException ex) {
            return "";
        }
    }

    private boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
