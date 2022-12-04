package pageObject.registries;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import pageObject.MainPage;

import static com.codeborne.selenide.Selenide.$;

public class CoordinatingCommitteePage {

    MainPage mainPage = new MainPage();
    public final String linkToTheRegistry = mainPage.urlOfMainPageAutorization + "application/admin/coordinating-committee";

    private SelenideElement registryName = $(By.xpath("//div[@class='information-bar__item_name']"));

    private SelenideElement inscriptionAccessDenied
            = $(By.xpath("//div[contains(text(),'Доступ на запрашиваемую страницу запрещен')]"));

    @Step("Проверить отображение наименования реестра \"Координационный комитет\"")
    public void checkNameOfRegistry(String name) {
        Assertions.assertEquals(name, registryName.getText());
    }

    // метод для проверки, есть ли элемент на странице
    private boolean isElementPresent(String xpath) {
        try {
            $(By.xpath(xpath));

            return true;
        } catch (NoSuchElementException e) {

            return false;
        }
    }

}
