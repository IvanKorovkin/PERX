package pageObject.registries;

import io.qameta.allure.Step;
import pageObject.BasePage;

public class ReportPage extends BasePage {

    BasePage basePage = new BasePage("Отчетность по этапам");

    private final String linkToTheRegistry = basePage.getUrl() + "reports/stage-report/index";

    public String getLinkToTheRegistry() {
        return linkToTheRegistry;
    }

    @Step("Проверить отображение наименования реестра \"Отчетность по этапам\"")
    public void checkNameOfRegistry() {
       basePage.checkNameOfRegistry();
    }

}
