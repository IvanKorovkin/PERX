package pageObject.Auth;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pageObject.BasePage;

import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage extends BasePage {

    private final SelenideElement registrationNameInput = $(By.xpath("//input[@id='registration_name']"));
    private final SelenideElement registrationDisplayNameInput = $(By.xpath("//input[@id='registration_displayName']"));
    private final SelenideElement registrationEmailInput = $(By.xpath("//input[@id='registration_email']"));
    private final SelenideElement registrationAvatarUrlInput = $(By.xpath("registration_avatarUrl"));
    private final SelenideElement submitButton = $(By.xpath("//button[.='OK']"));

    private final SelenideElement registrationNameInputErrorMessage
            = $(By.xpath("//div[@class='ant-form-item-explain-error']"));
    private final SelenideElement repeatNameErrorMessage
            = $(By.xpath("//div[.='failed to create user: already exists']"));

    @Step("Проверка неуспешной регистрации - уже имеющееся имя пользователя имя")
    public RegistrationPage checkUnsuccessfulRegistrationRepeatName() {
        submitButton.click();
        repeatNameErrorMessage.shouldBe(Condition.enabled);

        return page(RegistrationPage.class);
    }

    @Step("Проверка неуспешной регистрации - пустое имя")
    public RegistrationPage checkUnsuccessfulRegistrationEmptyName() {

        String expectedResult = "Пожалуйста, введите Пользователь";

        String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;

        registrationNameInput.sendKeys(deleteString);
        submitButton.click();
        registrationNameInputErrorMessage.shouldBe(Condition.enabled);

        Assertions.assertEquals(expectedResult, registrationNameInputErrorMessage.getText());

        return page(RegistrationPage.class);
    }

}
