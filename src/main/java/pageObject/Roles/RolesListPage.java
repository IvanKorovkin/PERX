package pageObject.Roles;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageObject.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class RolesListPage extends BasePage {

    private final SelenideElement createRoleButton = $(By.xpath("//span[.='Создать роль']"));
    private final SelenideElement testRoleName = $(By.xpath("//a[.='Avtotest']"));

    @Step("Нажать на кнопку Создать роль")
    public CreateRolePage clickCreateRoleButton() {
        createRoleButton.click();

        return page(CreateRolePage.class);
    }

    @Step("Проверка отображения созданной автотестом роли в списке ролей")
    public RolesListPage checkDisplayRoleInList() {
        testRoleName.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Перейти в настройки созданной автотестом роли")
    public CreateRolePage goToTestRole() {
        testRoleName.click();

        return page(CreateRolePage.class);
    }

    @Step("Проверка того, что созданная автотестом роль не отображается в списке ролей")
    public RolesListPage checkAbsenceDeleteRoleInList() {
        testRoleName.shouldNotBe(Condition.exist);

        return this;
    }

}
