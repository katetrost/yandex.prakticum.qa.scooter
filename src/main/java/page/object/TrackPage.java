package page.object;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class TrackPage {
    //локатор картинка "Такого заказа нет"
    public static Object imgNotFound = $(By.xpath(".//img[@alt='Not found']"));
}
