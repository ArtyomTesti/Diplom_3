package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import stellar.config.WebDriverConfig;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public abstract class BaseTest {
    protected WebDriver driver;

    @Parameterized.Parameter
    public String browser;

    @Parameterized.Parameters(name = "Browser: {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"chrome"},
                {"yandex"}
        });
    }

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