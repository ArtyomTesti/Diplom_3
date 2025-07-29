package login;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import stellar.pages.MainPage;
import stellar.pages.LoginPage;
import stellar.pages.RegistrationPage;
import tests.BaseTest;

import static org.junit.Assert.assertTrue;

/**
 * Тест входа через ссылку на странице регистрации
 */
public class LoginFromRegistrationTest extends BaseTest {

    @Test
    @DisplayName("Вход через ссылку на странице регистрации")
    public void testLoginFromRegistrationPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickLoginLink();

        loginPage.login("test-user@example.com", "password123");

        assertTrue("После входа должен отображаться конструктор",
                mainPage.isConstructorHeaderDisplayed());
    }
}