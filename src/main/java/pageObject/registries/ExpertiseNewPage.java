package pageObject.registries;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import pageObject.MainPage;


import java.util.function.BooleanSupplier;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;

public class ExpertiseNewPage {

    MainPage mainPage = new MainPage();
    public final String linkToTheRegistry = mainPage.urlOfMainPageAutorization + "application/admin/expertise";

    private SelenideElement registryName = $(By.xpath("//*[@class=\"information-bar__item_name\"]"));

    private SelenideElement statusOfApplication = $(By.xpath("//th[@role='columnheader']//a[contains(text(),'Статус заявки')]"));

    private SelenideElement statusOfExpertise = $(By.xpath("//th[@role='columnheader']//a[contains(text(),'Статус экспертизы')]"));

    private SelenideElement nameOfExpert = $(By.xpath("//th[@role='columnheader']//a[contains(text(),'ФИО эксперта')]"));

    private SelenideElement colorOfExpert = $(By.xpath("//th[@role='columnheader']//a[contains(text(),'Цвет эксперта')]"));

    private SelenideElement checkForPlagiarism = $(By.xpath("//th[@role='columnheader']//a[contains(text(),'Проверка на плагиат')]"));

    private SelenideElement inscriptionAccessDenied = $(By.xpath("//div[contains(text(),'Доступ на запрашиваемую страницу запрещен')]"));

    public ExpertiseNewPage getRegistryName() {
        registryName.getText();

        return this;
    }

    @Step("Проверить отображение наименования реестра Экспертиза")
    public void checkNameOfRegistry(String name) {
        Assertions.assertEquals(name, registryName.getText());
    }

    private boolean isElementPresent(SelenideElement element) {

        return element.exists();
    }



    @Step("Проверить, что для аналитика и наблюдателя в реестре Экспертиза не отображается поле Статус заявки")
    public void checkClosedColumnStatusOfApplicationForWatcherAndAnalytic() {
        Assertions.assertFalse(isElementPresent(statusOfApplication));
    }

    @Step("Проверить, что для аналитика и наблюдателя в реестре Экспертиза не отображается поле Статус экспертизы")
    public void checkClosedColumnStatusOfExpertiseForWatcherAndAnalytic() {
        Assertions.assertFalse(isElementPresent(statusOfExpertise));
    }

    @Step("Проверить, что для аналитика и наблюдателя в реестре Экспертиза не отображается поле Имя эксперта")
    public void checkClosedColumnNameOfExpertForWatcherAndAnalytic() {
        Assertions.assertFalse(isElementPresent(nameOfExpert));
    }

    @Step("Проверить, что для аналитика и наблюдателя в реестре Экспертиза не отображается поле Цвет эксперта")
    public void checkClosedColumnColorOfExpertForWatcherAndAnalytic() {
        Assertions.assertFalse(isElementPresent(colorOfExpert));
    }

    @Step("Проверить, что для аналитика и наблюдателя в реестре Экспертиза не отображается поле Проверка на плагиат")
    public void checkClosedColumnCheckForPlagiarismForWatcherAndAnalytic() {
        Assertions.assertFalse(isElementPresent(checkForPlagiarism));
    }

}
