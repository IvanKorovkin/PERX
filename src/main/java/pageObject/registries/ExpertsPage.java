package pageObject.registries;

import io.qameta.allure.Step;
import pageObject.BasePage;

public class ExpertsPage extends BasePage {

    BasePage basePage = new BasePage("Эксперты");
    private final String linkToTheRegistry = basePage.getUrl() + "user/admin-experts";

    public String getLinkToTheRegistry() {
        return linkToTheRegistry;
    }

    @Step("Проверить отображение наименования реестра \"Эксперты\"")
    public void checkNameOfRegistry() {
        basePage.checkNameOfRegistry();
    }

}
