import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.LogType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import pageObject.Auth.AuthPage;
import pageObject.SpaceMembers.SpaceMembersListPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@Story("Проверка добавления и приглашения участников в пространство")
public class SpaceMembersTest {

    @BeforeAll
    public static void setUp() {
        Configuration.timeout = 10000;
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
                .goToTestSpace()
                .goToSpaceMembersList()
                .checkPageMembers();
    }

    @Order(2)
    @Test
    @DisplayName("Проверка всплывающего сообщения, если не заполнено поле Пользователь")
    public void testErrorMessageUserInput() throws InterruptedException {
        new SpaceMembersListPage()
                .checkErrorMessageUserInput();
    }

    @Order(3)
    @Test
    @DisplayName("Проверка успешного добавления участника")
    public void testAddMember() {
        new SpaceMembersListPage()
                .checkAddMember();
    }

    @Order(4)
    @Test
    @DisplayName("Проверка добавления участнику роли")
    public void testAddRole() {
        new SpaceMembersListPage()
                .checkAddRole();
    }

    @Order(5)
    @Test
    @DisplayName("Проверка удаления участника из пространства")
    public void testDeleteMember() {
        new SpaceMembersListPage()
                .checkDeleteMember();
    }

    @Order(6)
    @Test
    @DisplayName("Проверка работы кнопки Пригласить")
    public void testInvitationButton() {
        new SpaceMembersListPage()
                .checkInvitationButton();
    }

    @Order(7)
    @ParameterizedTest
    @ValueSource(strings = {"admin1 @tests.rdwer", "admin1tests.rdwer", "test1@test", "1e1@.com", " @111.com",
            "1@ .com", "          ", "!@#$%^&*()@fd.co"})
    @DisplayName("Проверка валидации поля Почта")
    public void testValidationEmailInput(String email) {
        new SpaceMembersListPage()
                .checkValidationEmailInput(email);
    }

    @Order(8)
    @Test
    @DisplayName("Проверка формирования ссылки-приглашения")
    public void testCreateInvitationLink() {
        new SpaceMembersListPage()
                .checkCreateInvitationLink();
    }

    @Order(9)
    @Test
    @DisplayName("Проверка удаления ссылки-приглашения")
    public void testDeleteInvitationLink() {
        new SpaceMembersListPage()
                .checkDeleteInvitationLink();
    }

    @Order(10)
    @Test
    @DisplayName("Проверка перехода по ссылке приглашению")
    public void testFollowInvitationLink() throws InterruptedException {
        new SpaceMembersListPage()
                .checkFollowInvitationLink();
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
