package pageObject.registries;

import io.qameta.allure.Step;
import pageObject.BasePage;

public class AgreementPage extends BasePage {

    BasePage basePage = new BasePage("Договоры");

    private final String linkToTheRegistry = basePage.getUrl() + "agreement/admin/documents";

    public String getLinkToTheRegistry() {
        return linkToTheRegistry;
    }

    @Step("Проверить отображение наименования реестра \"Договоры\"")
    public void checkNameOfRegistry() {
        basePage.checkNameOfRegistry();
    }

}
