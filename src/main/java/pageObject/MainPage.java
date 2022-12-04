package pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private String basicName = "i.korovkin";
    private String basicPassword = "zu_e8Oyah_p0oh";
    private String demoFPG = "new.xn--80afcdbalict6afooklqi5o.xn--p1ai/";
    public String urlOfMainPageAutorization = "https://" + basicName + ":" + basicPassword + "@" + demoFPG;

    private SelenideElement entranceButton = $(By.xpath("//*[@class=\"login-button\"]"));

    @Step("Пройти базовую авторизацию и войти на главную страницу")
    public MainPage entranceToMainPage() {
        open(urlOfMainPageAutorization);

        return this;
    }

    @Step("Кликнуть на кнопку Войти")
    public LoginPage entranceButtonClick() {
        entranceButton.click();

        return page(LoginPage.class);
    }

}
