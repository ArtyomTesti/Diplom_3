package login;

import stellar.api.UserAPI;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stellar.pages.MainPage;
import stellar.pages.LoginPage;
import stellar.pages.RegistrationPage;
import stellar.pages.ForgotPasswordPage;
import tests.BaseTest;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class LoginTests extends BaseTest {
    private Map<String, String> userData;
    private String token;

    @Before
    public void setUpUser() {
        userData = UserAPI.createRandomUser();
    }

    @After
    public void tearDownUser() {
        if (token != null) {
            UserAPI.deleteUser(token);
        }
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт'")
    @Description("Cценарий входа через главную кнопку.")
    public void testLoginViaMainButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userData.get("email"), userData.get("password"));

        token = UserAPI.loginUserAndGetToken(userData.get("email"), userData.get("password"));

        assertTrue("После входа должен отображаться конструктор",
                mainPage.isConstructorHeaderDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Сценарий входа через ссылку со страницы регистрации.")
    public void testLoginViaPersonalAccount() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userData.get("email"), userData.get("password"));

        token = UserAPI.loginUserAndGetToken(userData.get("email"), userData.get("password"));

        assertTrue("После входа должен отображаться конструктор",
                mainPage.isConstructorHeaderDisplayed());
    }

    @Test
    @DisplayName("Вход через ссылку на странице регистрации")
    @Description("Сценарий входа через ссылку со страницы регистрации.")
    public void testLoginFromRegistrationPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickLoginLink();

        loginPage.login(userData.get("email"), userData.get("password"));

        token = UserAPI.loginUserAndGetToken(userData.get("email"), userData.get("password"));

        assertTrue("После входа должен отображаться конструктор",
                mainPage.isConstructorHeaderDisplayed());
    }

    @Test
    @DisplayName("Вход через ссылку на странице восстановления пароля")
    @Description("Сценарий входа через ссылку со страницы восстановления пароля.")
    public void testLoginFromPasswordRecovery() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPasswordLink();

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickLoginLink();

        loginPage.login(userData.get("email"), userData.get("password"));

        token = UserAPI.loginUserAndGetToken(userData.get("email"), userData.get("password"));

        assertTrue("После входа должен отображаться конструктор",
                mainPage.isConstructorHeaderDisplayed());
    }
}