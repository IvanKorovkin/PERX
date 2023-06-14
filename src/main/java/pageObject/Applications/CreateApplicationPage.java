package pageObject.Applications;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;

public class CreateApplicationPage extends ApplicationsListPage {

    private final SelenideElement appNameInput = $(By.xpath("//input[@id='client_client_name']"));
    private final SelenideElement appDescriptionInput = $(By.xpath("//input[@id='client_client_description']"));
    private final SelenideElement roleInput = $(By.xpath("//input[@id='client_client_roleId']"));
    private final SelenideElement apiKeyInput = $(By.xpath("//input[@id='client_client_apiKey_key']"));
    private final SelenideElement generateTokenCheckbox = $(By.xpath("//input[@id='client_client_apiKey_rotate']"));
    private final SelenideElement authIdInput = $(By.xpath("//input[@id='client_client_oauth_authId']"));
    private final SelenideElement tokenUrlInput = $(By.xpath("//input[@id='client_client_oauth_tokenUrl']"));
    private final SelenideElement clientIdInput = $(By.xpath("//input[@id='client_client_oauth_clientId']"));
    private final SelenideElement clientSecretInput = $(By.xpath("//input[@id='client_client_oauth_clientSecret']"));
    private final SelenideElement subjectInput = $(By.xpath("//input[@id='client_client_tls_subject']"));
    private final SelenideElement submitButton = $(By.xpath("//button[.='OK']"));

    private final SelenideElement deleteAppButton = $(By.xpath("//button[.='Удалить приложение']"));
    private final SelenideElement deleteSubmitButton = $(By.xpath("(//button[.='OK'])[2]"));

    private final SelenideElement emptyAppNameErrorMessage = $(By.xpath("//div[@id='client_client_name_help']"));
    private final SelenideElement createAppMessage = $(By.xpath("//div[.='Приложение Test_app успешно создано']"));
    private final SelenideElement updateAppMessage = $(By.xpath("//div[.='Приложение Test_app успешно обновлено']"));
    private final SelenideElement deleteAppMessage = $(By.xpath("//div[.='Приложение Test_app успешно удалено']"));

    @Step("Проверка отображения элементов на странице создания приложения")
    public CreateApplicationPage checkCreateApplicationsPage() {
        appNameInput.shouldBe(Condition.enabled);
        appDescriptionInput.shouldBe(Condition.enabled);
        roleInput.shouldBe(Condition.enabled);
        apiKeyInput.shouldBe(Condition.enabled);
        generateTokenCheckbox.shouldBe(Condition.enabled);
        authIdInput.shouldBe(Condition.enabled);
        tokenUrlInput.shouldBe(Condition.enabled);
        clientIdInput.shouldBe(Condition.enabled);
        clientSecretInput.shouldBe(Condition.enabled);
        subjectInput.shouldBe(Condition.enabled);
        submitButton.shouldBe(Condition.visible);

        return this;
    }

    @Step("Проверка, что кнопка ОК по умолчанию неактивна")
    public CreateApplicationPage checkDisabledSubmitButton() {
        submitButton.shouldNotBe(Condition.enabled);

        return this;
    }

    @Step("Проверка всплывающего сообщения, если не заполнено название приложения")
    public CreateApplicationPage checkEmptyAppNameError() {
        appDescriptionInput.click();
        appDescriptionInput.sendKeys("T");
        submitButton.click();

        emptyAppNameErrorMessage.shouldBe(Condition.enabled);

        return this;
    }


    @Step("Проверка появления окна с предложением сохранить данные в случае ухода со страницы")
    public CreateApplicationPage checkSaveWindow() {
        getMenuContent().click();
        getContinueWithoutSaveButton().shouldBe(Condition.enabled);
        getCloseModalWindowButton().click();
        refresh();

        return this;
    }

    @Step("Проверка создания приложения")
    public CreateApplicationPage checkCreateApplication() {
        appNameInput.sendKeys("Test_app");
        submitButton.click();

        createAppMessage.shouldBe(Condition.enabled);
        deleteAppButton.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Проверка генерации API key")
    public CreateApplicationPage checkGenerateApiKey() {

        generateTokenCheckbox.click();
        submitButton.click();

        updateAppMessage.shouldBe(Condition.enabled);
        Assertions.assertNotNull(apiKeyInput.getText());

        return this;
    }

    @Step("Проверка удаления приложения")
    public ApplicationsListPage checkDeleteApp() {

        updateAppMessage.shouldNotBe(Condition.visible);

        deleteAppButton.click();
        deleteSubmitButton.click();

        deleteAppMessage.shouldBe(Condition.enabled);

        return page(ApplicationsListPage.class);
    }



}
