package TestRolesModel;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import pageObject.otherPage.MainPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@Story("Проверка Ролевой модели Главного эксперта")
public class AdminTest {

    @BeforeAll
    static void setUp() {
        Configuration.timeout = 30000;
        Configuration.headless = true;
    }

    @Order(1)
    @Test
    @DisplayName("Доступ к реестру \"Перечень заявок\"")
    public void testAccessToListOfApplicationAdmin() {
        new MainPage()
                .entranceToMainPage()
                .entranceButtonClick()
                .enterAdminName()
                .enterAdminPassword()
                .clickEntranceButton()
                .moveCursorToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryListOfApplication()
                .checkNameOfRegistry();
    }

    @Order(2)
    @Test
    @DisplayName("Доступ к реестру \"Эксперты\"")
    public void testAccessToExpertsAdmin() {
        new MainPage()
                .moveCursorToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryExperts()
                .checkNameOfRegistry();
    }

    @Order(3)
    @Test
    @DisplayName("Доступ к реестру \"Экспертиза\"")
    public void testAccessToExpertiseAdmin() {
        new MainPage()
                .moveCursorToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryExpertiseNew()
                .checkNameOfRegistry();
    }

    @Order(4)
    @Test
    @DisplayName("Доступ к реестру \"Договоры и акты с экспертами (новые)\"")
    public void testAccessToAgreementExpert() {
        new MainPage()
                .moveCursorToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryAgreementExpertNew()
                .checkNameOfRegistry();
    }

    @Order(5)
    @Test
    @DisplayName("Доступ к реестру \"Рассмотрение итогов\"")
    public void testAccessToOESAdminAdmin() {
        new MainPage()
                .moveCursorToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryOESAdmin()
                .checkNameOfRegistry();
    }

    @Order(6)
    @Test
    @DisplayName("Доступ к реестру \"Экспертный совет\"")
    public void testAccessToOESAdmin() {
        new MainPage()
                .moveCursorToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryOES()
                .checkNameOfRegistry();
    }

    @Order(7)
    @Test
    @DisplayName("Доступ к реестру \"Координационный комитет\"")
    public void testAccessToCoordinatingCommitteeAdmin() {
        new MainPage()
                .moveCursorToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryCoordinatingCommittee()
                .checkNameOfRegistry();
    }

    @Order(8)
    @Test
    @DisplayName("Доступ к реестру \"Договоры\"")
    public void testAccessToAgreementAdmin() {
        new MainPage()
                .moveCursorToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryAgreement()
                .checkNameOfRegistry();
    }

    @Order(9)
    @Test
    @DisplayName("Доступ к реестру \"Отчетность по этапам\"")
    public void testAccessToReportAdmin() {
        new MainPage()
                .moveCursorToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryReport()
                .checkNameOfRegistry();
    }

    @AfterEach
    void getLogs() {
        LogEntries browserLogs = WebDriverRunner.getWebDriver().manage().logs().get(LogType.BROWSER);

        for (LogEntry log : browserLogs) {
            Allure.addAttachment("Сообщения в консоли браузера", log.getMessage());
        }
    }

    @AfterAll
    static void quitBrowser() {
        Selenide.closeWebDriver();
    }
}
