import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.LogType;
import org.junit.jupiter.api.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import pageObject.AuthPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@Story("Проверка авторизации")
public class AuthorizationTest {

    @BeforeAll
    public static void setUp() {
        Configuration.timeout = 30000;
        Configuration.pageLoadTimeout = 50000;
        //Configuration.headless = true;
    }

    @Order(1)
    @Test
    @DisplayName("Проверка неуспешной авторизации - неверный логин")
    public void testUnsuccessfulAuthorizationInvalidLogin() {
        new AuthPage()
                .openAuthPage()
                .enterInvalidAdminName()
                .enterAdminPassword()
                .clickEntranceButton()
                .checkUnsuccessfulAuthorization();
    }

    @Order(2)
    @Test
    @DisplayName("Проверка неуспешной авторизации - неверный пароль")
    public void testUnsuccessfulAuthorizationInvalidPassword() {
        new AuthPage()
                .openAuthPage()
                .enterAdminName()
                .enterInvalidAdminPassword()
                .clickEntranceButton()
                .checkUnsuccessfulAuthorization();
    }

    @Order(3)
    @Test
    @DisplayName("Проверка неуспешной авторизации - неверный логин и пароль")
    public void testUnsuccessfulAuthorizationInvalidLoginAndPassword() {
        new AuthPage()
                .openAuthPage()
                .enterInvalidAdminName()
                .enterInvalidAdminPassword()
                .clickEntranceButton()
                .checkUnsuccessfulAuthorization();
    }

    @Order(4)
    @Test
    @DisplayName("Проверка неуспешной авторизации - пустой логин")
    public void testUnsuccessfulAuthorizationEmptyLogin() {
        new AuthPage()
                .openAuthPage()
                .enterAdminPassword()
                .clickEntranceButton()
                .checkUnsuccessfulAuthorization();
    }

    @Order(5)
    @Test
    @DisplayName("Проверка неуспешной авторизации - пустой пароль")
    public void testUnsuccessfulAuthorizationEmptyPassword() {
        new AuthPage()
                .openAuthPage()
                .enterAdminName()
                .clickEntranceButton()
                .checkUnsuccessfulAuthorization();
    }

    @Order(6)
    @Test
    @DisplayName("Проверка неуспешной авторизации - пустой логин и пароль")
    public void testUnsuccessfulAuthorizationEmptyLoginAndPassword() {
        new AuthPage()
                .openAuthPage()
                .clickEntranceButton()
                .checkUnsuccessfulAuthorization();
    }

    @Order(7)
    @Test
    @DisplayName("Проверка успешной авторизации под админом")
    public void testSuccessfulAuthorizationAdmin() {
        new AuthPage()
                .openAuthPage()
                .loginAdmin();
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
