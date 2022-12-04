package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class WorkingPanelPage extends BasicPage{

    public WorkingPanelPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[contains(text(), 'Экспертиза (новая)')]")
    public WebElement registryExpertiseNew;

    @FindBy(xpath = "//*[contains(text(), 'Управление ролями и пользователям')]")
    private WebElement registryIdentity;

    @Step("Кликнуть в рабочей панели на реестр Экспертиза(новая)")
    public ExpertiseNewPage entranceToRegistryExpertiseNew() {
        registryExpertiseNew.click();

        return new ExpertiseNewPage(driver);
    }

    @Step("Кликнуть в рабочей панели на реестр Управление ролями и пользователями")
    public IdentityPage entranceToRegistryIdentity() {
        registryIdentity.click();
        List<WebElement> webElements = driver.findElements(By.xpath("//div[@class='system-roles-block']"));
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(webElements));

        return new IdentityPage(driver);
    }
}
