package ExpertiseNewRegistry;

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

@Story("Проверка доступа к реестру Экспертиза под разными ролями")
public class AccessToExpertiseNewRegistryTest {

    @BeforeAll
    public static void setUp() {
        Configuration.timeout = 10000;
    }

    @BeforeEach
    public void entranceToAdminProfile() {
            new MainPage()
                .entranceToMainPage()
                .entranceButtonClick()
                .enterAdminName()
                .enterAdminPassword()
                .clickEntranceButton()
                .moveCursorToAvatar()
                .clickToWorkingPanelInUsersMenu();
}


    @Order(1)
    @Test
    @DisplayName("Доступ в реестру Экспертиза (Администратор)")
    public void testAccessToExpertiseAdmin() {

               new WorkingPanelPage()
                .entranceToRegistryExpertiseNew()
                .checkNameOfRegistry("Экспертиза");

    }

    @Order(2)
    @Test
    @DisplayName("Доступ в реестру Экспертиза (Главный эксперт)")
    public void testAccessToExpertiseMainExpert() {

        new WorkingPanelPage()
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

    @Order(3)
    @Test
    @DisplayName("Доступ в реестру Экспертиза (Наблюдатель)")
    public void testAccessToExpertiseWatcher() {

        new WorkingPanelPage()
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
                .moveToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryExpertiseNew()
                .checkNameOfRegistry("Экспертиза");

    }

    @Order(4)
    @Test
    @DisplayName("Наблюдателю в реестре Экспертиза недоступно поле Статус заявки")
    public void testClosedColumnsInExpertiseStatusApplicationForWatcher() {

        new WorkingPanelPage()
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
                .moveToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryExpertiseNew()
                .checkClosedColumnStatusOfApplicationForWatcherAndAnalytic();

    }

    @Order(5)
    @Test
    @DisplayName("Наблюдателю в реестре Экспертиза недоступно поле Статус экспертизы")
    public void testClosedColumnsInExpertiseStatusExpertiseForWatcher() {

        new WorkingPanelPage()
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
                .moveToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryExpertiseNew()
                .checkClosedColumnStatusOfExpertiseForWatcherAndAnalytic();

    }

    @Order(6)
    @Test
    @DisplayName("Наблюдателю в реестре Экспертиза недоступно поле Имя эксперта")
    public void testClosedColumnsInExpertiseNameOfExpertForWatcher() {

        new WorkingPanelPage()
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
                .moveToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryExpertiseNew()
                .checkClosedColumnNameOfExpertForWatcherAndAnalytic();

    }

    @Order(7)
    @Test
    @DisplayName("Наблюдателю в реестре Экспертиза недоступно поле Цвет эксперта")
    public void testClosedColumnsInExpertiseColorOfExpertForWatcher() {

        new WorkingPanelPage()
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
                .moveToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryExpertiseNew()
                .checkClosedColumnColorOfExpertForWatcherAndAnalytic();

    }

    @Order(8)
    @Test
    @DisplayName("Наблюдателю в реестре Экспертиза недоступно поле Проверка на плагиат")
    public void testClosedColumnsInExpertiseCheckForPlagiarismForWatcher() {

        new WorkingPanelPage()
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
                .moveToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryExpertiseNew()
                .checkClosedColumnCheckForPlagiarismForWatcherAndAnalytic();

    }

    @Order(9)
    @Test
    @DisplayName("Доступ к реестру Экспертиза (Аналитик)")
    public void testAccessToExpertiseAnalytic() {

        new WorkingPanelPage()
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

    @Order(10)
    @Test
    @DisplayName("Аналитику в реестре Экспертиза недоступно поле Статус заявки")
    public void testClosedColumnsInExpertiseStatusApplicationForAnalytic() {

        new WorkingPanelPage()
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
                .checkClosedColumnStatusOfApplicationForWatcherAndAnalytic();

    }

    @Order(11)
    @Test
    @DisplayName("Аналитику в реестре Экспертиза недоступно поле Статус экспертизы")
    public void testClosedColumnsInExpertiseStatusExpertiseForAnalytic() {

        new WorkingPanelPage()
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
                .checkClosedColumnStatusOfExpertiseForWatcherAndAnalytic();

    }

    @Order(12)
    @Test
    @DisplayName("Аналитику в реестре Экспертиза недоступно поле Имя эксперта")
    public void testClosedColumnsInExpertiseNameOfExpertForAnalytic() {

        new WorkingPanelPage()
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
                .checkClosedColumnNameOfExpertForWatcherAndAnalytic();

    }

    @Order(13)
    @Test
    @DisplayName("Аналитику в реестре Экспертиза недоступно поле Цвет эксперта")
    public void testClosedColumnsInExpertiseColorOfExpertForAnalytic() {

        new WorkingPanelPage()
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
                .checkClosedColumnColorOfExpertForWatcherAndAnalytic();

    }

    @Order(14)
    @Test
    @DisplayName("Аналитику в реестре Экспертиза недоступно поле Проверка на плагиат")
    public void testClosedColumnsInExpertiseCheckForPlagiarismForAnalytic() {

        new WorkingPanelPage()
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
                .checkClosedColumnCheckForPlagiarismForWatcherAndAnalytic();

    }

    @Order(15)
    @Test
    @DisplayName("Валидатор не имеет доступа к реестру Экспертиза")
    public void testAccessDeniedToExpertiseRegistryForValidator() {

        new WorkingPanelPage()
                .entranceToRegistryIdentity()
                .openSystemRolesFilter()
                .selectInSystemRolesFilterUsers()
                .editSystemRoleOfFirstUserInList()
                .enterNewSystemRoleInModalWindow("Валидатор")
                .closeModalWindowOfChangeSystemRole()
                .clickButtonManagementFirstUser()
                .clickButtonAuthUser()
                .enterAdminPasswordIdentity()
                .confirmIdentity()
                .checkTransitionToExpertiseRegistryByLink()
                .checkInaccessibilityForExpertiseRegistryForOtherRole();

    }

    @Order(16)
    @Test
    @DisplayName("Куратор не имеет доступа к реестру Экспертиза")
    public void testAccessDeniedToExpertiseRegistryForCurator() {

        new WorkingPanelPage()
                .entranceToRegistryIdentity()
                .openSystemRolesFilter()
                .selectInSystemRolesFilterUsers()
                .editSystemRoleOfFirstUserInList()
                .enterNewSystemRoleInModalWindow("Куратор")
                .closeModalWindowOfChangeSystemRole()
                .clickButtonManagementFirstUser()
                .clickButtonAuthUser()
                .enterAdminPasswordIdentity()
                .confirmIdentity()
                .checkTransitionToExpertiseRegistryByLink()
                .checkInaccessibilityForExpertiseRegistryForOtherRole();

    }

    @Order(17)
    @Test
    @DisplayName("Член ОЭС не имеет доступа к реестру Экспертиза")
    public void testAccessDeniedToExpertiseRegistryForMemberOfOES() {

        new WorkingPanelPage()
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
                .checkTransitionToExpertiseRegistryByLink()
                .checkInaccessibilityForExpertiseRegistryForOtherRole();

    }

    @Order(18)
    @Test
    @DisplayName("Контент-менеджер не имеет доступа к реестру Экспертиза")
    public void testAccessDeniedToExpertiseRegistryForContentManager() {

        new WorkingPanelPage()
                .entranceToRegistryIdentity()
                .openSystemRolesFilter()
                .selectInSystemRolesFilterUsers()
                .editSystemRoleOfFirstUserInList()
                .enterNewSystemRoleInModalWindow("Контент Менеджер")
                .closeModalWindowOfChangeSystemRole()
                .clickButtonManagementFirstUser()
                .clickButtonAuthUser()
                .enterAdminPasswordIdentity()
                .confirmIdentity()
                .checkTransitionToExpertiseRegistryByLink()
                .checkInaccessibilityForExpertiseRegistryForOtherRole();

    }

    @Order(19)
    @Test
    @DisplayName("Фин.Сотрудник не имеет доступа к реестру Экспертиза")
    public void testAccessDeniedToExpertiseRegistryForFinanceEmployee() {

        new WorkingPanelPage()
                .entranceToRegistryIdentity()
                .openSystemRolesFilter()
                .selectInSystemRolesFilterUsers()
                .editSystemRoleOfFirstUserInList()
                .enterNewSystemRoleInModalWindow("Фин. Сотрудник")
                .closeModalWindowOfChangeSystemRole()
                .clickButtonManagementFirstUser()
                .clickButtonAuthUser()
                .enterAdminPasswordIdentity()
                .confirmIdentity()
                .checkTransitionToExpertiseRegistryByLink()
                .checkInaccessibilityForExpertiseRegistryForOtherRole();

    }

    @Order(20)
    @Test
    @DisplayName("Член КК не имеет доступа к реестру Экспертиза")
    public void testAccessDeniedToExpertiseRegistryForMemberOfCC() {

        new WorkingPanelPage()
                .entranceToRegistryIdentity()
                .openSystemRolesFilter()
                .selectInSystemRolesFilterUsers()
                .editSystemRoleOfFirstUserInList()
                .enterNewSystemRoleInModalWindow("Член Координационного комитета")
                .closeModalWindowOfChangeSystemRole()
                .clickButtonManagementFirstUser()
                .clickButtonAuthUser()
                .enterAdminPasswordIdentity()
                .confirmIdentity()
                .checkTransitionToExpertiseRegistryByLink()
                .checkInaccessibilityForExpertiseRegistryForOtherRole();

    }

    @Order(21)
    @Test
    @DisplayName("Бухгалтер не имеет доступа к реестру Экспертиза")
    public void testAccessDeniedToExpertiseRegistryForBooker() {

        new WorkingPanelPage()
                .entranceToRegistryIdentity()
                .openSystemRolesFilter()
                .selectInSystemRolesFilterUsers()
                .editSystemRoleOfFirstUserInList()
                .enterNewSystemRoleInModalWindow("Бухгалтер")
                .closeModalWindowOfChangeSystemRole()
                .clickButtonManagementFirstUser()
                .clickButtonAuthUser()
                .enterAdminPasswordIdentity()
                .confirmIdentity()
                .checkTransitionToExpertiseRegistryByLink()
                .checkInaccessibilityForExpertiseRegistryForOtherRole();

    }


    @AfterEach
    void quitBrowser() {
        LogEntries browserLogs = WebDriverRunner.getWebDriver().manage().logs().get(String.valueOf(LogType.BROWSER));

        for (LogEntry log: browserLogs) {
            Allure.addAttachment("Сообщения в консоли браузера", log.getMessage());
        }
        Selenide.closeWebDriver();
    }


}
