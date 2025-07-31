package stellar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.qameta.allure.Step;

/**
 * Класс для работы с главной страницей приложения
 */
public class MainPage extends BasePage {

    // Локаторы элементов
    @FindBy(xpath = "//button[text()='Войти в аккаунт']")
    private WebElement loginButton;

    @FindBy(xpath = "//a[@href='/account']")
    private WebElement personalAccountButton;

    @FindBy(xpath = "//h1[text()='Соберите бургер']")
    private WebElement constructorHeader;

    @FindBy(xpath = "//div[contains(@class, 'tab_tab__') and contains(., 'Булки')]")
    private WebElement bunsSection;

    @FindBy(xpath = "//div[contains(@class, 'tab_tab__') and contains(., 'Соусы')]")
    private WebElement saucesSection;

    @FindBy(xpath = "//div[contains(@class, 'tab_tab__') and contains(., 'Начинки')]")
    private WebElement fillingsSection;

    @FindBy(xpath = "//div[contains(@class, 'tab_tab__') and contains(@class, 'current')]")
    private WebElement activeTab;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Клик по кнопке "Войти в аккаунт"
     */
    @Step("Нажать кнопку 'Войти в аккаунт'")
    public void clickLoginButton() {
        waitForClickable(loginButton);
        loginButton.click();
    }

    /**
     * Клик по кнопке "Личный кабинет"
     */
    @Step("Нажать кнопку 'Личный кабинет'")
    public void clickPersonalAccountButton() {
        waitForClickable(personalAccountButton);
        personalAccountButton.click();
    }

    /**
     * Проверка отображения заголовка конструктора
     * @return true если заголовок отображается
     */
    @Step("Проверить отображение заголовка конструктора")
    public boolean isConstructorHeaderDisplayed() {
        waitForVisibility(constructorHeader);
        return constructorHeader.isDisplayed();
    }

    // Методы для работы с разделами конструктора
    @Step("Выбрать раздел 'Булки'")
    public void selectBunsSection() {
        waitForClickable(bunsSection);
        bunsSection.click();
        waitForSectionActive("Булки");
    }

    @Step("Выбрать раздел 'Соусы'")
    public void selectSaucesSection() {
        waitForClickable(saucesSection);
        saucesSection.click();
        waitForSectionActive("Соусы");
    }

    @Step("Выбрать раздел 'Начинки'")
    public void selectFillingsSection() {
        waitForClickable(fillingsSection);
        // Добавляем проверку, что элемент действительно кликабелен
        wait.until(driver -> {
            try {
                fillingsSection.click();
                return true;
            } catch (Exception e) {
                return false;
            }
        });
        waitForSectionActive("Начинки");
    }

    private void waitForSectionActive(String sectionName) {
        WebElement sectionElement = getSectionElement(sectionName);
        // Ожидаем не только наличие класса, но и видимость элемента
        wait.until(ExpectedConditions.and(
                ExpectedConditions.attributeContains(sectionElement, "class", "current"),
                ExpectedConditions.visibilityOf(sectionElement)
        ));
    }

    private WebElement getSectionElement(String sectionName) {
        switch (sectionName) {
            case "Булки":
                return bunsSection;
            case "Соусы":
                return saucesSection;
            case "Начинки":
                return fillingsSection;
            default:
                throw new IllegalArgumentException("Неизвестный раздел: " + sectionName);
        }
    }
    /**
     * Проверка активного раздела конструктора
     * @param sectionName название раздела ("Булки", "Соусы", "Начинки")
     * @return true если раздел активен
     */
    @Step("Проверить активность раздела: {sectionName}")
    public boolean isSectionActive(String sectionName) {
        WebElement sectionElement = getSectionElement(sectionName);
        // Добавляем явное ожидание перед проверкой
        try {
            wait.until(driver ->
                    sectionElement.getAttribute("class").contains("current") &&
                            sectionElement.isDisplayed()
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}