package pageObject.Roles;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Selenide.*;

public class CreateRolePage extends RolesListPage {

    private final String roleName = "Avtotest";

    private final SelenideElement roleIdInput = $(By.xpath("//input[@id='role_role_id']"));
    private final SelenideElement roleDescriptionInput = $(By.xpath("//input[@id='role_role_description']"));
    private final SelenideElement environmentAccessInput = $(".ant-select-selection-overflow");
    private final SelenideElement allowManagementCheckbox = $(By.xpath("//input[@id='role_role_allowManagement']"));
    private final SelenideElement addRuleButton = $(By.xpath("//span[.='Добавить правило']"));
    private final SelenideElement submitButton = $(By.xpath("//button[.='OK']"));
    private final SelenideElement accessRuleButton = $(By.xpath("//span[@class='ant-collapse-header-text']"));
    private final SelenideElement deleteRoleButton = $(By.xpath("//button[.='Удалить роль']"));

    private final SelenideElement roleIdErrorMessage = $(By.xpath("//div[@class='ant-form-item-explain-error']"));

    private final SelenideElement addRuleTitle = $(By.xpath("//div[.='Добавление правила']"));
    private final SelenideElement addRuleCollectionsInput = $(By.xpath("//input[@id='collectionIds']"));
    private final SelenideElement addRuleOKButton = $(By.xpath("(//button[.='OK'])[2]"));
    private final SelenideElement deleteRuleOKButton = $(By.xpath("(//button[.='OK'])[3]"));
    private final SelenideElement deleteRuleButton
            = $(By.xpath("//button[@class='ant-btn css-htwhyh ant-btn-default ant-btn-sm ant-btn-icon-only ant-btn-dangerous']"));

    private final SelenideElement createRoleMessage = $(By.xpath("//div[.='Роль " + roleName + " успешно создана']"));
    private final SelenideElement deleteRoleMessage = $(By.xpath("//div[.='Роль " + roleName + " успешно удалена']"));
    private final SelenideElement repeatIdRoleErrorMessage = $(By.xpath("//div[.='role \"" + roleName + "\": already exists']"));

    @Step("Проверка отображения всех элементов страницы")
    public CreateRolePage checkPageCreateRole() {
        roleIdInput.shouldBe(Condition.enabled);
        roleDescriptionInput.shouldBe(Condition.enabled);
        environmentAccessInput.shouldBe(Condition.enabled);
        allowManagementCheckbox.shouldBe(Condition.enabled);
        addRuleButton.shouldBe(Condition.enabled);
        accessRuleButton.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Проверка, что кнопка ОК неактивна, если не заполнено ID роли")
    public CreateRolePage checkDisabledSubmitButton() {
        submitButton.shouldNotBe(Condition.enabled);

        return this;
    }

    @Step("Проверка всплывающего сообщения, если не заполнено ID роли")
    public CreateRolePage checkRoleIdErrorMessage() {

        String expectedResult = "Пожалуйста, введите ID роли";

        roleIdInput.click();
        roleDescriptionInput.click();
        roleIdErrorMessage.shouldBe(Condition.enabled);

        Assertions.assertEquals(expectedResult, roleIdErrorMessage.getText());

        return this;
    }

    @Step("Проверка невозможности создать роль с пустым ID")
    public CreateRolePage checkCreateRoleWithEmptyId() {

        String expectedResult = "Пожалуйста, введите ID роли";
        String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;

        roleIdInput.sendKeys("t");
        roleIdInput.sendKeys(deleteString);
        submitButton.click();
        roleIdErrorMessage.shouldBe(Condition.enabled);

        Assertions.assertEquals(expectedResult, roleIdErrorMessage.getText());

        return this;
    }

    @Step("Проверка появления окна с предложением сохранить данные в случае ухода со страницы")
    public CreateRolePage checkSaveWindow() {
        roleIdInput.sendKeys("test");
        getMenuContent().click();
        getContinueWithoutSaveButton().shouldBe(Condition.enabled);
        getCloseModalWindowButton().click();
        refresh();

        return this;
    }

    @Step("Проверка добавления правила")
    public CreateRolePage checkAddRule() {

        String expectedResult = "Правила доступа (1)";

        addRuleButton.click();
        addRuleCollectionsInput.sendKeys("web_*");
        addRuleTitle.click();
        addRuleOKButton.click();

        Assertions.assertEquals(expectedResult, accessRuleButton.getText());

        return this;
    }

    @Step("Проверка удаления правила")
    public CreateRolePage checkDeleteRule() {

        String expectedResult = "Правила доступа";

        accessRuleButton.click();
        deleteRuleButton.click();
        deleteRuleOKButton.click();

        Assertions.assertEquals(expectedResult, accessRuleButton.getText());

        return this;
    }

    @Step("Проверка успешного создания роли")
    public CreateRolePage checkCreateRole() {

        roleIdInput.sendKeys(roleName);
        submitButton.click();
        createRoleMessage.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Проверка того, что невозможно создать роль с уже имеющимся ID")
    public CreateRolePage checkCreateRoleRepeatId() {

        roleIdInput.sendKeys(roleName);
        submitButton.click();
        repeatIdRoleErrorMessage.shouldBe(Condition.enabled);
        goToRolesList();
        getContinueWithoutSaveButton().click();

        return this;
    }

    @Step("Проверка удаления роли")
    public RolesListPage checkDeleteRole() {

        deleteRoleButton.click();
        addRuleOKButton.click();
        deleteRoleMessage.shouldBe(Condition.enabled);

        return page(RolesListPage.class);
    }



}
