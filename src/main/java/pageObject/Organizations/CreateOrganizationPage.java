package pageObject.Organizations;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import pageObject.BasePage;

import static com.codeborne.selenide.Selenide.*;

public class CreateOrganizationPage extends BasePage {

    private final String url = "https://perxis.pt.perx.ru/orgs/create";
    private final String orgName = "Организация для avtotestov 123456 7890 !\"№;%:?*()_+";
    private final String orgDescription = "Римский император Константин I Великий по достоинству оценил выгодное " +
            "местоположение приморского Византия, расположенного на стыке Европы и Азии. Кроме того, на решение " +
            "Константина повлияла неспокойная обстановка в самом Риме: недовольство знати и постоянные распри в борьбе " +
            "за трон. Император хотел увенчать свою реформаторскую деятельность созданием нового административного " +
            "центра огромной державы. Закладка гин лично решил обозначить его границы.";

    private final SelenideElement submitButton = $(By.xpath("//button[@type='submit']"));
    private final SelenideElement deleteButton = $(By.xpath("//button[@type='button']"));
    private final SelenideElement confirmDeleteButton = $(By.xpath("//div[@class='ant-popconfirm-buttons']/button[.='OK']"));

    private final SelenideElement orgNameInput = $(By.xpath("//input[@id='org_name']"));
    private final SelenideElement orgNameMessage = $(By.xpath("//div[@id='org_name_help']"));
    private final SelenideElement orgDescriptionInput = $(By.xpath("//input[@id='org_description']"));
    private final SelenideElement orgLogoInput = $(By.xpath("//input[@id='org_logoUrl']"));

    private final SelenideElement orgNameTitle = $(By.xpath("//label[@title='Название организации']"));
    private final SelenideElement orgDescriptionTitle = $(By.xpath("//label[@title='Описание организации']"));
    private final SelenideElement orgLogoTitle = $(By.xpath("//label[@title='URL логотипа']"));

    private final SelenideElement emptyOrgNameError = $(By.xpath("//div[.='organization name required']"));
    private final SelenideElement createOrgMessage = $(By.xpath("//div[.='Организация успешно создана']"));
    private final SelenideElement repeatOrgNameError = $(By.xpath("//div[.='error create organization: organizations " +
            "with name Организация для avtotestov 123456 7890 !\"№;%:?*()_+ already exists']"));
    private final SelenideElement deleteOrgMessage = $(By.xpath("//div[.='Организация успешно удалена']"));

    public String getUrl() {
        return url;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getOrgDescription() {
        return orgDescription;
    }

    @Step("Проверка отображения всех элементов страницы")
    public CreateOrganizationPage checkPageElements() {
        orgNameTitle.shouldBe(Condition.visible);
        orgDescriptionTitle.shouldBe(Condition.visible);
        orgLogoTitle.shouldBe(Condition.visible);
        orgNameInput.shouldBe(Condition.enabled);
        orgDescriptionInput.shouldBe(Condition.enabled);
        orgLogoInput.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Проверка, что кнопка ОК неактивна, если не заполнено название организации")
    public CreateOrganizationPage checkDisabledButtonOK() {
        submitButton.shouldNotBe(Condition.enabled);

        return this;
    }

    @Step("Проверка всплывающего сообщения, если не заполнено название организации")
    public CreateOrganizationPage checkOrgNameMessage() {

        String expectedResult = "Пожалуйста, введите Название организации";

        orgNameInput.click();
        orgDescriptionInput.click();
        orgNameMessage.shouldBe(Condition.enabled);

        Assertions.assertEquals(expectedResult, orgNameMessage.getText());

        return this;
    }

    @Step("Проверка появления ошибки на странице, если название организации пустое (состоит из пробелов)")
    public CreateOrganizationPage checkEmptyOrgNameError() {
        orgNameInput.sendKeys("    ");
        submitButton.click();
        emptyOrgNameError.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Проверка успешного создания организации")
    public CreateOrganizationPage checkOrgCreate() {
        refresh();
        orgNameInput.sendKeys(getOrgName());
        orgDescriptionInput.sendKeys(getOrgDescription());
        submitButton.click();

        createOrgMessage.shouldBe(Condition.enabled);
        deleteButton.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Проверка неуспешного создания организации с уже существующим названием")
    public CreateOrganizationPage checkOrgCreateRepeatName() {
        orgNameInput.sendKeys(getOrgName());
        submitButton.click();
        repeatOrgNameError.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Удалить организацию")
    public OrganizationsListPage deleteOrg() {
        deleteButton.click();
        confirmDeleteButton.click();
        deleteOrgMessage.shouldBe(Condition.enabled);

        return page(OrganizationsListPage.class);
    }


}
