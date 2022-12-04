package pageObject.registries;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import pageObject.MainPage;

import static com.codeborne.selenide.Selenide.$;

public class OESPage {

    MainPage mainPage = new MainPage();
    protected final String linkToTheRegistry = mainPage.urlOfMainPageAutorization + "application/admin/oes";

    private SelenideElement registryName = $(By.xpath("//div[@id='information-bar__item_toggler']"));

    @Step("Проверить отображение наименования реестра \"Экспертный совет\"")
    public void checkNameOfRegistry(String name) {
        Assertions.assertEquals(name, registryName.getText());
    }

}
