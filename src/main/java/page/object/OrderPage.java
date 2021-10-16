package page.object;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selenide.$;

public class OrderPage {

    // Поля формы "Для кого самокат"
    //локатор поля ввода Имя
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='* Имя']")
    private SelenideElement nameField;

    //локатор поля ввода Фамилия
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='* Фамилия']")
    private SelenideElement surnameField;

    //локатор поля ввода Адрес, куда привезти заказ
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='* Адрес: куда привезти заказ']")
    private SelenideElement addressField;

    //локатор поля Станция метро
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='* Станция метро']")
    private SelenideElement metroStationField;

    //локатор поля ввода Телефон
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='* Телефон: на него позвонит курьер']")
    private SelenideElement telephoneField;

    //локатор кнопки Далее
    @FindBy(how = How.XPATH, using = ".//button[text()='Далее']")
    private SelenideElement nextButton;

    // Поля формы "Про аренду"
    //локатор поля ввода Даты
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='* Когда привезти самокат']")
    private SelenideElement dateField;

    //локатор поля * Срок аренды
    @FindBy(how = How.CLASS_NAME, using = "Dropdown-arrow")
    private SelenideElement leaseTerm;

    //локатор поля Цвет самоката чёрный жемчуг
    @FindBy(how = How.ID, using = "black")
    private SelenideElement blackColor;

    //локатор поля Цвет самоката чёрный жемчуг
    @FindBy(how = How.ID, using = "grey")
    private SelenideElement greyColor;

    //локатор поля ввода Комментарий
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Комментарий для курьера']")
    private SelenideElement commentField;

    //локатор кнопки "Заказать" в конце формы Про аренду
    @FindBy(how = How.XPATH, using = ".//div[starts-with(@class, 'Order_Buttons')]/button[text()='Заказать']")
    private SelenideElement orderButton;

    //локатор кнопки "Да" окна "Подтверждения"
    @FindBy(how = How.XPATH, using = ".//button[text()='Да']")
    private SelenideElement yesButton;

    //локатор "Заказ оформлен"
    @FindBy(how = How.XPATH, using = ".//div[starts-with(@class, 'Order_ModalHeader')]")
    private SelenideElement orderIsProcessed;

    //локатор "Номер заказа"
    @FindBy(how = How.XPATH, using = ".//div[starts-with(@class, 'Order_Text')]")
    private SelenideElement orderNumber;

    // Поля формы "Для кого самокат"
    //метод заполнения поля ввода Имя
    public void setUsername(String username) {
        nameField.setValue(username);
    }

    //метод заполнения поля ввода Фамилия
    public void setSurname(String surname) {
        surnameField.setValue(surname);
    }

    //метод заполнения поля ввода Адрес
    public void setAddress(String address) {
        addressField.setValue(address);
    }

    //метод клика по поля Станция метро
    public void setMetroStation(String metro) {
        metroStationField.setValue(metro);
        $(By.xpath(".//div[text()='" + metro + "']")).click();
    }

    //метод заполнения поля ввода Телефон
    public void setTelephone(String telephone) {
        telephoneField.setValue(telephone);
    }

    //метод клика по кнопке «Далее»
    public void clickNextButton() {
        nextButton.click();
    }

    //метод заполнения формы "Для кого самокат": объединяет ввод полей и клика по кнопке
    public void fillForWhomTheScooter(String username, String surname, String address, String metro, String telephone) {
        setUsername(username);
        setSurname(surname);
        setAddress(address);
        setMetroStation(metro);
        setTelephone(telephone);
        clickNextButton();
    }

    // Поля формы "Про аренду"
    //метод заполнения поля ввода Адрес
    public void setDate(String date) {
        dateField.setValue(date);
    }

    //метод клика по поля Срок аренды
    public void setLeaseTerm(String time) {
        leaseTerm.click();
        $(By.xpath(".//div[@class='Dropdown-menu']/div[text()='" + time + "']")).click();
    }

    //метод клика по "чёрный жемчуг"
    public void clickButtonBlackColor() {
        blackColor.click();
    }

    //метод клика по "серая безысходность"
    public void clickButtonGreyColor() {
        greyColor.click();
    }

    //метод заполнения поля ввода Комментарий
    public void setComment(String comment) {
        commentField.setValue(comment);
    }

    //метод клика по кнопке «Заказать»
    public void clickOrderButton() {
        orderButton.click();
    }

    //метод заполнения формы "Про аренду": объединяет ввод полей и клика по кнопке
    public void fillRentForm(String date, String time, String comment) {
        setDate(date);
        setLeaseTerm(time);
        clickButtonBlackColor();
        setComment(comment);
        clickOrderButton();
    }

    //метод клика по кнопке "Да" окна "Подтверждения"
    public void clickYesButton() {
        yesButton.click();
    }

    //Проверить, что заказ создался
    public void checkOrderOpenAndHasOrderNumber() {
        orderIsProcessed.shouldHave(matchText("Заказ оформлен"));
        orderNumber.shouldHave(matchText("Номер заказа: [0-9]+"));
    }
}
