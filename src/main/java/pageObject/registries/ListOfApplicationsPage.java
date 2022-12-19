package pageObject.registries;

import io.qameta.allure.Step;
import pageObject.BasePage;

public class ListOfApplicationsPage extends BasePage {

    BasePage basePage = new BasePage("Перечень заявок");
    private final String linkToTheRegistry = basePage.getUrl() + "application/admin/validation";

    public String getLinkToTheRegistry() {
        return linkToTheRegistry;
    }

    @Step("Проверить отображение наименования реестра Экспертиза")
    public void checkNameOfRegistry() {
        basePage.checkNameOfRegistry();
    }

}
