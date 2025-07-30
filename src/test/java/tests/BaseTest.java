package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import stellar.config.WebDriverConfig;

public abstract class BaseTest {
    protected WebDriver driver;
    private final String browser = System.getProperty("browser", "chrome");

    @Before
    public void setUp() {
        switch (browser) {
            case "chrome":
                driver = WebDriverConfig.createChromeDriver();
                break;
            case "yandex":
                driver = WebDriverConfig.createYandexDriver();
                break;
            default:
                throw new IllegalArgumentException("Unknown browser: " + browser);
        }
        driver.get("https://stellarburgers.nomoreparties.site");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}