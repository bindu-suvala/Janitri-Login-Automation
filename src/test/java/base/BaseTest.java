package tests.base; // Package name (make sure it matches your folder structure)

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    // WebDriver instance for browser control
    protected WebDriver driver;

    // Explicit wait instance for better synchronization
    protected WebDriverWait wait;

    // Base URL of the application under test
    protected static final String BASE_URL = "https://dev-dash.janitri.in/";

    /**
     * Runs once before all tests in the class.
     * Sets up WebDriverManager to automatically handle ChromeDriver binaries.
     */
    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        WebDriverManager.chromedriver().setup();
    }

    /**
     * Runs before each test method.
     * Creates a fresh Chrome browser instance with desired settings.
     */
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        // ===== Notification settings (optional) =====
        // Create a map of preferences to allow/block notifications
        Map<String, Object> prefs = new HashMap<>();
        Map<String, Object> profile = new HashMap<>();
        Map<String, Object> contentSettings = new HashMap<>();
        contentSettings.put("notifications", 1); // 1 = allow, 2 = block
        profile.put("managed_default_content_settings", contentSettings);
        prefs.put("profile", profile);
        options.setExperimentalOption("prefs", prefs);

        // ===== Browser startup arguments =====
        // Uncomment headless for CI/CD execution (no browser window shown)
        // options.addArguments("--headless=new");

        options.addArguments("--start-maximized");   // Start browser maximized
        options.addArguments("--disable-infobars");  // Disable "Chrome is being controlled" info bar
        options.addArguments("--disable-notifications"); // Force block notifications

        // Create a new ChromeDriver instance with the above options
        driver = new ChromeDriver(options);

        // Set implicit wait (not recommended with explicit waits — avoid mixing both)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Create explicit wait instance (better for specific conditions)
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open the application base URL
        driver.get(BASE_URL);
    }

    /**
     * Runs after each test method.
     * Closes the browser to ensure clean state for the next test.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Quit browser and end WebDriver session
        }
    }
}
