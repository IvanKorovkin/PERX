package pageObject.Extensions;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pageObject.BasePage;
import pageObject.Extensions.Copier.CopierExtensionPage;
import pageObject.Extensions.Forms.FormsExtensionPage;
import pageObject.Extensions.Notifications.NotificationsExtensionPage;

import java.util.List;
import static com.codeborne.selenide.Selenide.*;

public class ExtensionsListPage extends BasePage {

    protected final SelenideElement pageTitle = $(By.xpath("//span[@title='Расширения']"));
    protected final SelenideElement switchButton = $(By.xpath("//div[@class='ant-switch-handle']"));

    protected final SelenideElement copierTitle = $(By.xpath("//div[.='Копирование и создание элементов по шаблону']"));
    protected final List<SelenideElement> installExtensionButton = $$(By.xpath("//button[.='Установить']"));

    protected final SelenideElement deleteExtensionButton = $(By.xpath("//button[.='Удалить']"));
    protected final SelenideElement reinstallExtensionButton = $(By.xpath("//button[.='Переустановить']"));

    protected final SelenideElement conflictCheckbox = $(By.xpath("//input[@class='ant-checkbox-input']"));
    protected final SelenideElement deleteDataCheckboxDelete = $(By.xpath("(//input[@class='ant-checkbox-input'])[1]"));
    protected final SelenideElement installExtensionModalWindowButton = $(By.xpath("(//button[.='Установить'])[11]"));
    protected final SelenideElement reinstallExtensionModalWindowButton = $(By.xpath("(//button[.='Переустановить'])[2]"));
    protected final SelenideElement successfulInstallModalWindowText
            = $(By.xpath("//span[@class='ant-modal-confirm-title']"));
    protected final SelenideElement deleteExtensionModalWindowButton = $(By.xpath("(//button[.='Удалить'])[2]"));
    protected final SelenideElement confirmButton = $(By.xpath("//button[.='OK']"));

    protected final SelenideElement conflictCheckboxDeleteWindow = $(By.xpath("(//input[@class='ant-checkbox-input'])[2]"));

    public CopierExtensionPage goToCopier() {

         return page(CopierExtensionPage.class);
    }

    public FormsExtensionPage goToForms() {

        return page(FormsExtensionPage.class);
    }

    public NotificationsExtensionPage goToNotifications() {

        return page(NotificationsExtensionPage.class);
    }

}
