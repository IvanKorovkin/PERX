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

@Story("Проверка Ролевой модели Наблюдателя")
public class WatcherTest {

    @BeforeAll
    public static void setUp() {
        Configuration.timeout = 30000;
    }

    @Order(1)
    @Test
    @DisplayName("Доступ к реестру \"Экспертиза\"")
    public void testAccessToRegistryExpertiseWatcher() {
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
                .enterNewSystemRoleInModalWindow("Наблюдатель")
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
    @DisplayName("Наблюдателю в реестре Экспертиза недоступно поле Статус заявки")
    public void testAccessDeniedStatusOfApplicationInExpertiseForWatcher() {
        new ExpertiseNewPage()
                .checkClosedColumnStatusOfApplicationForWatcherAndAnalytic();
    }

    @Order(3)
    @Test
    @DisplayName("Наблюдателю в реестре Экспертиза недоступно поле Статус экспертизы")
    public void testAccessDeniedStatusOfExpertiseInExpertiseForWatcher() {
        new ExpertiseNewPage()
                .checkClosedColumnStatusOfExpertiseForWatcherAndAnalytic();
    }

    @Order(4)
    @Test
    @DisplayName("Наблюдателю в реестре Экспертиза недоступно поле Имя эксперта")
    public void testAccessDeniedExpertsNameInExpertiseForWatcher() {
        new ExpertiseNewPage()
                .checkClosedColumnNameOfExpertForWatcherAndAnalytic();
    }

    @Order(5)
    @Test
    @DisplayName("Наблюдателю в реестре Экспертиза недоступно поле Цвет эксперта")
    public void testAccessDeniedExpertsColorInExpertiseForWatcher() {
        new ExpertiseNewPage()
                .checkClosedColumnColorOfExpertForWatcherAndAnalytic();
    }

    @Order(6)
    @Test
    @DisplayName("Наблюдателю в реестре Экспертиза недоступно поле Проверка на плагиат")
    public void testAccessDeniedCheckForPlagiarismInExpertiseForWatcher() {
        new ExpertiseNewPage()
                .checkClosedColumnCheckForPlagiarismForWatcherAndAnalytic();
    }

    @Order(7)
    @Test
    @DisplayName("Доступ к реестру \"Экспертный совет\"")
    public void testAccessToOESWatcher() {
        new MainPage()
                .moveCursorToAvatarOnMainPage()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryOES()
                .checkNameOfRegistry("Экспертный совет");
    }

    @Order(8)
    @Test
    @DisplayName("Доступ к реестру \"Перечень заявок\"")
    public void testAccessToListOfApplicationWatcher() {
        new MainPage()
                .moveCursorToAvatarOnMainPage()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryListOfApplication()
                .checkNameOfRegistry("Перечень заявок");
    }

    @Order(9)
    @Test
    @DisplayName("Доступ к реестру \"Договоры\"")
    public void testAccessToAgreementWatcher() {
        new MainPage()
                .moveCursorToAvatarOnMainPage()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryAgreement()
                .checkNameOfRegistry("Договоры");
    }

    @Order(10)
    @Test
    @DisplayName("Доступ к реестру \"Отчетность по этапам\"")
    public void testAccessToReportWatcher() {
        new MainPage()
                .moveCursorToAvatarOnMainPage()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryReport()
                .checkNameOfRegistry("Отчетность по этапам");
    }

    @Order(11)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Эксперты\"")
    public void testAccessDeniedToExpertsWatcher() {
        new WorkingPanelPage()
                .followTheLinkExperts()
                .checkInaccessibilityRegistryForThisRole();
    }

    @Order(12)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Договоры и акты с экспертами\"")
    public void testAccessDeniedToAgreementExpert() {
        new WorkingPanelPage()
                .followTheLinkAgreementExpert()
                .checkInaccessibilityRegistryForThisRole();
    }

    @Order(13)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Рассмотрение итогов\"")
    public void testAccessDeniedToOESAdmin() {
        new WorkingPanelPage()
                .followTheLinkOESAdmin()
                .checkInaccessibilityRegistryForThisRole();
    }

    @Order(14)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Координационный комитет\"")
    public void testAccessDeniedToCoordinatingCommitteeWatcher() {
        new WorkingPanelPage()
                .followTheLinkCoordinatingCommittee()
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
