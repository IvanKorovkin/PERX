package pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageObject.registries.*;

import static com.codeborne.selenide.Selenide.*;

public class WorkingPanelPage {

    MainPage mainPage = new MainPage();
    public final String linkToTheRegistry = mainPage.urlOfMainPageAutorization + "user/admin";

    private SelenideElement registryExpertiseNew = $(By.xpath("//*[contains(text(), 'Экспертиза (новая)')]"));

    private SelenideElement registryIdentity = $(By.xpath("//*[contains(text(), 'Управление ролями и пользователям')]"));

    private SelenideElement registryListOfApplication = $(By.xpath("//*[contains(text(),'Перечень заявок')]"));

    private SelenideElement registryExperts = $(By.xpath("//*[contains(text(),'Эксперты')]"));

    private SelenideElement registryAgreementExpertNew =
            $(By.xpath("//*[contains(text(),'Договоры и акты с экспертами (новые)')]"));

    private SelenideElement registryOESAdmin = $(By.xpath("//*[contains(text(),'Рассмотрение итогов')]"));

    private SelenideElement registryOES = $(By.xpath("//span[text()='ОЭС']"));

    private SelenideElement registryCoordinatingCommittee = $(By.xpath("//span[text()='Координационный комитет']"));

    private SelenideElement registryAgreement = $(By.xpath("//span[.='Договоры']"));

    private SelenideElement registryReport = $(By.xpath("//span[.='Отчетность по этапам (новый)']"));

    @Step("Кликнуть в рабочей панели на реестр Экспертиза(новая)")
    public ExpertiseNewPage entranceToRegistryExpertiseNew() {
        registryExpertiseNew.click();

        return page(ExpertiseNewPage.class);
    }

    @Step("Кликнуть в рабочей панели на реестр Управление ролями и пользователями")
    public IdentityPage entranceToRegistryIdentity() {
        registryIdentity.click();

        return page(IdentityPage.class);
    }

    @Step("Кликнуть в рабочей панели на реестр Перечень заявок")
    public ListOfApplicationsPage entranceToRegistryListOfApplication() {
        registryListOfApplication.click();

        return page(ListOfApplicationsPage.class);
    }

    @Step("Кликнуть в рабочей панели на реестр Эксперты")
    public ExpertsPage entranceToRegistryExperts() {
            registryExperts.click();

        return page(ExpertsPage.class);
    }

    @Step("Кликнуть в рабочей панели на реестр Договоры и акты с экспертами (новые)")
    public AgreementExpertNewPage entranceToRegistryAgreementExpertNew() {
        registryAgreementExpertNew.click();

        return page(AgreementExpertNewPage.class);
    }

    @Step("Кликнуть в рабочей панели на реестр Рассмотрение итогов")
    public OES_AdminPage entranceToRegistryOESAdmin() {
        registryOESAdmin.click();

        return page(OES_AdminPage.class);
    }

    @Step("Кликнуть в рабочей панели на реестр ОЭС")
    public OES_AdminPage entranceToRegistryOES() {
        registryOES.click();

        return page(OES_AdminPage.class);
    }

    @Step("Кликнуть в рабочей панели на реестр КК")
    public CoordinatingCommitteePage entranceToRegistryCoordinatingCommittee() {
        registryCoordinatingCommittee.click();

        return page(CoordinatingCommitteePage.class);
    }

    @Step("Перейти по прямой ссылке в реестр КК")
    public AccessesDeniedPage followTheLinkCoordinatingCommittee() {
        CoordinatingCommitteePage coordinatingCommitteePage = new CoordinatingCommitteePage();
        open(coordinatingCommitteePage.linkToTheRegistry);

        return page(AccessesDeniedPage.class);
    }

    @Step("Перейти по прямой ссылке в реестр Договоры")
    public AccessesDeniedPage followTheLinkAgreement() {
        AgreementPage agreementPage = new AgreementPage();
        open(agreementPage.linkToTheRegistry);

        return page(AccessesDeniedPage.class);
    }

    @Step("Перейти по прямой ссылке в реестр Отчетность по этапам")
    public AccessesDeniedPage followTheLinkReport() {
        ReportPage reportPage = new ReportPage();
        open(reportPage.linkToTheRegistry);

        return page(AccessesDeniedPage.class);
    }
}
