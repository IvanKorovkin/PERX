package pageObject.otherPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageObject.BasePage;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    BasePage basePage = new BasePage();

    private final SelenideElement entranceButton = $(By.xpath("//*[@class='login-button']"));

    private final SelenideElement buttonWorkingPanelInMenu = $(By.xpath("//*[contains(text(), 'Рабочая панель')]"));

    @Step("Пройти базовую авторизацию и войти на главную страницу")
    public MainPage entranceToMainPage() {
        open(basePage.getUrl());

        return this;
    }

    @Step("Кликнуть на кнопку Войти")
    public LoginPage entranceButtonClick() {
        entranceButton.click();

        return page(LoginPage.class);
    }

    @Step("Навести курсор на аватар")
    public MainPage moveCursorToAvatar() {
        basePage.moveCursorToAvatar();

        return this;
    }

    @Step("Кликнуть в всплывающем меню пользователя на Рабочую панель")
    public WorkingPanelPage clickToWorkingPanelInUsersMenu() {
        buttonWorkingPanelInMenu.click();

        return page(WorkingPanelPage.class);
    }

}
