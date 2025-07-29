package login;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import stellar.pages.MainPage;
import stellar.pages.LoginPage;
import tests.BaseTest;

import static org.junit.Assert.assertTrue;

/**
 * Тест входа через кнопку личного кабинета
 */
public class LoginPersonalAccountTest extends BaseTest {

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    public void testLoginViaPersonalAccount() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("test-user@example.com", "password123");

        assertTrue("После входа должен отображаться конструктор",
                mainPage.isConstructorHeaderDisplayed());
    }
}