package Auth;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.LogType;
import org.junit.jupiter.api.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import pageObject.Main_Page;

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
    @DisplayName("Проверка успешной авторизации под админом")
    public void testSuccessfulAuthorizationAdmin() {
        new Main_Page()
                .openMainPage()
                .clickEntranceButton()
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
