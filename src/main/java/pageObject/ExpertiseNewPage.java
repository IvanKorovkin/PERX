package pageObject;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ExpertiseNewPage extends BasicPage{

    public ExpertiseNewPage(WebDriver driver) {
        super(driver);
    }

    MainPage mainPage = new MainPage(driver);
    protected final String linkToTheRegistry = mainPage.urlOfMainPageAutorization + "application/admin/expertise";

    @FindBy(xpath = "//*[@class=\"information-bar__item_name\"]")
    private WebElement registryName;

    private final String statusOfApplicationLocator = "//th[@role='columnheader']//a[contains(text(),'Статус заявки')]";
    @FindBy(xpath = statusOfApplicationLocator)
    private WebElement statusOfApplication;

    private final String statusOfExpertiseLocator = "//th[@role='columnheader']//a[contains(text(),'Статус экспертизы')]";
    @FindBy(xpath = statusOfExpertiseLocator)
    private WebElement statusOfExpertise;

    private final String nameOfExpertLocator = "//th[@role='columnheader']//a[contains(text(),'ФИО эксперта')]";
    @FindBy(xpath = nameOfExpertLocator)
    private WebElement nameOfExpert;

    private final String colorOfExpertLocator = "//th[@role='columnheader']//a[contains(text(),'Цвет эксперта')]";
    @FindBy(xpath = colorOfExpertLocator)
    private WebElement colorOfExpert;

    private final String checkForPlagiarismLocator = "//th[@role='columnheader']//a[contains(text(),'Проверка на плагиат')]";
    @FindBy(xpath = checkForPlagiarismLocator)
    private WebElement checkForPlagiarism;

    private final String inscriptionAccessDeniedLocator = "//div[contains(text(),'Доступ на запрашиваемую страницу запрещен')]";
    @FindBy(xpath = inscriptionAccessDeniedLocator)
    private WebElement inscriptionAccessDenied;

    public ExpertiseNewPage getRegistryName() {
        webDriverWait.until(ExpectedConditions.visibilityOf(registryName));
        registryName.getText();

        return this;
    }

    @Step("Проверить отображение наименования реестра Экспертиза")
    public void checkNameOfRegistry(String name) {
        Assertions.assertEquals(name, registryName.getText());
    }

    // метод для проверки, есть ли элемент на странице
    private boolean isElementPresent(String xpath) {
        try {
            driver.findElement(By.xpath(xpath));

            return true;
        } catch (NoSuchElementException e) {

            return false;
        }
    }

    @Step("Проверить, что для аналитика и наблюдателя в реестре Экспертиза не отображается поле Статус заявки")
    public void checkClosedColumnStatusOfApplicationForWatcherAndAnalytic() {
        Assertions.assertFalse(isElementPresent(statusOfApplicationLocator));
    }

    @Step("Проверить, что для аналитика и наблюдателя в реестре Экспертиза не отображается поле Статус экспертизы")
    public void checkClosedColumnStatusOfExpertiseForWatcherAndAnalytic() {
        Assertions.assertFalse(isElementPresent(statusOfExpertiseLocator));
    }

    @Step("Проверить, что для аналитика и наблюдателя в реестре Экспертиза не отображается поле Имя эксперта")
    public void checkClosedColumnNameOfExpertForWatcherAndAnalytic() {
        Assertions.assertFalse(isElementPresent(nameOfExpertLocator));
    }

    @Step("Проверить, что для аналитика и наблюдателя в реестре Экспертиза не отображается поле Цвет эксперта")
    public void checkClosedColumnColorOfExpertForWatcherAndAnalytic() {
        Assertions.assertFalse(isElementPresent(colorOfExpertLocator));
    }

    @Step("Проверить, что для аналитика и наблюдателя в реестре Экспертиза не отображается поле Проверка на плагиат")
    public void checkClosedColumnCheckForPlagiarismForWatcherAndAnalytic() {
        Assertions.assertFalse(isElementPresent(checkForPlagiarismLocator));
    }

    @Step("Проверить недоступность реестра экспертиза для данной роли")
    public void checkInaccessibilityForExpertiseRegistryForOtherRole() {
        webDriverWait.until(ExpectedConditions.visibilityOf(inscriptionAccessDenied));
        Assertions.assertEquals(true, isElementPresent(inscriptionAccessDeniedLocator));
    }


}
