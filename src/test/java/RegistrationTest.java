import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.LogType;
import org.junit.jupiter.api.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import pageObject.Auth.AuthPage;
import pageObject.Auth.RegistrationPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@Story("Проверка регистрации")
public class RegistrationTest {

    @BeforeAll
    public static void setUp() {
        Configuration.timeout = 20000;
        Configuration.pageLoadTimeout = 40000;
        //Configuration.headless = true;
    }

    @Order(1)
    @Test
    @DisplayName("Проверка неуспешной регистрации - уже имеющееся имя пользователя имя")
    public void testUnsuccessfulRegistrationRepeatName() {
        new AuthPage()
                .openRegistrationPage()
                .checkUnsuccessfulRegistrationRepeatName();
    }

    @Order(2)
    @Test
    @DisplayName("Проверка неуспешной регистрации - пустое имя пользователя")
    public void testUnsuccessfulRegistrationEmptyName() {
        new RegistrationPage()
                .checkUnsuccessfulRegistrationEmptyName();
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
