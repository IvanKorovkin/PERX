package pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class WorkingPanelPage {

    private SelenideElement registryExpertiseNew = $(By.xpath("//*[contains(text(), 'Экспертиза (новая)')]"));

    private SelenideElement registryIdentity = $(By.xpath("//*[contains(text(), 'Управление ролями и пользователям')]"));

    @Step("Кликнуть в рабочей панели на реестр Экспертиза(новая)")
    public ExpertiseNewPage entranceToRegistryExpertiseNew() {
        registryExpertiseNew.click();

        return page(ExpertiseNewPage.class);
    }

    @Step("Кликнуть в рабочей панели на реестр Управление ролями и пользователями")
    public IdentityPage entranceToRegistryIdentity() {
        registryIdentity.click();

        return page(IdentityPage.class);
    }
}
