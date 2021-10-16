package page.object;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

public class MainPage {
    public final static String MAIN_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    //локатор кнопки «Заказать» вверху страницы
    @FindBy(how = How.XPATH, using = ".//div[starts-with(@class, 'Header_Nav')]/button[text()='Заказать']")
    private static SelenideElement signInTopButton;

    //локатор кнопки «сообщения о куках»
    @FindBy(how = How.ID, using = "rcc-confirm-button")
    private static SelenideElement appCookieButton;

    //локатор пункта выпадающего списока «Вопросы о важном»
    @FindBy(className = "accordion__item")
    static private List<WebElement> importantQuestionsItem;

    //локатор кнопки «Заказать» внизу страницы
    @FindBy(how = How.XPATH, using = ".//div[starts-with(@class, 'Home_FinishButton')]/button[text()='Заказать']")
    public SelenideElement signInBottomButton;

    //локатор «Вопросы о важном»
    @FindBy(how = How.XPATH, using = ".//div[text()='Вопросы о важном']")
    public SelenideElement importantQuestions;

    //локатор логотип «Самоката»
    @FindBy(how = How.XPATH, using = ".//img[@alt='Scooter']")
    private SelenideElement logoScooter;

    //локатор логотип «Яндекса»
    @FindBy(how = How.XPATH, using = ".//img[@alt='Yandex']")
    private SelenideElement logoYandex;

    //локатор кнопки Статус заказа
    @FindBy(how = How.XPATH, using = ".//button[text()='Статус заказа']")
    private SelenideElement orderStatusButton;

    //локатор поля ввода "Введите номер заказа"
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Введите номер заказа']")
    private SelenideElement orderNumberField;

    //локатор кнопки "Go!"
    @FindBy(how = How.XPATH, using = ".//button[text()='Go!']")
    private SelenideElement goButton;

    //метод клика по пункту выпадающего списока и проверки что в нем есть текст
    public static SelenideElement getImportantQuestionsItem(String questionText) {
        return $(By.xpath(format(".//div[text()='%s']", questionText)));
    }

    //метод клика по кнопке «Заказать» сверху страницы
    public static void clickSignInTopButton() {
        signInTopButton.click();
    }

    //метод по закрытию сообщения о куках.
    public static void clickAppCookieButton() {
        appCookieButton.click();
    }

    //метод клика по кнопке «Заказать» внизу страницы
    public void clickSignInButtonBottom() {
        signInBottomButton.click();
    }

    //метод клика по логотип «Самоката»
    public void clickLogoScooter() {
        logoScooter.click();
    }

    //метод клика по логотип «Yandex»
    public void clickLogoYandex() {
        logoYandex.click();
    }

    //проверка адреса url
    public void checkAssertEqualsUrlTrue(String url) {
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals("Ошибка в url", url, currentUrl);
    }

    //метод клика по кнопки Статус заказа
    public void clickButtonOrderStatus() {
        orderStatusButton.click();
    }

    //метод заполнения поля ввода "Введите номер заказа"
    public void setOrderNumber(String orderNumber) {
        orderNumberField.setValue(orderNumber);
    }

    //метод клика по кнопки "Go!"
    public void clickButtonGo() {
        goButton.click();
    }

    //метод объединяет ввод полей и клика по кнопке
    public void orderNumber(String orderNumber) { // orderNumber(String orderNumber) -- не удачный нейминг метода, из названия метода должно быть ясно, что реализовано внутри метода.
        clickButtonOrderStatus();
        setOrderNumber(orderNumber);
        clickButtonGo();
    }

    //Проверить, что заказ создался
    public void orderIsProcessedText() {
        $(byXpath(".//div[starts-with(@class, 'Order_ModalHeader')]")).shouldHave(matchText("Заказ оформлен"));
        $(byXpath(".//div[starts-with(@class, 'Order_Text')]")).shouldHave(matchText("Номер заказа: [0-9]+"));
    }

    //проверка адреса url
    public void checkImportantQuestions(String question, String answer) throws Exception {
        SelenideElement item = getImportantQuestionsItem(question).parent().parent();
        item.click();

        WebElement paragraph = item.findElement(By.tagName("p"));

        String paragrphAnswer = paragraph.getText();

        if (paragrphAnswer.equals("")) {
            throw new Exception("В параграфе должен быть текст");
        } else if (!paragrphAnswer.equals(answer)) {
            throw new Exception("Текст ответа не соответствует шаблону");
        }

        if (paragraph.getAttribute("hidden") != null) {
            throw new Exception("Параграф должен быть видимым");
        }
    }
}
