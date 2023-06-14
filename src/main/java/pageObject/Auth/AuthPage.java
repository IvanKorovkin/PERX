package pageObject.Auth;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import pageObject.BasePage;

import static com.codeborne.selenide.Selenide.*;

public class AuthPage extends BasePage{

    private final String adminName = "test";
    private final String adminPassword = "test123";
    private final String invalidAdminName = "TEST123";
    private final String invalidAdminPassword = "test12345";

    private final String userName = "test1";
    private final String userPassword = "test123";

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

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public AuthPage enterAdminName() {
        inputName.sendKeys(getAdminName());

        return this;
    }

    public AuthPage enterAdminPassword() {
        inputPassword.sendKeys(getAdminPassword());

        return this;
    }

    public AuthPage enterUserName() {
        inputName.sendKeys(getUserName());

        return this;
    }

    public AuthPage enterUserPassword() {
        inputPassword.sendKeys(getUserPassword());

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

    @Step("Залогиниться под обычным пользователем")
    public BasePage loginUser() {
        enterUserName();
        enterUserPassword();
        clickEntranceButton();

        return page(BasePage.class);
    }

    @Step("Перейти на страницу регистрации")
    public RegistrationPage openRegistrationPage() {
        open(getBasicURL());
        loginAdmin();
        open(getRegistrationUrl());

        return page(RegistrationPage.class);
    }
}
