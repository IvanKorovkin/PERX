package pageObject.registries;

import io.qameta.allure.Step;
import pageObject.BasePage;

public class OES_AdminPage extends BasePage {

    BasePage basePage = new BasePage("Рассмотрение итогов");
    private final String linkToTheRegistry = basePage.getUrl() + "application/admin/oes-admin";

    public String getLinkToTheRegistry() {
        return linkToTheRegistry;
    }

    @Step("Проверить отображение наименования реестра \"Рассмотрение итогов\"")
    public void checkNameOfRegistry() {
        basePage.checkNameOfRegistry();
    }

}
