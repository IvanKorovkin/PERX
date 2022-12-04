package pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private String basicName = "i.korovkin";
    private String basicPassword = "zu_e8Oyah_p0oh";
    private String demoFPG = "new.xn--80afcdbalict6afooklqi5o.xn--p1ai/";
    public String urlOfMainPageAutorization = "https://" + basicName + ":" + basicPassword + "@" + demoFPG;

    private SelenideElement entranceButton = $(By.xpath("//*[@class=\"login-button\"]"));

    private SelenideElement avatarAuthUser = $(By.xpath("//a[@class='header__top-expert']"));

    private SelenideElement buttonWorkingPanelInMenu = $(By.xpath("//*[contains(text(), 'Рабочая панель')]"));

    @Step("Пройти базовую авторизацию и войти на главную страницу")
    public MainPage entranceToMainPage() {
        open(urlOfMainPageAutorization);

        return this;
    }

    @Step("Кликнуть на кнопку Войти")
    public LoginPage entranceButtonClick() {
        entranceButton.click();

        return page(LoginPage.class);
    }

    @Step("Навести курсор на аватар уже залогиненного пользователя")
    public MainPage moveCursorToAvatarOnMainPage() {
        avatarAuthUser.hover();

        return this;
    }

    @Step("Кликнуть в всплывающем меню пользователя на Рабочую панель")
    public WorkingPanelPage clickToWorkingPanelInUsersMenu() {
        buttonWorkingPanelInMenu.click();

        return page(WorkingPanelPage.class);
    }

}
