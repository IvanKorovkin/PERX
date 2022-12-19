package pageObject.registries;

import io.qameta.allure.Step;
import pageObject.BasePage;

public class OESPage extends BasePage {

    BasePage basePage = new BasePage("Экспертный совет");
    private final String linkToTheRegistry = basePage.getUrl() + "application/admin/oes";

    public String getLinkToTheRegistry() {
        return linkToTheRegistry;
    }

    @Step("Проверить отображение наименования реестра \"Экспертный совет\"")
    public void checkNameOfRegistry() {
        basePage.checkNameOfRegistry();
    }

}
