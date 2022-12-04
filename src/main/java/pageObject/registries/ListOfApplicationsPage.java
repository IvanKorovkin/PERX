package pageObject.registries;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import pageObject.MainPage;

import static com.codeborne.selenide.Selenide.$;

public class ListOfApplicationsPage {

    MainPage mainPage = new MainPage();
    protected final String linkToTheRegistry = mainPage.urlOfMainPageAutorization + "application/admin/validation";

    private SelenideElement registryName = $(By.xpath("//*[@class=\"information-bar__item_name\"]"));

    @Step("Проверить отображение наименования реестра Экспертиза")
    public void checkNameOfRegistry(String name) {
        Assertions.assertEquals(name, registryName.getText());
    }

}
