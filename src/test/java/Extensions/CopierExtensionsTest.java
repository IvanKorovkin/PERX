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
import pageObject.Extensions.ExtensionsListPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@Story("Проверка установки, переустановки и удаления расширения Copier")
public class CopierExtensionsTest {

    @BeforeAll
    public static void setUp() {
        Configuration.timeout = 15000;
        Configuration.pageLoadTimeout = 40000;
        //Configuration.headless = true;
    }

    @Order(1)
    @Test
    @DisplayName("Проверка установки расширения Copier")
    public void testInstallExtensionCopier() {
        new AuthPage()
                .openAuthPage()
                .loginAdmin()
                .goToOrganizationsList()
                .goToTestOrg()
                .goToTestSpace()
                .goToMasterEnv()
                .goToExtensionsList()
                .checkInstallExtensionCopier();
    }

    @Order(2)
    @Test
    @DisplayName("Проверка переустановки расширения Copier")
    public void testReinstallExtensionCopier() {
        new ExtensionsListPage()
                .checkReinstallExtensionCopier();
    }

    @Order(3)
    @Test
    @DisplayName("Проверка удаления расширения Copier")
    public void testDeleteExtensionCopier() {
        new ExtensionsListPage()
                .checkDeleteExtensionCopier();
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
