package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;

public class ProfilePage extends BasePage{

    private final SelenideElement title = $(By.xpath("//span[@class='ant-page-header-heading-title ']"));
    private final SelenideElement inputName = $(By.xpath("//input[@id='user_update_displayName']"));
    private final SelenideElement inputNameTitle = $(By.xpath("//label[@for='user_update_displayName']"));
    private final SelenideElement inputEmail = $(By.xpath("//input[@id='user_update_email']"));
    private final SelenideElement inputAvatarUrl = $(By.xpath("//input[@id='user_update_avatarUrl']"));
    private final SelenideElement buttonRussian = $(By.xpath("//span[.='Русский']"));
    private final SelenideElement buttonEnglish = $(By.xpath("//span[.='English']"));
    private final SelenideElement inputTimeZone = $(By.xpath("//span[@class='ant-select-selection-item']"));
    private final SelenideElement submitButton = $(By.xpath("//button[.='OK']"));

    private final SelenideElement invalidEmailMessage = $(By.xpath("//div[@id='user_update_email_help']"));
    private final SelenideElement changeProfileMessage = $(By.xpath("//div[.='Данные профиля успешно обновлены']"));
    private final SelenideElement changeLanguageEnglishMessage = $(By.xpath("//div[.='Changes saved successfully']"));

    @Step("Проверка отображения всех элементов страницы")
    public ProfilePage checkProfilePage() {
        title.shouldBe(Condition.enabled);
        inputName.shouldBe(Condition.enabled);
        inputEmail.shouldBe(Condition.enabled);
        inputAvatarUrl.shouldBe(Condition.enabled);
        buttonRussian.shouldBe(Condition.enabled);
        buttonEnglish.shouldBe(Condition.enabled);
        inputTimeZone.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Проверка, что кнопка ОК неактивна, если не заполнено название окружения")
    public ProfilePage checkDisabledSubmitButton() {
        submitButton.shouldNotBe(Condition.enabled);

        return this;
    }

    @Step("Проверка успешного изменения имени")
    public ProfilePage checkChangeName() {

        String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;

        inputName.clear();
        inputName.sendKeys(" ");
        submitButton.click();

        changeProfileMessage.shouldBe(Condition.enabled);

        inputName.sendKeys(deleteString);
        inputName.shouldBe(Condition.empty);
        inputName.sendKeys("Test1 Testov");
        submitButton.click();

        changeProfileMessage.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Проверка валидации поля Почта")
    public ProfilePage checkValidationInputEmail(String email) {

        String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;

        inputEmail.sendKeys(deleteString);
        inputEmail.shouldBe(Condition.empty);
        inputEmail.click();
        inputEmail.sendKeys(email);
        submitButton.click();

        invalidEmailMessage.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Проверка переключения меню на английский язык и обратно на русский")
    public ProfilePage checkChangeLanguage() {

        String expectedResultRussianTitle = "Настройки профиля";
        String expectedResultEnglishTitle = "Account settings";
        String expectedResultRussianTitleName = "Отображаемое имя";
        String expectedResultEnglishTitleName = "Display name";

        buttonEnglish.click();
        title.shouldBe(Condition.matchText(expectedResultEnglishTitle));
        Assertions.assertEquals(expectedResultEnglishTitle, title.getText());
        Assertions.assertEquals(expectedResultEnglishTitleName, inputNameTitle.getText());
        changeLanguageEnglishMessage.shouldBe(Condition.enabled);

        buttonRussian.click();
        title.shouldBe(Condition.matchText(expectedResultRussianTitle));
        Assertions.assertEquals(expectedResultRussianTitle, title.getText());
        Assertions.assertEquals(expectedResultRussianTitleName, inputNameTitle.getText());
        changeProfileMessage.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Проверка появления окна с предложением сохранить данные в случае ухода со страницы")
    public ProfilePage checkSaveWindow() {
        inputName.clear();
        inputName.sendKeys(" ");
        getMenuContent().click();
        getContinueWithoutSaveButton().shouldBe(Condition.enabled);
        getCloseModalWindowButton().click();
        refresh();

        return this;
    }

}
