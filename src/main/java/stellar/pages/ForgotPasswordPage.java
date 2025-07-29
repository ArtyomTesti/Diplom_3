package stellar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Страница восстановления пароля
 */
public class ForgotPasswordPage extends BasePage {

    @FindBy(xpath = "//input[@name='name']")
    private WebElement emailField;

    @FindBy(xpath = "//button[text()='Восстановить']")
    private WebElement recoverButton;

    @FindBy(xpath = "//a[text()='Войти']")
    private WebElement loginLink;

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Восстановление пароля
     * @param email email пользователя
     */
    public void recoverPassword(String email) {
        waitForVisibility(emailField);
        emailField.sendKeys(email);
        recoverButton.click();
    }

    /**
     * Переход на страницу входа
     */
    public void clickLoginLink() {
        waitForClickable(loginLink);
        loginLink.click();
    }
}