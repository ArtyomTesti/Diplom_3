package constructor;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import stellar.pages.MainPage;
import tests.BaseTest;

import static org.junit.Assert.assertTrue;

public class BunSectionTest extends BaseTest {

    @Test
    @DisplayName("Проверка перехода в раздел 'Булки'")
    public void testBunSectionNavigation() {
        MainPage mainPage = new MainPage(driver);


        assertTrue("При загрузке страницы должен быть активен раздел 'Булки'",
                mainPage.isSectionActive("Булки"));


        mainPage.selectSaucesSection();
        assertTrue("После перехода должен быть активен раздел 'Соусы'",
                mainPage.isSectionActive("Соусы"));

        mainPage.selectBunsSection();
        assertTrue("После повторного перехода должен быть активен раздел 'Булки'",
                mainPage.isSectionActive("Булки"));
    }
    }