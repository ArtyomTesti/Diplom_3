package stellar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.qameta.allure.Step;

/**
 * Страница авторизации пользователя
 */
public class LoginPage extends BasePage {

    // Поле ввода email
    @FindBy(xpath = "//input[@name='name']")
    private WebElement emailField;

    // Поле ввода пароля
    @FindBy(xpath = "//input[@name='Пароль']")
    private WebElement passwordField;

    // Кнопка входа
    @FindBy(xpath = "//button[text()='Войти']")
    private WebElement loginButton;

    // Ссылка на регистрацию
    @FindBy(xpath = "//a[text()='Зарегистрироваться']")
    private WebElement registerLink;

    // Ссылка на восстановление пароля
    @FindBy(xpath = "//a[text()='Восстановить пароль']")
    private WebElement forgotPasswordLink;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Вход в систему
     * @param email email пользователя
     * @param password пароль пользователя
     */

    @Step("Вход с email: {email} и паролем: {password}")
    public void login(String email, String password) {
        waitForVisibility(emailField);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    /**
     * Переход на страницу регистрации
     */
    @Step("Нажать на ссылку 'Зарегистрироваться'")
    public void clickRegisterLink() {
        waitForClickable(registerLink);
        registerLink.click();
    }

    /**
     * Переход на страницу восстановления пароля
     */
    @Step("Нажать на ссылку 'Восстановить пароль'")
    public void clickForgotPasswordLink() {
        waitForClickable(forgotPasswordLink);
        forgotPasswordLink.click();
    }

    /**
     * Проверка видимости кнопки входа
     * @return true если кнопка отображается
     */
    @Step("Проверить видимость кнопки входа")
    public boolean isLoginButtonDisplayed() {
        waitForVisibility(loginButton);
        return loginButton.isDisplayed();
    }
}