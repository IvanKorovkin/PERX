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
import pageObject.MainPage;
import pageObject.WorkingPanelPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@Story("Проверка ролевой модели члена ОЭС")
public class MemberOfOESTest {

    @BeforeAll
    public static void setUp() {
        Configuration.timeout = 30000;
    }

    @Order(1)
    @Test
    @DisplayName("Доступ к реестру \"Перечень заявок\"")
    public void testAccessToListOfApplicationMemberOfOES() {
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
                .enterNewSystemRoleInModalWindow("Член ОЭС")
                .closeModalWindowOfChangeSystemRole()
                .clickButtonManagementFirstUser()
                .clickButtonAuthUser()
                .enterAdminPasswordIdentity()
                .confirmIdentity()
                .moveToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryListOfApplication()
                .checkNameOfRegistry("Перечень заявок");
    }

    @Order(2)
    @Test
    @DisplayName("Доступ к реестру \"Экспертный совет\"")
    public void testAccessToOESMemberOfOES() {
        new MainPage()
                .moveCursorToAvatarOnMainPage()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryOES()
                .checkNameOfRegistry("Экспертный совет");
    }

    @Order(3)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Эксперты\"")
    public void testAccessDeniedToExpertsMembersOfOES() {
        new WorkingPanelPage()
                .followTheLinkExperts()
                .checkInaccessibilityRegistryForThisRole();
    }

    @Order(4)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Экспертиза\"")
    public void testAccessDeniedToExpertiseMemberOfOES() {
        new WorkingPanelPage()
                .followTheLinkExpertise()
                .checkInaccessibilityRegistryForThisRole();
    }

    @Order(5)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Договоры и акты с экспертами\"")
    public void testAccessDeniedToAgreementExpertMemberOfOES() {
        new WorkingPanelPage()
                .followTheLinkAgreementExpert()
                .checkInaccessibilityRegistryForThisRole();
    }

    @Order(6)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Расмотрение итогов\"")
    public void testAccessDeniedToOESAdminMemberOfOES() {
        new WorkingPanelPage()
                .followTheLinkOESAdmin()
                .checkInaccessibilityRegistryForThisRole();
    }

    @Order(7)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Координационный комитет\"")
    public void testAccessDeniedToCoordinatingCommitteeMemberOfOES() {
        new WorkingPanelPage()
                .followTheLinkCoordinatingCommittee()
                .checkInaccessibilityRegistryForThisRole();
    }

    @Order(8)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Договоры\"")
    public void testAccessDeniedToAgreementMemberOfOES() {
        new WorkingPanelPage()
                .followTheLinkAgreement()
                .checkInaccessibilityRegistryForThisRole();
    }

    @Order(9)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Отчетность по этапам\"")
    public void testAccessDeniedToReportMemberOfOES() {
        new WorkingPanelPage()
                .followTheLinkReport()
                .checkInaccessibilityRegistryForThisRole();
    }

    @AfterEach
    public void getLogs() {
        LogEntries browserLogs = WebDriverRunner.getWebDriver().manage().logs().get(LogType.BROWSER);

        for (LogEntry log: browserLogs) {
            Allure.addAttachment("Сообщения в консоли браузера", log.getMessage());
        }
    }

    @AfterAll
    public static void quitBrowser() {
        Selenide.closeWebDriver();
    }

}
