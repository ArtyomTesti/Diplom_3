package login;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import stellar.pages.MainPage;
import stellar.pages.LoginPage;
import stellar.pages.ForgotPasswordPage;
import tests.BaseTest;

import static org.junit.Assert.assertTrue;

/**
 * Тест входа через ссылку на странице восстановления пароля
 */
public class LoginFromPasswordRecoveryTest extends BaseTest {

    @Test
    @DisplayName("Вход через ссылку на странице восстановления пароля")
    public void testLoginFromPasswordRecovery() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPasswordLink();

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickLoginLink();

        loginPage.login("test-user@example.com", "password123");

        assertTrue("После входа должен отображаться конструктор",
                mainPage.isConstructorHeaderDisplayed());
    }
}