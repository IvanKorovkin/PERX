package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdminProfilePage extends BasicPage{

    public AdminProfilePage(WebDriver driver) {
        super(driver);
    }

    private final String workingPanelInUsersMenuLocator = "//*[contains(text(), 'Рабочая панель')]";

    @FindBy(xpath = "//*[@class=\"header__top-expert-email\"]")
    private WebElement avatarOfUser;

    @FindBy(xpath = workingPanelInUsersMenuLocator)
    private WebElement workingPanelInUsersMenu;

    @Step("Навести курсор мыши на аватар админа")
    public AdminProfilePage moveCursorToAvatar() {
        actions.moveToElement(avatarOfUser).build().perform();

        return new AdminProfilePage(driver);
    }

    @Step("Кликнуть в выпадающем меню на вкладку Рабочая панель")
    public WorkingPanelPage clickToWorkingPanelInUsersMenu() {

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(workingPanelInUsersMenuLocator)));
        workingPanelInUsersMenu.click();

        return new WorkingPanelPage(driver);
    }
}
