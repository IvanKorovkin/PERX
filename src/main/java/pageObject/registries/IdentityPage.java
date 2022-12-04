package pageObject.registries;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageObject.UserProfilePage;

import static com.codeborne.selenide.Selenide.*;

public class IdentityPage {

    private final String adminPassword = "Vamg8P06#JF6kC2w";

    private SelenideElement systemRolesFilter = $(By.xpath("//span[@data-field='SystemRoles']"));

    private SelenideElement usersInSystemRolesFilter =
            $(By.xpath("//li[text()='Пользователи' and @data-offset-index='19']"));


    private SelenideElement buttonEditSystemRoleOfFirstUser =
            $(By.xpath("(//*[@class='change-role user-table-svg tooltip tooltipstered'])[1]"));

    private SelenideElement nameModalWindowOfEditSystemRole
            = $(By.xpath("//h4[.='Изменение системных ролей пользователя']"));

    private SelenideElement inputForEnterSystemRoleClick = $(By.xpath("//*[@class='change-role-select']"));

    private SelenideElement inputForEnterSystemRoleEnter = $("#SelectedRolesIds_chosen .chosen-search-input");

    private SelenideElement systemRoleOfMainExpertInList = $(By.xpath("//li[@data-option-array-index='10']"));

    private SelenideElement buttonCloseModalWindowOfChangeSystemRole = $("#change-role-modal .modal-header svg");

    private SelenideElement buttonManagementFirstUser
            = $(By.xpath("(//button[@class='btn btn-primary js-show-user-actions'])[1]"));

    private SelenideElement buttonAuthUser = $(By.xpath("//div[@class='user-actions_block btn btn-outline js-auth-user']"));

    private SelenideElement inputEnterAdminPasswordIdentity = $(By.xpath("//input[@type='password']"));

    private SelenideElement buttonConfirmIdentity = $(By.xpath("//button[@id='confirm-change']"));

    private ElementsCollection rolesListInModalWindow = $$(By.xpath("//li[@data-option-array-index]"));

    @Step("Открыть выпадающий список в фильтре системных ролей")
    public IdentityPage openSystemRolesFilter() {
        systemRolesFilter.click();

        return this;
    }

    @Step("Выбрать в выпадающем списке системных ролей роль Пользователи")
    public IdentityPage selectInSystemRolesFilterUsers() {
        usersInSystemRolesFilter.click();

        return this;
    }

    @Step("Кликнуть на кнопку редактирования системной роли первого пользователя по списку")
    public IdentityPage editSystemRoleOfFirstUserInList() {
        buttonEditSystemRoleOfFirstUser.click();

        return this;
    }

    @Step("Ввести в модальном окне редактирования системных ролей какую-либо роль")
    public IdentityPage enterNewSystemRoleInModalWindow(String systemRole) {
        inputForEnterSystemRoleClick.click();
        inputForEnterSystemRoleEnter.sendKeys(systemRole);
        rolesListInModalWindow.findBy(Condition.text(systemRole)).click();

        return this;
    }

    @Step("Закрыть модальное окно редактирования системных ролей")
    public IdentityPage closeModalWindowOfChangeSystemRole() {
        buttonCloseModalWindowOfChangeSystemRole.click();

        return this;
    }

    @Step("Кликнуть на кнопку Управление")
    public IdentityPage clickButtonManagementFirstUser() {
        buttonManagementFirstUser.click();

        return this;
    }

    @Step("Кликнуть на кнопку Авторизоваться за пользователя")
    public IdentityPage clickButtonAuthUser() {
        buttonAuthUser.click();

        return this;
    }

    @Step("Ввести пароль для подтверждения действия")
    public IdentityPage enterAdminPasswordIdentity() {
        inputEnterAdminPasswordIdentity.sendKeys(adminPassword);

        return this;
    }

    @Step("Кликнуть на кнопку Подтвердить")
    public UserProfilePage confirmIdentity() {
        buttonConfirmIdentity.click();
        $(By.xpath("//h3[@class='mr-2']")).shouldBe(Condition.visible);

        return page(UserProfilePage.class);
    }













}
