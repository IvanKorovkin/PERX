package pageObject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import org.openqa.selenium.By;
import pageObject.registries.ExpertiseNewPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class UserProfilePage {

    protected String basicName = "i.korovkin";
    protected String basicPassword = "zu_e8Oyah_p0oh";
    protected String demoFPG = "new.xn--80afcdbalict6afooklqi5o.xn--p1ai";
    protected String urlOfPageUserProfile = "https://" + basicName + ":" + basicPassword + "@" + demoFPG + "/user/profile";

    private SelenideElement avatarOfMainExpert = $(By.xpath("//*[@class=\"header__top-expert-email\"]"));

    private SelenideElement avatarOfWatcher = $(By.xpath("//a[@class='header__top-expert']"));

    private SelenideElement buttonWorkingPanelInMenu = $(By.xpath("//*[contains(text(), 'Рабочая панель')]"));

    @Step("Навести курсор мыши на аватар Главного эксперта")
    public UserProfilePage moveToAvatarOfMainExpert() {
        avatarOfMainExpert.hover();

        return this;
    }

    @Step("Навести курсор мыши на аватар пользователя")
    public UserProfilePage moveToAvatar() {
        avatarOfWatcher.hover();

        return this;
    }

    @Step("Кликнуть в выпадающем меню пользователя на вкладку Рабочая панель")
    public WorkingPanelPage clickToWorkingPanelInUsersMenu() {
        buttonWorkingPanelInMenu.click();

        return page(WorkingPanelPage.class);
    }

    @Step("Перейти в реестр Экспертиза по прямой ссылке")
    public ExpertiseNewPage checkTransitionToExpertiseRegistryByLink() {
        ExpertiseNewPage expertiseNewPage = new ExpertiseNewPage();
        Selenide.open(expertiseNewPage.linkToTheRegistry);

        return page(ExpertiseNewPage.class);
    }

}
