package pageObject.registries;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import pageObject.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static helpers.Helper.*;

public class ExpertiseNewPage extends BasePage {

    BasePage basePage = new BasePage("Экспертиза");

    private final String linkToTheRegistry = basePage.getUrl() + "application/admin/expertise";

    private final SelenideElement statusOfApplication = $(By
            .xpath("//th[@role='columnheader']//a[contains(text(),'Статус заявки')]"));

    private final SelenideElement statusOfExpertise = $(By
            .xpath("//th[@role='columnheader']//a[contains(text(),'Статус экспертизы')]"));

    private final SelenideElement nameOfExpert = $(By
            .xpath("//th[@role='columnheader']//a[contains(text(),'ФИО эксперта')]"));

    private final SelenideElement colorOfExpert = $(By
            .xpath("//th[@role='columnheader']//a[contains(text(),'Цвет эксперта')]"));

    private final SelenideElement checkForPlagiarism = $(By
            .xpath("//th[@role='columnheader']//a[contains(text(),'Проверка на плагиат')]"));

    public String getLinkToTheRegistry() {
        return linkToTheRegistry;
    }

    @Step("Проверить отображение наименования реестра Экспертиза")
    public void checkNameOfRegistry() {
        basePage.checkNameOfRegistry();
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
