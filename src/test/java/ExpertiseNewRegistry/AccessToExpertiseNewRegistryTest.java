package ExpertiseNewRegistry;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.*;

import java.time.Duration;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@Story("Проверка доступа к реестру Экспертиза под разными ролями")
public class AccessToExpertiseNewRegistryTest {

    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
    ChromeOptions chromeOptions;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupBrowser() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        chromeOptions = new ChromeOptions();

        //chromeOptions.addArguments("--headless");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Order(1)
    @Test
    @DisplayName("Доступ в реестру Экспертиза (Администратор)")
    public void testAccessToExpertiseAdmin() {

        new MainPage(driver)
                .entranceToMainPage()
                .entranceButtonClick()
                .enterAdminName()
                .enterAdminPassword()
                .clickEntranceButton()
                .moveCursorToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryExpertiseNew()
                .checkNameOfRegistry("Экспертиза");

    }

    @Order(2)
    @Test
    @DisplayName("Доступ в реестру Экспертиза (Главный эксперт)")
    public void testAccessToExpertiseMainExpert() {

        new MainPage(driver)
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

    @Order(3)
    @Test
    @DisplayName("Доступ в реестру Экспертиза (Наблюдатель)")
    public void testAccessToExpertiseWatcher() {

        new MainPage(driver)
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
                .moveToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryExpertiseNew()
                .checkNameOfRegistry("Экспертиза");

    }

    @Order(4)
    @Test
    @DisplayName("Наблюдателю в реестре Экспертиза недоступно поле Статус заявки")
    public void testClosedColumnsInExpertiseStatusApplicationForWatcher() {

        new MainPage(driver)
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
                .moveToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryExpertiseNew()
                .checkClosedColumnStatusOfApplicationForWatcherAndAnalytic();

    }

    @Order(5)
    @Test
    @DisplayName("Наблюдателю в реестре Экспертиза недоступно поле Статус экспертизы")
    public void testClosedColumnsInExpertiseStatusExpertiseForWatcher() {

        new MainPage(driver)
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
                .moveToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryExpertiseNew()
                .checkClosedColumnStatusOfExpertiseForWatcherAndAnalytic();

    }

    @Order(6)
    @Test
    @DisplayName("Наблюдателю в реестре Экспертиза недоступно поле Имя эксперта")
    public void testClosedColumnsInExpertiseNameOfExpertForWatcher() {

        new MainPage(driver)
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
                .moveToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryExpertiseNew()
                .checkClosedColumnNameOfExpertForWatcherAndAnalytic();

    }

    @Order(7)
    @Test
    @DisplayName("Наблюдателю в реестре Экспертиза недоступно поле Цвет эксперта")
    public void testClosedColumnsInExpertiseColorOfExpertForWatcher() {

        new MainPage(driver)
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
                .moveToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryExpertiseNew()
                .checkClosedColumnColorOfExpertForWatcherAndAnalytic();

    }

    @Order(8)
    @Test
    @DisplayName("Наблюдателю в реестре Экспертиза недоступно поле Проверка на плагиат")
    public void testClosedColumnsInExpertiseCheckForPlagiarismForWatcher() {

        new MainPage(driver)
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
                .moveToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryExpertiseNew()
                .checkClosedColumnCheckForPlagiarismForWatcherAndAnalytic();

    }

    @Order(9)
    @Test
    @DisplayName("Доступ к реестру Экспертиза (Аналитик)")
    public void testAccessToExpertiseAnalytic() {

        new MainPage(driver)
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

    @Order(10)
    @Test
    @DisplayName("Аналитику в реестре Экспертиза недоступно поле Статус заявки")
    public void testClosedColumnsInExpertiseStatusApplicationForAnalytic() {

        new MainPage(driver)
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
                .checkClosedColumnStatusOfApplicationForWatcherAndAnalytic();

    }

    @Order(11)
    @Test
    @DisplayName("Аналитику в реестре Экспертиза недоступно поле Статус экспертизы")
    public void testClosedColumnsInExpertiseStatusExpertiseForAnalytic() {

        new MainPage(driver)
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
                .checkClosedColumnStatusOfExpertiseForWatcherAndAnalytic();

    }

    @Order(12)
    @Test
    @DisplayName("Аналитику в реестре Экспертиза недоступно поле Имя эксперта")
    public void testClosedColumnsInExpertiseNameOfExpertForAnalytic() {

        new MainPage(driver)
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
                .checkClosedColumnNameOfExpertForWatcherAndAnalytic();

    }

    @Order(13)
    @Test
    @DisplayName("Аналитику в реестре Экспертиза недоступно поле Цвет эксперта")
    public void testClosedColumnsInExpertiseColorOfExpertForAnalytic() {

        new MainPage(driver)
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
                .checkClosedColumnColorOfExpertForWatcherAndAnalytic();

    }

    @Order(14)
    @Test
    @DisplayName("Аналитику в реестре Экспертиза недоступно поле Проверка на плагиат")
    public void testClosedColumnsInExpertiseCheckForPlagiarismForAnalytic() {

        new MainPage(driver)
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
                .checkClosedColumnCheckForPlagiarismForWatcherAndAnalytic();

    }

    @Order(15)
    @Test
    @DisplayName("Валидатор не имеет доступа к реестру Экспертиза")
    public void testAccessDeniedToExpertiseRegistryForValidator() {

        new MainPage(driver)
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

        new MainPage(driver)
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

        new MainPage(driver)
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
                .checkTransitionToExpertiseRegistryByLink()
                .checkInaccessibilityForExpertiseRegistryForOtherRole();

    }

    @Order(18)
    @Test
    @DisplayName("Контент-менеджер не имеет доступа к реестру Экспертиза")
    public void testAccessDeniedToExpertiseRegistryForContentManager() {

        new MainPage(driver)
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

        new MainPage(driver)
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

        new MainPage(driver)
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

        new MainPage(driver)
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
        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);

        for (LogEntry log: browserLogs) {
            Allure.addAttachment("Сообщения в консоли браузера", log.getMessage());
        }
        driver.quit();
    }


}
