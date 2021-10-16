import org.junit.Test;
import page.object.MainPage;
import page.object.TrackPage;

import static com.codeborne.selenide.Selenide.*;
import static page.object.MainPage.MAIN_PAGE_URL;

public class AdditionalScenariosTest {

    @Test
    public void checkLogoScooter() {
        MainPage OrderPage =
                open("https://qa-scooter.praktikum-services.ru/order",
                        MainPage.class);
        OrderPage.clickAppCookieButton();

        OrderPage.clickLogoScooter();
        OrderPage.checkAssertEqualsUrlTrue("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void clickLogoYandex() {
        MainPage HomePage = open(MAIN_PAGE_URL, MainPage.class);
        HomePage.clickAppCookieButton();

        HomePage.clickLogoYandex();
        switchTo().window("Яндекс");
        HomePage.checkAssertEqualsUrlTrue("https://yandex.ru/");
    }

    @Test
    public void wrongOrderNumber () {
        MainPage HomePage = open(MAIN_PAGE_URL,MainPage.class);
        HomePage.clickAppCookieButton();

        HomePage.orderNumber("77");
        final Object imgNotFound = TrackPage.imgNotFound;
    }
}
