package pageObject.Organizations;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageObject.BasePage;
import pageObject.Spaces.SpacesListPage;

import static com.codeborne.selenide.Selenide.*;

public class OrganizationsListPage extends BasePage {

    private final String url = "https://perxis.pt.perx.ru/orgs";

    private final SelenideElement createOrganizationButton = $(By.xpath("//span[.='Создать организацию']"));
    private final SelenideElement orgName
            = $(By.xpath("//a[.='Организация для avtotestov 123456 7890 !\"№;%:?*()_+']"));
    private final SelenideElement orgNameTest = $(By.xpath("//a[.='Тест 13.04 ']"));
    private final SelenideElement firstOrgInList = $(By.xpath("//li[@class='ant-list-item']"));
    private final SelenideElement stringCountSelect = $(By.xpath("//span[@class='ant-select-selection-item']"));
    private final SelenideElement stringCount100 = $(By.xpath("//div[.='100 / стр.']"));
    private final SelenideElement lastOrgSettings = $(By.xpath("(//a[.='Настройки'])[last()]"));
    private final SelenideElement lastOrgTitle = $(By.xpath("(//h4[@class='ant-list-item-meta-title'])[last()]"));

    public String getUrl() {
        return url;
    }

    @Step("Нажать на кнопку Создать организацию")
    public CreateOrganizationPage clickCreateOrganizationButton() {
        createOrganizationButton.click();

        return page(CreateOrganizationPage.class);
    }

    @Step("Выбрать отображение 100 элементов на странице")
    public OrganizationsListPage set100ElementsOnPage() {
        firstOrgInList.shouldBe(Condition.enabled);
        stringCountSelect.click();
        stringCount100.click();

        return this;
    }

    @Step("Проверка отображения в списке организаций созданной автотестом организации")
    public OrganizationsListPage checkDisplayOrgInList() {
        open(url);
        set100ElementsOnPage();
        orgName.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Перейти в тестовую организацию")
    public SpacesListPage goToTestOrg() {
        open(getUrl());
        set100ElementsOnPage();
        orgNameTest.click();

        return page(SpacesListPage.class);
    }

    @Step("Перейти в настройки созданной автотестом организации")
    public CreateOrganizationPage goToSettingsLastOrg() {
        open(getUrl());
        set100ElementsOnPage();
        lastOrgSettings.click();

        return page(CreateOrganizationPage.class);
    }

    @Step("Перейти в пространства созданной автотестом организации")
    public SpacesListPage goToSpaceLastOrg() {
        open(getUrl());
        lastOrgTitle.click();

        return page(SpacesListPage.class);
    }

    @Step("Проверка того, что удаленная организация не отображается в списке")
    public OrganizationsListPage checkAbsenceDeleteOrgInList() {
        set100ElementsOnPage();
        orgName.shouldNotBe(Condition.exist);

        return this;
    }
}
