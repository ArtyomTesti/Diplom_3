package stellar.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

/**
 * Класс для настройки и управления WebDriver.
 * Предоставляет методы для создания экземпляров WebDriver для разных браузеров.
 */
public class WebDriverConfig {
  private static final int IMPLICIT_WAIT_TIMEOUT = 10;

    /**
     * Создает и настраивает экземпляр WebDriver для Chrome.
     * Использует WebDriverManager для автоматического управления драйверами.
     *
     * @return настроенный экземпляр WebDriver для Chrome
     */
    public static WebDriver createChromeDriver() {

      WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
        return driver;
    }

    public static WebDriver createYandexDriver() {
        String driverPath = System.getProperty("yandex.driver.path", "C:\\Users\\Artyom\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\yandexdriver.exe");
        String browserPath = System.getProperty("yandex.browser.path", "C:\\Users\\Artyom\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");

        System.setProperty("webdriver.chrome.driver", driverPath);

        ChromeOptions options = new ChromeOptions();
        options.setBinary(browserPath);
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
        return driver;
    }
}