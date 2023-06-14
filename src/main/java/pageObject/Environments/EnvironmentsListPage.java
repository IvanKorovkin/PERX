package pageObject.Environments;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageObject.BasePage;
import pageObject.ContentsModel.ContentsModelListPage;

import java.util.List;
import static com.codeborne.selenide.Selenide.*;

public class EnvironmentsListPage extends BasePage {

    private final String synonymName = "synonym";

    private final SelenideElement masterEnvLink = $(By.xpath("//a[.='master']"));

    private final SelenideElement createEnvironmentButton = $(By.xpath("//span[.='Создать окружение']"));
    private final SelenideElement title = $(By.xpath("//span[@title='Окружения']"));
    private final SelenideElement envName = $(By.xpath("//a[.='Тест 1234']"));

    private final SelenideElement addSynonymButton = $(By.xpath("(//span[.='Добавить синоним'])[last()]"));
    private final SelenideElement synonymNameInput = $(By.xpath("//input[@type='search']"));
    private final SelenideElement submitButton = $(By.xpath("//button[.='OK']"));
    private final SelenideElement createSynonymMessage = $(By.xpath("//div[.='Синоним успешно добавлен']"));
    private final SelenideElement deleteSynonymMessage = $(By.xpath("//div[.='Синоним успешно удален']"));
    private final List<SelenideElement> synonymNameButtons = $$(By.xpath("//span[.='synonym']"));
    private final SelenideElement synonymNameButtonClose = $(By.xpath("//span[@aria-label='close']"));
    private final SelenideElement envSettings = $(By.xpath("(//a[.='Настройки'])[last()]"));

    private final SelenideElement titleMasterEnv = $(By.xpath("//a[.='master']"));

    @Step("Проверка отображения всех элементов страницы")
    public EnvironmentsListPage checkPageEnvironmentList() {
        title.shouldBe(Condition.visible);
        titleMasterEnv.shouldBe(Condition.visible);
        createEnvironmentButton.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Нажать на кнопку Создать окружение")
    public CreateEnvironmentPage clickCreateOrganizationButton() {
        createEnvironmentButton.click();

        return page(CreateEnvironmentPage.class);
    }

    @Step("Перейти в окружение master")
    public ContentsModelListPage goToMasterEnv() {
        masterEnvLink.click();

        return page(ContentsModelListPage.class);
    }

    @Step("Проверка отображения в списке окружений созданного автотестом окружения")
    public EnvironmentsListPage checkDisplayEnvInList() {
        goToEnvironmentList();
        envName.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Проверка добавления синонима для окружения")
    public EnvironmentsListPage checkAddSynonym() {
        addSynonymButton.click();
        synonymNameInput.sendKeys(synonymName);
        submitButton.click();

        createSynonymMessage.shouldBe(Condition.enabled);
        for (SelenideElement synonymNameButton: synonymNameButtons) {
            synonymNameButton.shouldBe(Condition.enabled);
        }

        return this;
    }

    @Step("Проверка удаления синонима для окружения")
    public EnvironmentsListPage checkDeleteSynonym() {

        try {
            synonymNameButtonClose.hover();
            synonymNameButtonClose.click();
        } catch (Exception E) {
            synonymNameButtonClose.shouldBe(Condition.enabled);
            synonymNameButtonClose.hover();
            synonymNameButtonClose.click();
        }

        synonymNameButtonClose.hover();
        synonymNameButtonClose.click();

        deleteSynonymMessage.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Перейти в настройки созданного автотестом окружения")
    public CreateEnvironmentPage goToEnvSettings() {
        envSettings.click();

        return page(CreateEnvironmentPage.class);
    }

    @Step("Проверка того, что удаленное окружение не отображается в списке")
    public EnvironmentsListPage checkAbsenceDeleteEnvInList() {
        envName.shouldNotBe(Condition.exist);

        return this;
    }


}
