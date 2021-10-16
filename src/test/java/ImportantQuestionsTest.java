import org.junit.Test;
import page.object.MainPage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static page.object.MainPage.MAIN_PAGE_URL;

public class ImportantQuestionsTest {

    @Test
    public void dropdownListTextTest() throws Exception {
        MainPage mainPage = open(MAIN_PAGE_URL, MainPage.class);

        Map<String, String> questions = new HashMap<String, String>();

        questions.put("Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой.");
        questions.put("Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее.");

        mainPage.importantQuestions.scrollIntoView(true);

        for (Map.Entry<String, String> item : questions.entrySet()) {
            mainPage.checkImportantQuestions(item.getKey(), item.getValue());
            System.out.printf("Вопрос: %s  Ответ: %s \n", item.getKey(), item.getValue());
        }
    }

}
