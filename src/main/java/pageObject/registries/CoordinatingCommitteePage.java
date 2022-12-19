package pageObject.registries;

import io.qameta.allure.Step;
import pageObject.BasePage;

public class CoordinatingCommitteePage extends BasePage {

    BasePage basePage = new BasePage("Координационный комитет");

    private final String linkToTheRegistry = basePage.getUrl() + "application/admin/coordinating-committee";

    public String getLinkToTheRegistry() {
        return linkToTheRegistry;
    }

    @Step("Проверить отображение наименования реестра \"Координационный комитет\"")
    public void checkNameOfRegistry() {
        basePage.checkNameOfRegistry();
    }

}
