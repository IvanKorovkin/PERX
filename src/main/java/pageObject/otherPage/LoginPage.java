package pageObject.otherPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    private final SelenideElement usernameField = $(By.xpath("//*[@id=\"Username\"]"));

    private final SelenideElement passwordField = $(By.xpath("//*[@id=\"Password\"]"));

    private final SelenideElement entranceButton = $(By.xpath("//*[@value=\"login\"]"));

    @Step("Ввести логин Админа")
    public LoginPage enterAdminName() {
        String adminName = "lerique@me.com";
        usernameField.sendKeys(adminName);

        return this;
    }

    @Step("Ввести пароль Админа")
    public LoginPage enterAdminPassword() {
        String adminPassword = "Vamg8P06#JF6kC2w";
        passwordField.sendKeys(adminPassword);

        return this;
    }

    @Step("Кликнуть на кнопку Войти")
    public AdminProfilePage clickEntranceButton() {
        entranceButton.click();

        return page(AdminProfilePage.class);
    }



}
