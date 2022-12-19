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
import pageObject.otherPage.WorkingPanelPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@Story("Проверка ролевой модели Пользователя")
public class UserTest {

    @BeforeAll
    static void setUp() {
        Configuration.timeout = 30000;
        Configuration.headless = true;
    }

    @Order(1)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Перечень заявок\"")
    public void testAccessDeniedToListOfApplicationUser() {
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
                .clickButtonManagementFirstUser()
                .clickButtonAuthUser()
                .enterAdminPasswordIdentity()
                .confirmIdentity()
                .followTheLinkListOfApplication()
                .checkInaccessibilityRegistryForThisRole();

    }

    @Order(2)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Эксперты\"")
    public void testAccessDeniedToExpertsUser() {
        new WorkingPanelPage()
                .followTheLinkExperts()
                .checkInaccessibilityRegistryForThisRole();
    }

    @Order(3)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Экспертиза\"")
    public void testAccessDeniedToExpertiseUser() {
        new WorkingPanelPage()
                .followTheLinkExpertise()
                .checkInaccessibilityRegistryForThisRole();
    }

    @Order(4)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Договоры и акты с экспертами\"")
    public void testAccessDeniedToAgreementExpertUser() {
        new WorkingPanelPage()
                .followTheLinkAgreementExpert()
                .checkInaccessibilityRegistryForThisRole();
    }

    @Order(5)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Расмотрение итогов\"")
    public void testAccessDeniedToOESAdminUser() {
        new WorkingPanelPage()
                .followTheLinkOESAdmin()
                .checkInaccessibilityRegistryForThisRole();
    }

    @Order(6)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Экспертный совет\"")
    public void testAccessDeniedToOESUser() {
        new WorkingPanelPage()
                .followTheLinkOES()
                .checkInaccessibilityRegistryForThisRole();
    }

    @Order(7)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Координационный комитет\"")
    public void testAccessDeniedToCoordinatingCommitteeUser() {
        new WorkingPanelPage()
                .followTheLinkCoordinatingCommittee()
                .checkInaccessibilityRegistryForThisRole();
    }

    @Order(8)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Договоры\"")
    public void testAccessDeniedToAgreementUser() {
        new WorkingPanelPage()
                .followTheLinkAgreement()
                .checkInaccessibilityRegistryForThisRole();
    }

    @Order(9)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Отчетность по этапам\"")
    public void testAccessDeniedToReportUser() {
        new WorkingPanelPage()
                .followTheLinkReport()
                .checkInaccessibilityRegistryForThisRole();
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
