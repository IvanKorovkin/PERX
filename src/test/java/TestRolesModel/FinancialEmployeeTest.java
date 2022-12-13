package TestRolesModel;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import pageObject.MainPage;
import pageObject.WorkingPanelPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class FinancialEmployeeTest {

    @BeforeAll
    static void setUp() {
        Configuration.timeout = 30000;
    }

    @Order(1)
    @Test
    @DisplayName("Доступ к реестру \"Договоры\"")
    public void testAccessToAgreementFinancialEmployee() {
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
                .enterNewSystemRoleInModalWindow("Фин.Сотрудник")
                .closeModalWindowOfChangeSystemRole()
                .clickButtonManagementFirstUser()
                .clickButtonAuthUser()
                .enterAdminPasswordIdentity()
                .confirmIdentity()
                .moveToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryAgreement()
                .checkNameOfRegistry("Договоры");
    }

    @Order(2)
    @Test
    @DisplayName("Доступ к реестру \"Отчетность по этапам\"")
    public void testAccessToReportFinancialEmployee() {
        new MainPage()
                .moveCursorToAvatarOnMainPage()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryReport()
                .checkNameOfRegistry("Отчетность по этапам");
    }

    @Order(3)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Перечень заявок\"")
    public void testAccessDeniedToListOfApplicationFinancialEmployee() {
        new WorkingPanelPage()
                .followTheLinkListOfApplication()
                .checkInaccessibilityRegistryForThisRole();
    }

    @Order(4)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Эксперты\"")
    public void testAccessDeniedToExpertsFinancialEmployee() {
        new WorkingPanelPage()
                .followTheLinkExperts()
                .checkInaccessibilityRegistryForThisRole();
    }
}
