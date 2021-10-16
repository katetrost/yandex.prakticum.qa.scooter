import com.codeborne.selenide.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;
import page.object.MainPage;
import page.object.OrderPage;

import static com.codeborne.selenide.Selenide.*;
import static page.object.MainPage.MAIN_PAGE_URL;

public class OrderPageBottomButtonTest {

    @BeforeClass
    public static void setup() {
        Configuration.browser = "Opera";
    }

    @Test
    public void scooterOrderTestButtonBottom() {
        MainPage mainPage = open(MAIN_PAGE_URL, MainPage.class);
        mainPage.clickAppCookieButton();

        mainPage.signInBottomButton.scrollIntoView(true);

        mainPage.clickSignInButtonBottom();
        OrderPage orderPage = page(OrderPage.class);

        orderPage.fillForWhomTheScooter("Миша", "Буровой",
                "Арбат, 12",
                "Арбатская",
                "89223344556");

        orderPage.fillRentForm("05.10.2021",
                "трое суток",
                "Привет ревьюер");

        orderPage.clickYesButton();

        orderPage.checkOrderOpenAndHasOrderNumber();
    }
}
