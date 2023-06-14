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
import pageObject.Organizations.CreateOrganizationPage;
import pageObject.Organizations.OrganizationsListPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@Story("Проверка создания и удаления организации")
public class CreateOrganizationTest {

    @BeforeAll
    public static void setUp() {
        Configuration.timeout = 20000;
        Configuration.pageLoadTimeout = 40000;
        //Configuration.headless = true;
    }

    @Order(1)
    @Test
    @DisplayName("Проверка отображения всех элементов страницы")
    public void testPageElements() {
        new AuthPage()
                .openAuthPage()
                .loginAdmin()
                .goToOrganizationsList()
                .clickCreateOrganizationButton()
                .checkPageElements();
    }

    @Order(2)
    @Test
    @DisplayName("Проверка, что кнопка ОК неактивна, если не заполнено название организации")
    public void testDisabledButtonOK() {
        new CreateOrganizationPage()
                .checkDisabledButtonOK();
    }

    @Order(3)
    @Test
    @DisplayName("Проверка всплывающего сообщения, если не заполнено название организации")
    public void testOrgNameMessage() {
        new CreateOrganizationPage()
                .checkOrgNameMessage();
    }

    @Order(4)
    @Test
    @DisplayName("Проверка появления ошибки на странице, если название организации пустое (состоит из пробелов)")
    public void testEmptyOrgNameError() {
        new CreateOrganizationPage()
                .checkEmptyOrgNameError();
    }

    @Order(5)
    @Test
    @DisplayName("Проверка появления окна с предложением сохранить данные в случае ухода со страницы")
    public void testSaveWindow() {
        new CreateOrganizationPage()
                .checkSaveWindow();
    }

    @Order(6)
    @Test
    @DisplayName("Проверка успешного создания организации")
    public void testOrgCreate() {
        new CreateOrganizationPage()
                .checkOrgCreate();
    }

    @Order(7)
    @Test
    @DisplayName("Проверка отображения в списке организаций созданной автотестом организации")
    public void testDisplayOrgInList() {
        new OrganizationsListPage()
                .checkDisplayOrgInList();
    }

    @Order(8)
    @Test
    @DisplayName("Проверка неуспешного создания организации с уже существующим названием")
    public void testOrgCreateRepeatName() {
        new OrganizationsListPage()
                .clickCreateOrganizationButton()
                .checkOrgCreateRepeatName();
    }

    @Order(9)
    @Test
    @DisplayName("Проверка удаления организации")
    public void testDeleteOrg() {
        new OrganizationsListPage()
                .goToSettingsLastOrg()
                .deleteOrg();
    }

    @Order(10)
    @Test
    @DisplayName("Проверка того, что удаленная организация не отображается в списке")
    public void testAbsenceDeleteOrgInList() {
        new OrganizationsListPage()
                .checkAbsenceDeleteOrgInList();
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
