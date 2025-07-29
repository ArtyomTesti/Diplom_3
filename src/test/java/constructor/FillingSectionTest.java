package constructor;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import stellar.pages.MainPage;
import tests.BaseTest;

import static org.junit.Assert.assertTrue;

/**
 * Тесты раздела "Начинки" в конструкторе
 */
public class FillingSectionTest extends BaseTest {

    @Test
    @DisplayName("Проверка перехода в раздел 'Начинки'")
    public void testFillingSectionNavigation() {
        MainPage mainPage = new MainPage(driver);

        // Проверяем начальное состояние
        assertTrue("При загрузке должен быть активен раздел 'Булки'",
                mainPage.isSectionActive("Булки"));

        mainPage.selectFillingsSection();
        assertTrue("Раздел 'Начинки' должен быть активен",
                mainPage.isSectionActive("Начинки"));
    }
}