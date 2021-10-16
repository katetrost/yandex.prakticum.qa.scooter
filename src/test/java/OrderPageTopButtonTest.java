import org.junit.Test;
import page.object.MainPage;
import page.object.OrderPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static page.object.MainPage.MAIN_PAGE_URL;

public class OrderPageTopButtonTest {

    @Test
    public void scooterOrderTestTopButton() {
        MainPage mainPage = open(MAIN_PAGE_URL, MainPage.class);
        mainPage.clickSignInTopButton();
        mainPage.clickAppCookieButton();

        OrderPage orderPage = page(OrderPage.class);

        orderPage.fillForWhomTheScooter("Екатерина",
                "Тростянская",
                "Сивково, 12",
                "Сокол",
                "89223344556");

        orderPage.fillRentForm("04.10.2021", "сутки", "");

        orderPage.clickYesButton();

        orderPage.checkOrderOpenAndHasOrderNumber();
    }
}
