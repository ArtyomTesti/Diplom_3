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
    @DisplayName("Проверка переключения между разделами конструктора")
    @Description("Тест проверяет корректность переключения между всеми разделами конструктора: " +
            "1. Проверяем начальное состояние (активны Булки) " +
            "2. Переключаемся на Соусы и проверяем " +
            "3. Переключаемся на Начинки и проверяем " +
            "4. Возвращаемся к Буклам и проверяем")
    public void testAllSectionsNavigation() {
        MainPage mainPage = new MainPage(driver);

        // Проверка начального состояния
        assertTrue("При загрузке должен быть активен раздел 'Булки'",
                mainPage.isSectionActive("Булки"));

        // Тест раздела Соусы
        mainPage.selectSaucesSection();
        assertTrue("После перехода должен быть активен раздел 'Соусы'",
                mainPage.isSectionActive("Соусы"));

        // Тест раздела Начинки
        mainPage.selectFillingsSection();
        assertTrue("После перехода должен быть активен раздел 'Начинки'",
                mainPage.isSectionActive("Начинки"));

        // Возврат к Буклам
        mainPage.selectBunsSection();
        waitForSectionStabilization();
        assertTrue("После повторного перехода должен быть активен раздел 'Булки'",
                mainPage.isSectionActive("Булки"));
    }

    private void waitForSectionStabilization() {
        try {
            Thread.sleep(1000); // Пауза 1 секунда
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}