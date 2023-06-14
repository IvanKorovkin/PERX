package pageObject.Applications;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageObject.BasePage;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class ApplicationsListPage extends BasePage {

    private final SelenideElement createApplicationButton = $(By.xpath("//button[.='Создать приложение']"));
    private final SelenideElement testAppInList = $(By.xpath("//a[.='Test_app']"));

    @Step("Проверка отображения элементов на странице списка приложений")
    public ApplicationsListPage checkApplicationsPage() {
        createApplicationButton.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Нажать на кнопку Создать приложение")
    public CreateApplicationPage clickCreateApplicationButton() {
        createApplicationButton.click();

        return page(CreateApplicationPage.class);
    }

    @Step("Проверка отображения созданного автотестом приложения в списке приложений")
    public ApplicationsListPage checkDisplayAppInList() {
        testAppInList.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Перейти в настройки созданного автотестом приложения")
    public CreateApplicationPage goToTestApp() {
        testAppInList.click();

        return page(CreateApplicationPage.class);
    }

    @Step("Проверка того, что удлаенное приложение не отображается в списке приложений")
    public ApplicationsListPage checkAbsenceDeleteAppInList() {
        testAppInList.shouldNotBe(Condition.exist);

        return this;
    }

}
