package pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageObject.Applications.ApplicationsListPage;
import pageObject.Environments.EnvironmentsListPage;
import pageObject.Extensions.ExtensionsListPage;
import pageObject.Organizations.OrganizationsListPage;
import pageObject.Roles.RolesListPage;
import pageObject.SpaceMembers.SpaceMembersListPage;
import pageObject.Spaces.SpacesListPage;

import static com.codeborne.selenide.Selenide.*;

public class BasePage {

    private final String basicURL = "https://perxis.pt.perx.ru/";
    private final String registrationUrl = getBasicURL() + "registration";

    private final SelenideElement menuContent = $(By.xpath("//span[.='Контент']"));
    private final SelenideElement menuContentsModel = $(By.xpath("//span[.='Модели контента']"));
    private final SelenideElement menuActions = $(By.xpath("//span[.='Действия']"));
    private final SelenideElement menuExtensions = $(By.xpath("//span[.='Расширения']"));
    private final SelenideElement menuEnvironments = $(By.xpath("//span[.='Окружения']"));
    private final SelenideElement menuSettings = $(By.xpath("//span[.='Настройки']"));

    private final SelenideElement menuSettingsRoles = $(By.xpath("//a[.='Роли']"));
    private final SelenideElement menuSettingsMembers = $(By.xpath("//a[.='Участники']"));
    private final SelenideElement menuSettingsApplications = $(By.xpath("//a[.='Приложения']"));

    private final SelenideElement menuProfileName = $(By.xpath("//span[@aria-label='user']"));
    private final SelenideElement profileSettings = $(By.xpath("//a[.='Настройка профиля']"));
    private final SelenideElement profileOrganizations = $(By.xpath("//span[.='Организации']"));
    private final SelenideElement profileSpaces = $(By.xpath("//a[.='Пространства']"));

    //всплывающее меню сохранения изменений
    private final SelenideElement continueWithoutSaveButton = $(By.xpath("//button[.='Продолжить без сохранения']"));
    private final SelenideElement saveAndContinueButton = $(By.xpath("//button[.='Сохранить и продолжить']"));
    private final SelenideElement publicAndContinueButton = $(By.xpath("//button[.='Опубликовать и продолжить']"));
    private final SelenideElement cancelButton = $(By.xpath("//button[.='Отмена']"));

    public String getBasicURL() {
        return basicURL;
    }

    public String getRegistrationUrl() {
        return registrationUrl;
    }

    public SelenideElement getCloseModalWindowButton() {
        return closeModalWindowButton;
    }

    private final SelenideElement closeModalWindowButton = $(By.xpath("//button[@class='ant-modal-close']"));

    public SelenideElement getMenuContent() {
        return menuContent;
    }

    public SelenideElement getContinueWithoutSaveButton() {
        return continueWithoutSaveButton;
    }

    @Step("Перейти в список организаций")
    public OrganizationsListPage goToOrganizationsList() {
        menuProfileName.click();
        profileOrganizations.click();

        return page(OrganizationsListPage.class);
    }

    @Step("Перейти в список пространств")
    public SpacesListPage goToSpacesList() {
        menuProfileName.click();
        profileSpaces.click();

        return page(SpacesListPage.class);
    }

    @Step("Перейти в список окружений")
    public EnvironmentsListPage goToEnvironmentList() {
        menuEnvironments.click();

        return page(EnvironmentsListPage.class);
    }

    @Step("Перейти в список расширений")
    public ExtensionsListPage goToExtensionsList() {
        menuExtensions.click();

        return page(ExtensionsListPage.class);
    }

    @Step("Перейти в настройки профиля")
    public ProfilePage goProfileSettings() {
        menuProfileName.click();
        profileSettings.click();

        return page(ProfilePage.class);
    }

    @Step("Перейти в список ролей")
    public RolesListPage goToRolesList() {
        menuSettings.click();
        menuSettingsRoles.click();

        return page(RolesListPage.class);
    }

    @Step("Перейти в список участников пространства")
    public SpaceMembersListPage goToSpaceMembersList() {
        menuSettings.click();
        menuSettingsMembers.click();

        return page(SpaceMembersListPage.class);
    }

    @Step("Перейти в список приложений")
    public ApplicationsListPage goToApplicationsList() {
        menuSettings.click();
        menuSettingsApplications.click();

        return page(ApplicationsListPage.class);
    }







}
