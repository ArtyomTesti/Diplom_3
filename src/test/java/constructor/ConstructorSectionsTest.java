package constructor;

import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;
import org.junit.Test;
import stellar.pages.MainPage;
import tests.BaseTest;

import static org.junit.Assert.assertTrue;

/**
 * Тесты разделов конструктора (Булки, Соусы, Начинки)
 */
public class ConstructorSectionsTest extends BaseTest {

    @Test
    @DisplayName("Проверка активности раздела 'Булки' при загрузке страницы")
    @Description("Тест проверяет, что по умолчанию активен раздел 'Булки'")
    public void testBunsSectionIsActiveByDefault() {
        MainPage mainPage = new MainPage(driver);
        assertTrue("При загрузке должен быть активен раздел 'Булки'",
                mainPage.isSectionActive("Булки"));
    }
    @Test
    @DisplayName("Проверка переключения на раздел 'Соусы'")
    @Description("Тест проверяет корректность переключения на раздел 'Соусы'")
    public void testSwitchToSaucesSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.selectSaucesSection();
        assertTrue("После перехода должен быть активен раздел 'Соусы'",
                mainPage.isSectionActive("Соусы"));
    }
    @Test
    @DisplayName("Проверка переключения на раздел 'Начинки'")
    @Description("Тест проверяет корректность переключения на раздел 'Начинки'")
    public void testSwitchToFillingsSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.selectFillingsSection();
        assertTrue("После перехода должен быть активен раздел 'Начинки'",
                mainPage.isSectionActive("Начинки"));
    }
    @Test
    @DisplayName("Проверка возврата в раздел 'Булки' после переключения")
    @Description("Тест проверяет корректность возврата в раздел 'Булки' после другого раздела")
    public void testSwitchBackToBunsSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.selectSaucesSection(); // Переключаемся на соусы
        mainPage.selectBunsSection();   // Возвращаемся к булкам
        assertTrue("После возврата должен быть активен раздел 'Булки'",
                mainPage.isSectionActive("Булки"));
    }
    }
