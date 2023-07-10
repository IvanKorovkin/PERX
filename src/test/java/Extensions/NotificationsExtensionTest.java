package Extensions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.LogType;
import org.junit.jupiter.api.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import pageObject.Auth.AuthPage;
import pageObject.Extensions.Notifications.NotificationsExtensionContentPage;
import pageObject.Extensions.Notifications.NotificationsExtensionPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@Story("Проверка установки, переустановки и удаления расширения Notifications")
public class NotificationsExtensionTest {

    @BeforeAll
    public static void setUp() {
        Configuration.timeout = 15000;
        Configuration.pageLoadTimeout = 40000;
        //Configuration.headless = true;
    }

    @Order(1)
    @Test
    @DisplayName("Проверка установки расширения Notifications")
    public void testInstallExtensionNotifications() {
        new AuthPage()
                .openAuthPage()
                .loginAdmin()
                .goToOrganizationsList()
                .goToTestOrg()
                .goToTestSpace()
                .goToMasterEnv()
                .goToExtensionsList()
                .goToNotifications()
                .checkInstallExtensionNotifications();
    }

    @Order(2)
    @Test
    @DisplayName("Проверка переустановки расширения Notifications")
    public void testReinstallExtensionForms() {
        new NotificationsExtensionPage()
                .checkReinstallExtensionNotifications();
    }

    @Order(3)
    @Test
    @DisplayName("Проверка того, что после установки расширения в контенте появляется коллекция Уведомления")
    public void testDisplayNotificationsExtensionCollectionsInContent() {
        new NotificationsExtensionPage()
                .goToContent()
                .goToNotificationsExtensionContent()
                .checkDisplayNotificationsExtensionCollectionsInContent();
    }

    @Order(4)
    @Test
    @DisplayName("Проверка удаления расширения Notifications")
    public void testDeleteExtensionNotifications() {
        new NotificationsExtensionContentPage()
                .goToExtensionsList()
                .goToNotifications()
                .checkDeleteExtensionNotifications();
    }

    @Order(5)
    @Test
    @DisplayName("Проверка того, что после удаления расширения в контенте нет коллекции Уведомления")
    public void testAbsenceNotificationsExtensionCollectionsInContent() {
        new NotificationsExtensionPage()
                .goToContent()
                .goToNotificationsExtensionContent()
                .checkAbsenceNotificationsExtensionCollectionsInContent();
    }

    @AfterEach
    void getLogs() {
        LogEntries browserLogs = WebDriverRunner.getWebDriver().manage().logs().get(String.valueOf(LogType.BROWSER));

        for (LogEntry log: browserLogs) {
            Allure.addAttachment("Сообщения в консоли браузера", log.getMessage());
        }
    }

    @AfterAll
    public static void quitBrowser() {
        Selenide.closeWebDriver();
    }
}
