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
import pageObject.Roles.CreateRolePage;
import pageObject.Roles.RolesListPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@Story("Проверка создания и удаления роли")
public class CreateRoleTest {

    @BeforeAll
    public static void setUp() {
        Configuration.timeout = 20000;
        Configuration.pageLoadTimeout = 40000;
        //Configuration.headless = true;
    }

    @Order(1)
    @Test
    @DisplayName("Проверка отображения всех элементов на странице создания роли")
    public void testPageCreateRole() {
        new AuthPage()
                .openAuthPage()
                .loginAdmin()
                .goToOrganizationsList()
                .goToTestOrg()
                .goToTestSpace()
                .goToMasterEnv()
                .goToRolesList()
                .clickCreateRoleButton()
                .checkPageCreateRole();
    }

    @Order(2)
    @Test
    @DisplayName("Проверка, что кнопка ОК неактивна, если не заполнено ID роли")
    public void testDisabledSubmitButton() {
        new CreateRolePage()
                .checkDisabledSubmitButton();
    }

    @Order(3)
    @Test
    @DisplayName("Проверка всплывающего сообщения, если не заполнено ID роли")
    public void testRoleIdErrorMessage() {
        new CreateRolePage()
                .checkRoleIdErrorMessage();
    }

    @Order(4)
    @Test
    @DisplayName("Проверка невозможности создать роль с пустым ID")
    public void testCreateRoleWithEmptyId() {
        new CreateRolePage()
                .checkCreateRoleWithEmptyId();
    }

    @Order(5)
    @Test
    @DisplayName("Проверка появления окна с предложением сохранить данные в случае ухода со страницы")
    public void testSaveWindow() {
        new CreateRolePage()
                .checkSaveWindow();
    }

    @Order(6)
    @Test
    @DisplayName("Проверка добавления правила")
    public void testAddRule() {
        new CreateRolePage()
                .checkAddRule();
    }

    @Order(7)
    @Test
    @DisplayName("Проверка удаления правила")
    public void testDeleteRule() {
        new CreateRolePage()
                .checkDeleteRule();
    }

    @Order(8)
    @Test
    @DisplayName("Проверка успешного создания роли")
    public void testCreateRole() {
        new CreateRolePage()
                .checkCreateRole();
    }

    @Order(9)
    @Test
    @DisplayName("Проверка отображения созданной автотестом роли в списке ролей")
    public void testDisplayRoleInList() {
        new CreateRolePage()
                .goToRolesList()
                .checkDisplayRoleInList();
    }

    @Order(10)
    @Test
    @DisplayName("Проверка того, что невозможно создать роль с уже имеющимся ID")
    public void testCreateRoleRepeatId() {
        new RolesListPage()
                .clickCreateRoleButton()
                .checkCreateRoleRepeatId();
    }

    @Order(11)
    @Test
    @DisplayName("Проверка удаления роли")
    public void testDeleteRole() {
        new CreateRolePage()
                .goToRolesList()
                .goToTestRole()
                .checkDeleteRole();
    }

    @Order(12)
    @Test
    @DisplayName("Проверка того, что созданная автотестом роль не отображается в списке ролей")
    public void testAbsenceDeleteRoleInList() {
        new RolesListPage()
                .checkAbsenceDeleteRoleInList();
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
