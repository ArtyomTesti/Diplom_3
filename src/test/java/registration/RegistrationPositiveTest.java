package registration;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import stellar.pages.MainPage;
import stellar.pages.RegistrationPage;
import stellar.pages.LoginPage;
import tests.BaseTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Тесты для проверки успешной регистрации
 */
public class RegistrationPositiveTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    public void testSuccessfulRegistration() {
        // Генерация уникальных данных
        String timestamp = String.valueOf(System.currentTimeMillis());
        String name = "User_" + timestamp;
        String email = "test_" + timestamp + "@example.com";
        String password = "Pass_" + timestamp.substring(0, 6) + "!";

        // Шаг 1: Переход на страницу регистрации
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        // Шаг 2: Заполнение формы регистрации
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.register(name, email, password);

        // Шаг 3: Проверка успешной регистрации
        LoginPage newLoginPage = new LoginPage(driver);
        assertTrue("После регистрации должна отображаться страница входа",
                newLoginPage.isLoginButtonDisplayed());
    }
    @Test
    @DisplayName("Регистрация с паролем короче 6 символов")
    public void testShortPasswordRegistration() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.register("TestUser", "test@example.com", "12345");

        assertEquals("Некорректный пароль", registrationPage.getErrorMessage());
    }
}