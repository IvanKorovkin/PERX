package pageObject.Extensions.Notifications;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import pageObject.Extensions.ExtensionsListPage;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.refresh;

public class NotificationsExtensionPage extends ExtensionsListPage {

    @Step("Проверка установки расширения Notifications")
    public ExtensionsListPage checkInstallExtensionNotifications() {

        String expectedResult = "Установка расширения Уведомления выполнена успешно";

        switchButton.click();
        installExtensionButton.get(4).shouldBe(Condition.enabled);
        installExtensionButton.get(4).click();
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

    @Step("Проверка переустановки расширения Notifications")
    public ExtensionsListPage checkReinstallExtensionNotifications() {

        String expectedResult = "Переустановка расширения Уведомления выполнено успешно";

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

    @Step("Проверка удаления расширения Notifications")
    public ExtensionsListPage checkDeleteExtensionNotifications() {

        String expectedResult = "Удаление расширения Уведомления выполнено успешно";

        refresh();
        deleteExtensionButton.click();
        conflictCheckboxDeleteWindow.shouldBe(Condition.enabled);
        deleteExtensionModalWindowButton.shouldBe(Condition.enabled);
        deleteDataCheckboxDelete.click();
        conflictCheckboxDeleteWindow.click();
        deleteExtensionModalWindowButton.click();

        successfulInstallModalWindowText.should(Condition.exactOwnText(expectedResult));

        confirmButton.click();

        deleteExtensionButton.shouldNotBe(Condition.visible);
        reinstallExtensionButton.shouldNotBe(Condition.visible);

        return page(ExtensionsListPage.class);
    }

}
