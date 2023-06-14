package pageObject.Extensions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import java.util.List;
import static com.codeborne.selenide.Selenide.*;

public class ExtensionsListPage {

    private final SelenideElement pageTitle = $(By.xpath("//span[@title='Расширения']"));
    private final SelenideElement switchButton = $(By.xpath("//div[@class='ant-switch-handle']"));

    private final SelenideElement copierTitle = $(By.xpath("//div[.='Копирование и создание элементов по шаблону']"));
    private final List<SelenideElement> installExtensionButton = $$(By.xpath("//button[.='Установить']"));

    private final SelenideElement deleteExtensionButton = $(By.xpath("//button[.='Удалить']"));
    private final SelenideElement reinstallExtensionButton = $(By.xpath("//button[.='Переустановить']"));

    private final SelenideElement conflictCheckbox = $(By.xpath("//input[@class='ant-checkbox-input']"));
    private final SelenideElement deleteDataCheckboxDelete = $(By.xpath("(//input[@class='ant-checkbox-input'])[1]"));
    private final SelenideElement installExtensionModalWindowButton = $(By.xpath("(//button[.='Установить'])[11]"));
    private final SelenideElement reinstallExtensionModalWindowButton = $(By.xpath("(//button[.='Переустановить'])[2]"));
    private final SelenideElement successfulInstallModalWindowText
            = $(By.xpath("//span[@class='ant-modal-confirm-title']"));
    private final SelenideElement deleteExtensionModalWindowButton = $(By.xpath("(//button[.='Удалить'])[2]"));
    private final SelenideElement confirmButton = $(By.xpath("//button[.='OK']"));

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
        conflictCheckbox.click();
        deleteExtensionModalWindowButton.click();

        successfulInstallModalWindowText.should(Condition.exactOwnText(expectedResult));

        confirmButton.click();

        deleteExtensionButton.shouldNotBe(Condition.visible);
        reinstallExtensionButton.shouldNotBe(Condition.visible);

        return page(ExtensionsListPage.class);
    }

}
