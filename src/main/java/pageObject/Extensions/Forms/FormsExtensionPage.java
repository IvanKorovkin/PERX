package pageObject.Extensions.Forms;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageObject.Extensions.ExtensionsListPage;

import static com.codeborne.selenide.Selenide.*;

public class FormsExtensionPage extends ExtensionsListPage {

    private final SelenideElement deleteExtensionWithDataError
            = $(By.xpath("//div[@class='ant-modal-confirm-content']//child::div/div[1]"));

    @Step("Проверка установки расширения Forms")
    public ExtensionsListPage checkInstallExtensionForms() {

        String expectedResult = "Установка расширения Формы выполнена успешно";

        switchButton.click();
        installExtensionButton.get(2).shouldBe(Condition.enabled);
        installExtensionButton.get(2).click();
        try {
            conflictCheckbox.shouldBe(Condition.enabled);
            installExtensionModalWindowButton.shouldBe(Condition.enabled);
        } catch (Exception E) {
            installExtensionButton.get(2).click();
            conflictCheckbox.shouldBe(Condition.enabled);
            installExtensionModalWindowButton.shouldBe(Condition.enabled);
        }
        conflictCheckbox.click();
        installExtensionModalWindowButton.click();

        successfulInstallModalWindowText.should(Condition.exactOwnText(expectedResult));

        confirmButton.click();

        deleteExtensionButton.shouldBe(Condition.enabled);
        reinstallExtensionButton.shouldBe(Condition.enabled);

        return page(ExtensionsListPage.class);
    }

    @Step("Проверка переустановки расширения Forms")
    public ExtensionsListPage checkReinstallExtensionForms() {

        String expectedResult = "Переустановка расширения Формы выполнено успешно";

        refresh();
        reinstallExtensionButton.shouldBe(Condition.enabled);
        reinstallExtensionButton.click();

        try {
            conflictCheckbox.shouldBe(Condition.enabled);
            reinstallExtensionModalWindowButton.shouldBe(Condition.enabled);
            conflictCheckbox.click();
            reinstallExtensionModalWindowButton.click();
        } catch (Exception E) {
            reinstallExtensionButton.click();
        }

        conflictCheckbox.click();
        reinstallExtensionModalWindowButton.click();

        successfulInstallModalWindowText.should(Condition.exactOwnText(expectedResult));

        confirmButton.click();
        refresh();

        return page(ExtensionsListPage.class);
    }

    @Step("Проверка того, что нельзя удалить расширение Forms вместе с данными, если роль forms_extension используется" +
            "каким-либо пользователем")
    public ExtensionsListPage checkDeleteExtensionFormsWithData() {

        String expectedResultTitle = "При удалении расширения Формы возникли ошибки";
        String expectedResultContent = "failed to uninstall role: role forms_extension is being used by user";

        refresh();
        deleteExtensionButton.shouldBe(Condition.enabled);
        deleteExtensionButton.click();

        try {
            conflictCheckboxDeleteWindow.shouldBe(Condition.enabled);
            deleteExtensionModalWindowButton.shouldBe(Condition.enabled);
        } catch (Exception E) {
            deleteExtensionButton.click();
        }
        deleteDataCheckboxDelete.click();
        conflictCheckboxDeleteWindow.click();
        deleteExtensionModalWindowButton.click();

        successfulInstallModalWindowText.should(Condition.exactOwnText(expectedResultTitle));
        deleteExtensionWithDataError.should(Condition.partialText(expectedResultContent));

        confirmButton.click();

        deleteExtensionButton.shouldBe(Condition.enabled);
        reinstallExtensionButton.shouldBe(Condition.enabled);

        return page(ExtensionsListPage.class);
    }

    @Step("Проверка удаления расширения Forms")
    public ExtensionsListPage checkDeleteExtensionForms() {

        String expectedResult = "Удаление расширения Формы выполнено успешно";

        refresh();
        deleteExtensionButton.click();
        conflictCheckboxDeleteWindow.shouldBe(Condition.enabled);
        deleteExtensionModalWindowButton.shouldBe(Condition.enabled);
        conflictCheckboxDeleteWindow.click();
        deleteExtensionModalWindowButton.click();

        successfulInstallModalWindowText.should(Condition.exactOwnText(expectedResult));

        confirmButton.click();

        deleteExtensionButton.shouldNotBe(Condition.visible);
        reinstallExtensionButton.shouldNotBe(Condition.visible);

        return page(ExtensionsListPage.class);
    }

}
