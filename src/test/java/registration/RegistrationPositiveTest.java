package registration;

import org.apache.commons.lang3.RandomStringUtils;
import stellar.api.UserAPI;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stellar.pages.MainPage;
import stellar.pages.RegistrationPage;
import stellar.pages.LoginPage;
import tests.BaseTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationPositiveTest extends BaseTest {
    private Map<String, String> userData;
    private String token;

    @Before
    public void setUp() {
        super.setUp();
        userData = generateRandomUserData();
    }

    private Map<String, String> generateRandomUserData() {
        String email = RandomStringUtils.randomAlphanumeric(10) + "@example.com";
        String password = RandomStringUtils.randomAlphanumeric(10);
        String name = RandomStringUtils.randomAlphanumeric(10);

        Map<String, String> userData = new HashMap<>();
        userData.put("email", email);
        userData.put("password", password);
        userData.put("name", name);
        return userData;
    }

    @After
    public void tearDown() {
        if (token != null) {
            UserAPI.deleteUser(token);
        }
    }

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    @Description("Тест проверяет успешную регистрацию нового пользователя через UI: " +
            "1. Генерация уникальных тестовых данных " +
            "2. Заполнение формы регистрации " +
            "3. Проверка перехода на страницу входа после регистрации " +
            "4. Удаление пользователя через API после теста")
    public void testSuccessfulRegistration() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.register(
                userData.get("name"),
                userData.get("email"),
                userData.get("password")
        );

        token = UserAPI.loginUserAndGetToken(
                userData.get("email"),
                userData.get("password")
        );
        LoginPage newLoginPage = new LoginPage(driver);
        assertTrue("После регистрации должна отображаться страница входа",
                newLoginPage.isLoginButtonDisplayed());
    }


    @Test
    @DisplayName("Регистрация с паролем короче 6 символов")
    @Description("Тест проверяет обработку ошибки при вводе слишком короткого пароля: " +
            "1. Ввод пароля из 5 символов " +
            "2. Проверка отображения сообщения об ошибке. " +
            "Пользователь не создается, так как регистрация не должна пройти.")
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