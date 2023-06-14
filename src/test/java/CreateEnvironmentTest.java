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
import pageObject.Environments.CreateEnvironmentPage;
import pageObject.Environments.EnvironmentsListPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@Story("Проверка создания и удаления окружения")
public class CreateEnvironmentTest {

    @BeforeAll
    public static void setUp() {
        Configuration.timeout = 20000;
        Configuration.pageLoadTimeout = 40000;
        //Configuration.headless = true;
    }

    @Order(1)
    @Test
    @DisplayName("Проверка отображения всех элементов на странице списка окружений")
    public void testPageEnvironmentList() {
        new AuthPage()
                .openAuthPage()
                .loginAdmin()
                .goToOrganizationsList()
                .goToTestOrg()
                .goToTestSpace()
                .checkPageEnvironmentList();
    }

    @Order(2)
    @Test
    @DisplayName("Проверка отображения всех элементов на странице создания нового окружения")
    public void testPageCreateEnvironment() {
        new EnvironmentsListPage()
                .clickCreateOrganizationButton()
                .checkPageCreateEnvironment();
    }

    @Order(3)
    @Test
    @DisplayName("Проверка, что кнопка ОК неактивна, если не заполнено название окружения")
    public void testDisabledSubmitButton() {
        new CreateEnvironmentPage()
                .checkDisabledSubmitButton();
    }

    @Order(4)
    @Test
    @DisplayName("Проверка всплывающего сообщения, если не выбрано исходное окружения")
    public void testInitialEnvironmentErrorMessage() {
        new CreateEnvironmentPage()
                .checkInitialEnvironmentErrorMessage();
    }

    @Order(5)
    @Test
    @DisplayName("Проверка, что невозможно создать новое окружение, не указав исходное")
    public void testCreateEnvironmentWithoutInitialEnv() {
        new CreateEnvironmentPage()
                .checkCreateEnvironmentWithoutInitialEnv();
    }

    @Order(6)
    @Test
    @DisplayName("Проверка появления окна с предложением сохранить данные в случае ухода со страницы")
    public void testSaveWindow() {
        new CreateEnvironmentPage()
                .checkSaveWindow();
    }

    @Order(7)
    @Test
    @DisplayName("Проверка успешного создания нового окружения")
    public void testCreateEnvironment() {
        new CreateEnvironmentPage()
                .checkCreateEnvironment();
    }

    @Order(8)
    @Test
    @DisplayName("Проверка отображения в списке окружений созданного автотестом окружения")
    public void testDisplayEnvInList() {
        new EnvironmentsListPage()
                .checkDisplayEnvInList();
    }

    @Order(9)
    @Test
    @DisplayName("Проверка добавления синонима для окружения")
    public void testAddSynonym() {
        new EnvironmentsListPage()
                .checkAddSynonym();
    }

    @Order(10)
    @Test
    @DisplayName("Проверка удаления синонима для окружения")
    public void testDeleteSynonym() {
        new EnvironmentsListPage()
                .checkDeleteSynonym();
    }

    @Order(11)
    @Test
    @DisplayName("Проверка удаления окружения")
    public void testDeleteEnvironment() {
        new EnvironmentsListPage()
                .goToEnvSettings()
                .checkDeleteEnvironment();
    }

    @Order(12)
    @Test
    @DisplayName("Проверка того, что удаленное окружение не отображается в списке")
    public void testAbsenceDeleteEnvInList() {
        new CreateEnvironmentPage()
                .goToEnvironmentList()
                .checkAbsenceDeleteEnvInList();

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
