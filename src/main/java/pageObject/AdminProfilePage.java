package pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class AdminProfilePage {

    private SelenideElement avatarOfUser = $(By.xpath("//*[@class=\"header__top-expert-email\"]"));

    private SelenideElement workingPanelInUsersMenu = $(By.xpath("//*[contains(text(), 'Рабочая панель')]"));

    @Step("Навести курсор мыши на аватар админа")
    public AdminProfilePage moveCursorToAvatar() {
        avatarOfUser.hover();

        return page(AdminProfilePage.class);
    }

    @Step("Кликнуть в выпадающем меню на вкладку Рабочая панель")
    public WorkingPanelPage clickToWorkingPanelInUsersMenu() {
        workingPanelInUsersMenu.click();

        return page(WorkingPanelPage.class);
    }
}
