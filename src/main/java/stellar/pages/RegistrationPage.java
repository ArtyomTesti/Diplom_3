package stellar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.qameta.allure.Step;

/**
 * Страница регистрации нового пользователя
 */
public class RegistrationPage extends BasePage {

    @FindBy(xpath = "//input[@type='text' and @name='name']")
    private WebElement nameField;

    @FindBy(xpath = "//label[contains(text(),'Email')]/following-sibling::input")
    private WebElement emailField;

    @FindBy(xpath = "//input[@type='password' or @name='Пароль']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Зарегистрироваться']")
    private WebElement registerButton;

    @FindBy(xpath = "//p[contains(@class, 'input__error')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//a[text()='Войти']")
    private WebElement loginLink;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Заполнение формы регистрации
     * @param name имя пользователя
     * @param Email email пользователя
     * @param password пароль пользователя
     */
    @Step("Регистрация нового пользователя: имя = {name}, email = {Email}")
    public void register(String name, String Email, String password) {
        waitForVisibility(nameField);
        nameField.sendKeys(name);
        emailField.sendKeys(Email);
        passwordField.sendKeys(password);
        registerButton.click();
    }

    /**
     * Получение текста ошибки
     * @return текст сообщения об ошибке
     */
    @Step("Получить текст сообщения об ошибке")
    public String getErrorMessage() {
        waitForVisibility(errorMessage);
        return errorMessage.getText();
    }

    /**
     * Переход на страницу входа
     */
    @Step("Нажать на ссылку 'Войти'")
    public void clickLoginLink() {
        waitForClickable(loginLink);
        loginLink.click();
    }
}