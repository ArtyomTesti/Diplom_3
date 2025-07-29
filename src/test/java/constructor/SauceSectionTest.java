package constructor;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import stellar.pages.MainPage;
import tests.BaseTest;

import static org.junit.Assert.assertTrue;

/**
 * Тесты раздела "Соусы" в конструкторе
 */
public class SauceSectionTest extends BaseTest {

    @Test
    @DisplayName("Проверка перехода в раздел 'Соусы'")
    public void testSauceSectionNavigation() {
        MainPage mainPage = new MainPage(driver);
        // 1. Проверяем начальное состояние (Булки активны)
        assertTrue("При загрузке должен быть активен раздел 'Булки'",
                mainPage.isSectionActive("Булки"));

        // 2. Переходим в Соусы
        mainPage.selectSaucesSection();

        // 3. Проверяем активность раздела
        assertTrue("После перехода должен быть активен раздел 'Соусы'",
                mainPage.isSectionActive("Соусы"));
    }
}