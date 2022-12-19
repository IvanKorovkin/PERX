package pageObject.otherPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageObject.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class AdminProfilePage {

    BasePage basePage = new BasePage();

    private final SelenideElement workingPanelInUsersMenu = $(By.xpath("//*[contains(text(), 'Рабочая панель')]"));

    @Step("Навести курсор мыши на аватар")
    public AdminProfilePage moveCursorToAvatar() {
        basePage.moveCursorToAvatar();

        return page(AdminProfilePage.class);
    }

    @Step("Кликнуть в выпадающем меню на вкладку Рабочая панель")
    public WorkingPanelPage clickToWorkingPanelInUsersMenu() {
        workingPanelInUsersMenu.click();

        return page(WorkingPanelPage.class);
    }
}
