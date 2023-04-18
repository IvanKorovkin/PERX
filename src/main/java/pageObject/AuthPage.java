package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;

public class AuthPage {

    private final String adminName = "test";
    private final String adminPassword = "test123";
    private final String invalidAdminName = "TEST123";
    private final String invalidAdminPassword = "test12345";
    private final String basicURL = "https://perxis.pt.perx.ru/";

    private final SelenideElement inputName = $(By.xpath("//input[@id='username']"));
    private final SelenideElement inputPassword = $(By.xpath("//input[@id='password']"));
    private final SelenideElement entranceButton = $(By.xpath("//input[@id='kc-login']"));
    private final SelenideElement invalidUsernameOrPasswordMessage = $(By.xpath("//span[@id='input-error']"));

    public String getAdminName() {
        return adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public String getBasicURL() {
        return basicURL;
    }

    public AuthPage enterAdminName() {
        inputName.sendKeys(getAdminName());

        return this;
    }

    public AuthPage enterAdminPassword() {
        inputPassword.sendKeys(getAdminPassword());

        return this;
    }

    public AuthPage clickEntranceButton() {
        entranceButton.click();

        return this;
    }

    public AuthPage enterInvalidAdminName() {
        inputName.sendKeys(invalidAdminName);

        return this;
    }

    public AuthPage enterInvalidAdminPassword() {
        inputPassword.sendKeys(invalidAdminPassword);

        return this;
    }

    @Step("Перейти на страницу авторизации")
    public AuthPage openAuthPage() {
        open(getBasicURL());
        entranceButton.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Проверка неуспешного входа - неправильный логин или пароль")
    public void checkUnsuccessfulAuthorization() {

        String expectedResult = "Invalid username or password.";

        Assertions.assertEquals(expectedResult, invalidUsernameOrPasswordMessage.getText());
    }

    @Step("Залогиниться под админом")
    public BasePage loginAdmin() {
        enterAdminName();
        enterAdminPassword();
        clickEntranceButton();

        return page(BasePage.class);
    }
}
