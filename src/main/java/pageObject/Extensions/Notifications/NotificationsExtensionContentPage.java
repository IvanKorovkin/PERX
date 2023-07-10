package pageObject.Extensions.Notifications;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageObject.Content.ContentPage;

import static com.codeborne.selenide.Selenide.*;

public class NotificationsExtensionContentPage extends ContentPage {

    private final SelenideElement notificationsButton
            = $(By.xpath("//span[@class='ant-menu-title-content' and text()='Уведомления']"));
    private final SelenideElement menuNotificationsEmailButton = $(By.xpath("//a[.='Email']"));
    private final SelenideElement menuNotificationsEmailUniSenderGOButton = $(By.xpath("//a[.='Email UniSender GO']"));
    private final SelenideElement menuNotificationsEmailAddressButton = $(By.xpath("//a[.='Email адреса']"));
    private final SelenideElement menuNotificationsWebhookButton = $(By.xpath("//a[.='Webhook']"));
    private final SelenideElement menuNotificationsWebhookServiceButton = $(By.xpath("//a[.='Webhook сервисы']"));
    private final SelenideElement menuNotificationsTextNotificationsButton
            = $(By.xpath("//a[.='Тестовые уведомления']"));
    private final SelenideElement menuNotificationsButton = $(By.xpath("//a[.='Уведомления']"));

    @Step("Проверка отображения в меню контента коллекций расширения Notifications")
    public NotificationsExtensionContentPage checkDisplayNotificationsExtensionCollectionsInContent() {

        refresh();
        notificationsButton.shouldBe(Condition.enabled);
        notificationsButton.click();
        menuNotificationsEmailButton.shouldBe(Condition.enabled);
        menuNotificationsEmailUniSenderGOButton.shouldBe(Condition.enabled);
        menuNotificationsEmailAddressButton.shouldBe(Condition.enabled);
        menuNotificationsWebhookButton.shouldBe(Condition.enabled);
        menuNotificationsWebhookServiceButton.shouldBe(Condition.enabled);
        menuNotificationsTextNotificationsButton.shouldBe(Condition.enabled);
        menuNotificationsButton.shouldBe(Condition.enabled);

        return page(NotificationsExtensionContentPage.class);
    }

    @Step("Проверка того, что в меню контента нет коллекций расширения Notifications")
    public NotificationsExtensionContentPage checkAbsenceNotificationsExtensionCollectionsInContent() {

        refresh();
        notificationsButton.shouldNot(Condition.exist);

        return page(NotificationsExtensionContentPage.class);
    }

}
