package pageObject.registries;

import io.qameta.allure.Step;
import pageObject.BasePage;

public class AgreementExpertNewPage extends BasePage {

    BasePage basePage = new BasePage("Реестр договоров и актов с экспертами");

    private final String linkToTheRegistry = basePage.getUrl() + "agreement-expert/admin/documents";

    public String getLinkToTheRegistry() {
        return linkToTheRegistry;
    }

    @Step("Проверить отображение наименования реестра \"Договоры и Акты с экспертами (новые)\"")
    public void checkNameOfRegistry() {
        basePage.checkNameOfRegistry();
    }

}
