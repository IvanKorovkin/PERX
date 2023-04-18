package pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageObject.Organizations.OrganizationsListPage;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class BasePage {

    private final SelenideElement menuContent = $(By.xpath("//span[.='Контент']"));
    private final SelenideElement menuContentsModel = $(By.xpath("//span[.='Модели контента']"));
    private final SelenideElement menuActions = $(By.xpath("//span[.='Действия']"));
    private final SelenideElement menuExtensions = $(By.xpath("//span[.='Расширения']"));
    private final SelenideElement menuEnvironments = $(By.xpath("//span[.='Окружения']"));
    private final SelenideElement menuSettings = $(By.xpath("//span[.='Настройки']"));

    private final SelenideElement menuProfileName = $(By.xpath("//span[.='admin1']"));
    private final SelenideElement profileOrganizations = $(By.xpath("//span[.='Организации']"));

    @Step("Перейти в список организаций")
    public OrganizationsListPage goToOrganizationsList() {
        menuProfileName.click();
        profileOrganizations.click();

        return page(OrganizationsListPage.class);
    }





}
