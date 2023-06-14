package pageObject.Spaces;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageObject.BasePage;
import pageObject.Environments.EnvironmentsListPage;
import pageObject.Organizations.OrganizationsListPage;
import static com.codeborne.selenide.Selenide.*;

public class SpacesListPage extends BasePage {

    private final SelenideElement title = $(By.xpath("//span[@title='Пространства']"));
    private final SelenideElement titleDescription
            = $(By.xpath("//span[@title='В которых вы являетесь участником']"));
    private final SelenideElement createSpaceButton = $(By.xpath("//span[.='Создать пространство']"));
    private final SelenideElement spaceName
            = $(By.xpath("//a[.='Пространство для avtotestov 123456 7890 !\"№;%:?*()_+']"));
    private final SelenideElement firstSpaceSettings = $(By.xpath("//a[.='Настройки'][1]"));
    private final SelenideElement spaceNameTest = $(By.xpath("//a[.='тест 1']"));

    @Step("Проверка отображения всех элементов страницы")
    public SpacesListPage checkPageElements() {
        title.shouldBe(Condition.visible);
        titleDescription.shouldBe(Condition.visible);
        createSpaceButton.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Нажать на кнопку Создать пространство")
    public CreateSpacePage clickCreateSpaceButton() {
        createSpaceButton.click();

        return page(CreateSpacePage.class);
    }

    @Step("Перейти в настройки первого пространства")
    public CreateSpacePage goToFirstSpaceSettings() {
        firstSpaceSettings.click();

        return page(CreateSpacePage.class);
    }

    @Step("Перейти в тестовое пространство")
    public EnvironmentsListPage goToTestSpace() {
        spaceNameTest.click();

        return page(EnvironmentsListPage.class);
    }

    @Step("Проверка отображения в списке созданного автотестом пространства")
    public SpacesListPage checkDisplaySpaceInList() {
        goToSpacesList();
        spaceName.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Проверка того, что удаленное пространство не отображается в списке")
    public SpacesListPage checkAbsenceDeleteSpaceInList() {
        spaceName.shouldNotBe(Condition.exist);

        return this;
    }

    public OrganizationsListPage goToOrganizationList() {
        goToOrganizationsList();

        return page(OrganizationsListPage.class);
    }

}
