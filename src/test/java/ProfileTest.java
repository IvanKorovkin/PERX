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
import pageObject.ProfilePage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@Story("Проверка страницы профиля пользователя")
public class ProfileTest {

    @BeforeAll
    public static void setUp() {
        Configuration.timeout = 20000;
        Configuration.pageLoadTimeout = 40000;
        //Configuration.headless = true;
    }

    @Order(1)
    @Test
    @DisplayName("Проверка отображения всех элементов на странице профиля пользователя")
    public void testProfilePage() {
        new AuthPage()
                .openAuthPage()
                .loginUser()
                .goProfileSettings()
                .checkProfilePage();
    }

    @Order(2)
    @Test
    @DisplayName("Проверка, что кнопка ОК неактивна, если на странице не было изменений")
    public void testDisabledSubmitButton() {
        new ProfilePage()
                .checkDisabledSubmitButton();
    }

    @Order(3)
    @Test
    @DisplayName("Проверка появления окна с предложением сохранить данные в случае ухода со страницы")
    public void testSaveWindow() {
        new ProfilePage()
                .checkSaveWindow();
    }

    @Order(4)
    @Test
    @DisplayName("Проверка успешного изменения имени")
    public void testChangeName() {
        new ProfilePage()
                .checkChangeName();
    }

    @Order(5)
    @ParameterizedTest
    @ValueSource(strings = {"admin1 @tests.rdwer", "admin1tests.rdwer", "test1@test", "1e1@.com", " @111.com",
    "1@ .com", "          ", "!@#$%^&*()@fd.co"})
    @DisplayName("Проверка валидации поля Почта")
    public void testValidationInputEmail(String email) {
        new ProfilePage()
                .checkValidationInputEmail(email);
    }

    @Order(6)
    @Test
    @DisplayName("Проверка переключения меню на английский язык и обратно на русский")
    public void testChangeLanguage() {
        new ProfilePage()
                .checkChangeLanguage();
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
