package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class Main_Page {

    private final String basicName = "i.korovkin";
    private final String basicPassword = "AdtbOSzpUwjo9t";
    private final String basicURL = "minfin-myrtex.ru/";
    private final String mainPageUrl = "https://" + basicName + ":" + basicPassword + "@" + basicURL;

    private final SelenideElement entranceButton = $(By.xpath("(//div[@class='header-minfin-auth'])[1]"));

    public String getMainPageUrl() {
        return mainPageUrl;
    }

    @Step("Перейти на главную страницу")
    public Main_Page openMainPage() {
        open(getMainPageUrl());
        entranceButton.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Кликнуть на кнопку \"Войти\"")
    public AuthPage clickEntranceButton() {
        entranceButton.click();

        return page(AuthPage.class);
    }


}
