package pageObject.Environments;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import pageObject.BasePage;
import static com.codeborne.selenide.Selenide.*;

public class CreateEnvironmentPage extends EnvironmentsListPage {

    private final String environmentDescription = "Тест 1234";

    private final SelenideElement initialEnvironmentInput = $(By.xpath("//input[@id='env_source']"));
    private final SelenideElement initialEnvironmentErrorMessage
            = $(By.xpath("//div[@class='ant-form-item-explain-error']"));
    private final SelenideElement initialEnvironmentSelectMaster
            = $(By.xpath("//div[@class='ant-select-item-option-content']"));
    private final SelenideElement environmentDescriptionInput = $(By.xpath("//input[@id='env_env_description']"));
    private final SelenideElement submitButton = $(By.xpath("//button[.='OK']"));
    private final SelenideElement deleteButton = $(By.xpath("//button[.='Удалить окружение']"));
    private final SelenideElement confirmDeleteButton = $(By.xpath("//div[@class='ant-popconfirm-buttons']/button[.='OK']"));


    private final SelenideElement preparedStatus = $(By.xpath("//span[@class='ant-tag ant-tag-orange css-htwhyh']"));
    private final SelenideElement readyStatus = $(By.xpath("//span[@class='ant-tag ant-tag-green css-htwhyh']"));

    private final SelenideElement createEnvironmentMessage = $(By.xpath("//div[.='Окружение успешно создано']"));
    private final SelenideElement deleteEnvironmentMessage = $(By.xpath("//div[.='Окружение успешно удалено']"));

    @Step("Проверка отображения всех элементов страницы")
    public CreateEnvironmentPage checkPageCreateEnvironment() {
        initialEnvironmentInput.shouldBe(Condition.enabled);
        environmentDescriptionInput.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Проверка, что кнопка ОК неактивна, если не заполнено название окружения")
    public CreateEnvironmentPage checkDisabledSubmitButton() {
        submitButton.shouldNotBe(Condition.enabled);

        return this;
    }

    @Step("Проверка всплывающего сообщения, если не заполнено название окружения")
    public CreateEnvironmentPage checkInitialEnvironmentErrorMessage() {

        String expectedResult = "Будут перенесены все настройки и данные указанного окружения в новое";

        initialEnvironmentInput.click();
        environmentDescriptionInput.click();
        initialEnvironmentErrorMessage.shouldBe(Condition.enabled);

        Assertions.assertEquals(expectedResult, initialEnvironmentErrorMessage.getText());

        return this;
    }

    @Step("Проверка, что невозможно создать новое окружение, не указав исходное")
    public CreateEnvironmentPage checkCreateEnvironmentWithoutInitialEnv() {
        environmentDescriptionInput.sendKeys(environmentDescription);
        submitButton.click();

        initialEnvironmentInput.shouldBe(Condition.enabled);
        environmentDescriptionInput.shouldBe(Condition.enabled);
        submitButton.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Проверка успешного создания нового окружения")
    public CreateEnvironmentPage checkCreateEnvironment() {
        initialEnvironmentInput.click();
        initialEnvironmentSelectMaster.click();
        environmentDescriptionInput.sendKeys(environmentDescription);
        submitButton.click();

        createEnvironmentMessage.shouldBe(Condition.enabled);
        preparedStatus.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Проверка, что после создания окружения статус сменился на Готово")
    public CreateEnvironmentPage checkCreateEnvironmentStatus() {

        while (preparedStatus.isEnabled()) {
            refresh();
        }

        readyStatus.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Проверка успешного удаления окружения")
    public CreateEnvironmentPage checkDeleteEnvironment() {
        deleteButton.click();
        confirmDeleteButton.click();

        deleteEnvironmentMessage.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Проверка появления окна с предложением сохранить данные в случае ухода со страницы")
    public CreateEnvironmentPage checkSaveWindow() {
        initialEnvironmentInput.click();
        initialEnvironmentSelectMaster.click();
        getMenuContent().click();
        getContinueWithoutSaveButton().shouldBe(Condition.enabled);
        getCloseModalWindowButton().click();
        refresh();

        return this;
    }


}
