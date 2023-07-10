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
import pageObject.Extensions.Forms.FormsExtensionPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@Story("Проверка установки, переустановки и удаления расширения Forms")
public class FormsExtensionTest {

    @BeforeAll
    public static void setUp() {
        Configuration.timeout = 15000;
        Configuration.pageLoadTimeout = 40000;
        //Configuration.headless = true;
    }

    @Order(1)
    @Test
    @DisplayName("Проверка установки расширения Forms")
    public void testInstallExtensionForms() {
        new AuthPage()
                .openAuthPage()
                .loginAdmin()
                .goToOrganizationsList()
                .goToTestOrg()
                .goToTestSpace()
                .goToMasterEnv()
                .goToExtensionsList()
                .goToForms()
                .checkInstallExtensionForms();
    }

    @Order(2)
    @Test
    @DisplayName("Проверка переустановки расширения Forms")
    public void testReinstallExtensionForms() {
        new FormsExtensionPage()
                .checkReinstallExtensionForms();
    }

    @Order(3)
    @Test
    @DisplayName("Проверка того, что нельзя удалить расширение Forms вместе с данными, если роль forms_extension " +
            "используется каким-либо пользователем")
    public void testDeleteExtensionFormsWithData() {
        new FormsExtensionPage()
                .checkDeleteExtensionFormsWithData();
    }

    @Order(4)
    @Test
    @DisplayName("Проверка удаления расширения Forms")
    public void testDeleteExtensionForms() {
        new FormsExtensionPage()
                .checkDeleteExtensionForms();
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
