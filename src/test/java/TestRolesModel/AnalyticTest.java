package TestRolesModel;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.LogType;
import org.junit.jupiter.api.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import pageObject.MainPage;
import pageObject.WorkingPanelPage;
import pageObject.registries.ExpertiseNewPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@Story("Проверка ролевой модели Аналитика")
public class AnalyticTest {

    @BeforeAll
    public static void setUp() {
        Configuration.timeout = 30000;
    }

    @Order(1)
    @Test
    @DisplayName("Доступ к реестру \"Экспертиза\"")
    public void testAccessToRegistryExpertiseAnalytic() {
        new MainPage()
                .entranceToMainPage()
                .entranceButtonClick()
                .enterAdminName()
                .enterAdminPassword()
                .clickEntranceButton()
                .moveCursorToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryIdentity()
                .openSystemRolesFilter()
                .selectInSystemRolesFilterUsers()
                .editSystemRoleOfFirstUserInList()
                .enterNewSystemRoleInModalWindow("Аналитик")
                .closeModalWindowOfChangeSystemRole()
                .clickButtonManagementFirstUser()
                .clickButtonAuthUser()
                .enterAdminPasswordIdentity()
                .confirmIdentity()
                .moveToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryExpertiseNew()
                .checkNameOfRegistry("Экспертиза");
    }

    @Order(2)
    @Test
    @DisplayName("Аналитику в реестре Экспертиза недоступно поле Статус заявки")
    public void testAccessDeniedStatusOfApplicationInExpertiseForAnalytic() {
        new ExpertiseNewPage()
                .checkClosedColumnStatusOfApplicationForWatcherAndAnalytic();
    }

    @Order(3)
    @Test
    @DisplayName("Аналитику в реестре Экспертиза недоступно поле Статус экспертизы")
    public void testAccessDeniedStatusOfExpertiseInExpertiseForAnalytic() {
        new ExpertiseNewPage()
                .checkClosedColumnStatusOfExpertiseForWatcherAndAnalytic();
    }

    @Order(4)
    @Test
    @DisplayName("Аналитику в реестре Экспертиза недоступно поле Имя эксперта")
    public void testAccessDeniedExpertsNameInExpertiseForAnalytic() {
        new ExpertiseNewPage()
                .checkClosedColumnNameOfExpertForWatcherAndAnalytic();
    }

    @Order(5)
    @Test
    @DisplayName("Аналитику в реестре Экспертиза недоступно поле Цвет эксперта")
    public void testAccessDeniedExpertsColorInExpertiseForAnalytic() {
        new ExpertiseNewPage()
                .checkClosedColumnColorOfExpertForWatcherAndAnalytic();
    }

    @Order(6)
    @Test
    @DisplayName("Аналитику в реестре Экспертиза недоступно поле Проверка на плагиат")
    public void testAccessDeniedCheckForPlagiarismInExpertiseForAnalytic() {
        new ExpertiseNewPage()
                .checkClosedColumnCheckForPlagiarismForWatcherAndAnalytic();
    }

    @Order(7)
    @Test
    @DisplayName("Доступ к реестру \"Перечень заявок\"")
    public void testAccessToListOfApplicationAnalytic() {
        new MainPage()
                .moveCursorToAvatarOnMainPage()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryListOfApplication()
                .checkNameOfRegistry("Перечень заявок");
    }

    @Order(8)
    @Test
    @DisplayName("Доступ к реестру \"Координационный комитет\"")
    public void testAccessToCoordinatingCommitteeAnalytic() {
        new MainPage()
                .moveCursorToAvatarOnMainPage()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryCoordinatingCommittee()
                .checkNameOfRegistry("Координационный комитет");
    }

    @Order(9)
    @Test
    @DisplayName("Доступ к реестру \"Договор\"")
    public void testAccessToAgreementAnalytic() {
        new MainPage()
                .moveCursorToAvatarOnMainPage()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryAgreement()
                .checkNameOfRegistry("Договоры");
    }

    @Order(10)
    @Test
    @DisplayName("Доступ к реестру \"Отчетность по этапаам\"")
    public void testAccessToReportAnalytic() {
        new MainPage()
                .moveCursorToAvatarOnMainPage()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryReport()
                .checkNameOfRegistry("Отчетность по этапам");
    }

    @Order(11)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Эксперты\"")
    public void testAccessDeniedToExpertsAnalytic() {
        new WorkingPanelPage()
                .followTheLinkExperts()
                .checkInaccessibilityRegistryForThisRole();
    }

    @Order(12)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Договоры и акты с экспертами\"")
    public void testAccessDeniedToAgreementExpertAnalytic() {
        new WorkingPanelPage()
                .followTheLinkAgreementExpert()
                .checkInaccessibilityRegistryForThisRole();
    }

    @Order(13)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Рассмотрение итогов\"")
    public void testAccessDeniedToOESAdminAnalytic() {
        new WorkingPanelPage()
                .followTheLinkOESAdmin()
                .checkInaccessibilityRegistryForThisRole();
    }

    @Order(14)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Экспертный совет\"")
    public void testAccessDeniedToOESAnalytic() {
        new WorkingPanelPage()
                .followTheLinkOES()
                .checkInaccessibilityRegistryForThisRole();
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
