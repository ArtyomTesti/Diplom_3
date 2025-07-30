package stellar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.qameta.allure.Step;

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
    @Step("Восстановить пароль для email: {email}")
    public void recoverPassword(String email) {
        waitForVisibility(emailField);
        emailField.sendKeys(email);
        recoverButton.click();
    }

    @Step("Нажать на ссылку 'Войти'")
    public void clickLoginLink() {
        waitForClickable(loginLink);
        loginLink.click();
    }
}