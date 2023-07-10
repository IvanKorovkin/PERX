package pageObject.Extensions.Copier;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageObject.Extensions.ExtensionsListPage;

import static com.codeborne.selenide.Selenide.*;

public class CopierExtensionPage extends ExtensionsListPage {

    private final SelenideElement conflictCheckboxDeleteWindow = $(By.xpath("(//input[@class='ant-checkbox-input'])[2]"));

    @Step("Проверка установки расширения Copier")
    public ExtensionsListPage checkInstallExtensionCopier() {

        String expectedResult = "Установка расширения Копирование и создание элементов по шаблону выполнена успешно";

        switchButton.click();
        installExtensionButton.get(0).click();
        conflictCheckbox.click();
        installExtensionModalWindowButton.click();

        successfulInstallModalWindowText.should(Condition.exactOwnText(expectedResult));

        confirmButton.click();

        deleteExtensionButton.shouldBe(Condition.enabled);
        reinstallExtensionButton.shouldBe(Condition.enabled);

        return page(ExtensionsListPage.class);
    }

    @Step("Проверка переустановки расширения Copier")
    public ExtensionsListPage checkReinstallExtensionCopier() {

        String expectedResult = "Переустановка расширения Копирование и создание элементов по шаблону выполнено успешно";

        refresh();
        reinstallExtensionButton.click();
        conflictCheckbox.click();
        reinstallExtensionModalWindowButton.click();

        successfulInstallModalWindowText.should(Condition.exactOwnText(expectedResult));

        confirmButton.click();

        return page(ExtensionsListPage.class);
    }

    @Step("Проверка удаления расширения Copier")
    public ExtensionsListPage checkDeleteExtensionCopier() {

        String expectedResult = "Удаление расширения Копирование и создание элементов по шаблону выполнено успешно";

        refresh();
        deleteExtensionButton.click();
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
