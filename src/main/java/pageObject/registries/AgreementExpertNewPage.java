package pageObject.registries;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import pageObject.MainPage;

import static com.codeborne.selenide.Selenide.$;

public class AgreementExpertNewPage {

    MainPage mainPage = new MainPage();
    protected final String linkToTheRegistry = mainPage.urlOfMainPageAutorization + "agreement-expert/admin/documents";

    private SelenideElement registryName = $(By.xpath("//*[@class=\"information-bar__item_name\"]"));

    @Step("Проверить отображение наименования реестра \"Договоры и Акты с экспертами (новые)\"")
    public void checkNameOfRegistry(String name) {
        Assertions.assertEquals(name, registryName.getText());
    }

}
