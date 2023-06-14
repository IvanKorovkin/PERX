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
import pageObject.Spaces.CreateSpacePage;
import pageObject.Spaces.SpacesListPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@Story("Проверка создания и удаления пространства")
public class CreateSpaceTest {

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
                .goToTestOrg()
                .checkPageElements();
    }

    @Order(2)
    @Test
    @DisplayName("Проверка, что кнопка ОК неактивна, если не заполнено название пространства")
    public void testDisabledButtonOK() {
        new SpacesListPage()
                .clickCreateSpaceButton()
                .checkDisabledButtonOK();
    }

    @Order(3)
    @Test
    @DisplayName("Проверка всплывающего сообщения, если не заполнено название пространства")
    public void testSpaceNameMessage() {
        new CreateSpacePage()
                .checkSpaceNameMessage();
    }

    @Order(4)
    @Test
    @DisplayName("Проверка появления окна с предложением сохранить данные в случае ухода со страницы")
    public void testSaveWindow() {
        new CreateSpacePage()
                .checkSaveWindow();
    }

    @Order(5)
    @Test
    @DisplayName("Проверка успешного создания пространства")
    public void testSpaceCreate() {
        new CreateSpacePage()
                .checkSpaceCreate();
    }

    @Order(6)
    @Test
    @DisplayName("Проверка отображения в списке пространств созданного автотестом пространства")
    public void testDisplaySpaceInList() {
        new SpacesListPage()
                .checkDisplaySpaceInList();
    }

    @Order(7)
    @Test
    @DisplayName("Проверка неуспешного создания пространства с уже существующим названием")
    public void testSpaceCreateRepeatName() {
        new SpacesListPage()
                .clickCreateSpaceButton()
                .checkSpaceCreateRepeatName();
    }

    @Order(8)
    @Test
    @DisplayName("Проверка удаления пространства")
    public void testDeleteSpace() {
        new SpacesListPage()
                .goToFirstSpaceSettings()
                .deleteSpace();
    }

    @Order(9)
    @Test
    @DisplayName("Проверка того, что удаленное пространство не отображается в списке")
    public void testAbsenceDeleteSpaceInList() {
        new SpacesListPage()
                .checkAbsenceDeleteSpaceInList();
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
