package pageObject.otherPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageObject.BasePage;

import static com.codeborne.selenide.Selenide.*;

public class UserProfilePage extends WorkingPanelPage{

    BasePage basePage = new BasePage();

    private final SelenideElement buttonWorkingPanelInMenu = $(By.xpath("//*[contains(text(), 'Рабочая панель')]"));

    @Step("Навести курсор мыши на аватар")
    public UserProfilePage moveCursorToAvatar() {
        basePage.moveCursorToAvatar();

        return this;
    }

    @Step("Кликнуть в выпадающем меню пользователя на вкладку Рабочая панель")
    public WorkingPanelPage clickToWorkingPanelInUsersMenu() {
        buttonWorkingPanelInMenu.click();

        return page(WorkingPanelPage.class);
    }



}
