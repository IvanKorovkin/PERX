import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.LogType;
import org.junit.jupiter.api.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import pageObject.Applications.ApplicationsListPage;
import pageObject.Applications.CreateApplicationPage;
import pageObject.Auth.AuthPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@Story("Проверка создания и удаления приложения")
public class CreateApplicationTest {

    @BeforeAll
    public static void setUp() {
        Configuration.timeout = 20000;
        Configuration.pageLoadTimeout = 40000;
        //Configuration.headless = true;
    }

    @Order(1)
    @Test
    @DisplayName("Проверка отображения всех элементов на странице списка приложений")
    public void testApplicationsPage() {
        new AuthPage()
                .openAuthPage()
                .loginAdmin()
                .goToOrganizationsList()
                .goToTestOrg()
                .goToTestSpace()
                .goToMasterEnv()
                .goToApplicationsList()
                .checkApplicationsPage();
    }

    @Order(2)
    @Test
    @DisplayName("Проверка отображения элементов на странице создания приложения")
    public void testCreateApplicationsPage() {
        new ApplicationsListPage()
                .clickCreateApplicationButton()
                .checkCreateApplicationsPage();
    }

    @Order(3)
    @Test
    @DisplayName("Проверка, что кнопка ОК по умолчанию неактивна")
    public void testDisabledSubmitButton() {
        new CreateApplicationPage()
                .checkDisabledSubmitButton();
    }

    @Order(4)
    @Test
    @DisplayName("Проверка всплывающего сообщения, если не заполнено название приложения")
    public void testEmptyAppNameError() {
        new CreateApplicationPage()
                .checkEmptyAppNameError();
    }

    @Order(5)
    @Test
    @DisplayName("Проверка появления окна с предложением сохранить данные в случае ухода со страницы")
    public void testSaveWindow() {
        new CreateApplicationPage()
                .checkSaveWindow();
    }

    @Order(6)
    @Test
    @DisplayName("Проверка создания приложения")
    public void testCreateApplication() {
        new CreateApplicationPage()
                .checkCreateApplication();
    }

    @Order(7)
    @Test
    @DisplayName("Проверка генерации API key")
    public void testGenerateApiKey() {
        new CreateApplicationPage()
                .checkGenerateApiKey();
    }

    @Order(8)
    @Test
    @DisplayName("Проверка отображения созданного автотестом приложения в списке приложений")
    public void testDisplayAppInList() {
        new CreateApplicationPage()
                .goToApplicationsList()
                .checkDisplayAppInList();
    }

    @Order(9)
    @Test
    @DisplayName("Проверка удаления приложения")
    public void testDeleteApp() {
        new ApplicationsListPage()
                .goToTestApp()
                .checkDeleteApp();
    }

    @Order(10)
    @Test
    @DisplayName("Проверка того, что удлаенное приложение не отображается в списке приложений")
    public void testAbsenceDeleteAppInList() {
        new ApplicationsListPage()
                .checkAbsenceDeleteAppInList();
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
