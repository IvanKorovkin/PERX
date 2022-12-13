package pageObject.registries;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import pageObject.MainPage;

import static com.codeborne.selenide.Selenide.$;

public class AgreementPage {

    MainPage mainPage = new MainPage();
    public final String linkToTheRegistry = mainPage.urlOfMainPageAutorization + "agreement/admin/documents";



    private SelenideElement registryName = $(By.xpath("//div[@class='information-bar__item_name']"));

    @Step("Проверить отображение наименования реестра \"Договоры\"")
    public void checkNameOfRegistry(String name) {
        Assertions.assertEquals(name, registryName.getText());
    }

}
