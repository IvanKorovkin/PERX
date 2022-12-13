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
import pageObject.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@Story("Проверка Ролевой модели Главного эксперта")
public class MainExpertTest {

    @BeforeAll
    public static void setUp() {
        Configuration.timeout = 30000;
    }

    @Order(1)
    @Test
    @DisplayName("Доступ к реестру \"Экспертиза\"")
    public void testAccessToRegistryExpertiseMainExpert() {
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
                .enterNewSystemRoleInModalWindow("Главный эксперт")
                .closeModalWindowOfChangeSystemRole()
                .clickButtonManagementFirstUser()
                .clickButtonAuthUser()
                .enterAdminPasswordIdentity()
                .confirmIdentity()
                .moveToAvatarOfMainExpert()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryExpertiseNew()
                .checkNameOfRegistry("Экспертиза");
    }

    @Order(2)
    @Test
    @DisplayName("Доступ к реестру \"Перечень заявок\"")
    public void testAccessToListOfApplicationMainExpert() {
        new MainPage()
                .moveCursorToAvatarOnMainPage()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryListOfApplication()
                .checkNameOfRegistry("Перечень заявок");
    }

    @Order(3)
    @Test
    @DisplayName("Доступ к реестру \"Эксперты\"")
    public void testAccessToExpertsMainExpert() {
        new MainPage()
                .moveCursorToAvatarOnMainPage()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryExperts()
                .checkNameOfRegistry("Эксперты");
    }

    @Order(4)
    @Test
    @DisplayName("Доступ к реестру \"Договоры и акты с экспертами (новые)\"")
    public void testAccessToAgreementExpertMainExpert() {
        new MainPage()
                .moveCursorToAvatarOnMainPage()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryAgreementExpertNew()
                .checkNameOfRegistry("Реестр договоров и актов с экспертами");
    }

    @Order(5)
    @Test
    @DisplayName("Доступ к реестру \"Рассмотрение итогов\"")
    public void testAccessToOESAdminMainExpert() {
        new MainPage()
                .moveCursorToAvatarOnMainPage()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryOESAdmin()
                .checkNameOfRegistry("Рассмотрение итогов");
    }

    @Order(6)
    @Test
    @DisplayName("Доступ к реестру \"Экспертный совет\"")
    public void testAccessToOESMainExpert() {
        new MainPage()
                .moveCursorToAvatarOnMainPage()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryOES()
                .checkNameOfRegistry("Экспертный совет");
    }

    @Order(7)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Координационный комитет\"")
    public void testAccessDeniedToCoordinatingCommitteeMainExpert() {
        new WorkingPanelPage()
                .followTheLinkCoordinatingCommittee()
                .checkInaccessibilityRegistryForThisRole();
    }

    @Order(8)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Договоры\"")
    public void testAccessDeniedToAgreementMainExpert() {
        new WorkingPanelPage()
                .followTheLinkAgreement()
                .checkInaccessibilityRegistryForThisRole();
    }

    @Order(9)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Отчетность по этапам\"")
    public void testAccessDeniedToReportMainExpert() {
        new WorkingPanelPage()
                .followTheLinkReport()
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
