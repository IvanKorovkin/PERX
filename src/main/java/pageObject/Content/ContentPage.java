package pageObject.Content;

import io.qameta.allure.Step;
import pageObject.BasePage;
import pageObject.Extensions.Notifications.NotificationsExtensionContentPage;

import static com.codeborne.selenide.Selenide.page;

public class ContentPage extends BasePage {

    @Step("Перейти в коллекции расширения Notifications")
    public NotificationsExtensionContentPage goToNotificationsExtensionContent() {

        return page(NotificationsExtensionContentPage.class);
    }

}
