package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserProfilePage extends BasicPage{

    public UserProfilePage(WebDriver driver) {
        super(driver);
    }

    protected String basicName = "i.korovkin";
    protected String basicPassword = "zu_e8Oyah_p0oh";
    protected String demoFPG = "new.xn--80afcdbalict6afooklqi5o.xn--p1ai";
    protected String urlOfMainPageAutorization = "https://" + basicName + ":" + basicPassword + "@" + demoFPG + "/user/profile";

    @FindBy(xpath = "//*[@class=\"header__top-expert-email\"]")
    private WebElement avatarOfMainExpert;

    @FindBy(xpath = "//a[@class='header__top-expert']")
    private WebElement avatarOfWatcher;

    @FindBy(xpath = "//*[contains(text(), 'Рабочая панель')]")
    private WebElement buttonWorkingPanelInMenu;


    @Step("Навести курсор мыши на аватар Главного эксперта")
    public UserProfilePage moveToAvatarOfMainExpert() {
        actions.moveToElement(avatarOfMainExpert).build().perform();

        return this;
    }

    @Step("Навести курсор мыши на аватар пользователя")
    public UserProfilePage moveToAvatar() {
        actions.moveToElement(avatarOfWatcher).build().perform();
        webDriverWait.until(ExpectedConditions.visibilityOf(buttonWorkingPanelInMenu));

        return this;
    }

    @Step("Кликнуть в выпадающем меню пользователя на вкладку Рабочая панель")
    public WorkingPanelPage clickToWorkingPanelInUsersMenu() {
        buttonWorkingPanelInMenu.click();
        WorkingPanelPage workingPanelPage = new WorkingPanelPage(driver);
        webDriverWait.until(ExpectedConditions.visibilityOf(workingPanelPage.registryExpertiseNew));

        return new WorkingPanelPage(driver);
    }

    @Step("Перейти в реестр Экспертиза по прямой ссылке")
    public ExpertiseNewPage checkTransitionToExpertiseRegistryByLink() {
        ExpertiseNewPage expertiseNewPage = new ExpertiseNewPage(driver);
        driver.get(expertiseNewPage.linkToTheRegistry);

        return new ExpertiseNewPage(driver);
    }

}
