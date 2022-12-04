package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class IdentityPage extends BasicPage{

    public IdentityPage(WebDriver driver) {
        super(driver);
    }

    private final String adminPassword = "Vamg8P06#JF6kC2w";

    @FindBy(xpath = "//span[@data-field='SystemRoles']")
    private WebElement systemRolesFilter;

    @FindBy(xpath = "//li[text()='Пользователи' and @data-offset-index='19']")
    private WebElement usersInSystemRolesFilter;

    @FindBy(xpath = "(//*[@class='change-role user-table-svg tooltip tooltipstered'])[1]")
    private WebElement buttonEditSystemRoleOfFirstUser;

    @FindBy(xpath = "//h4[.='Изменение системных ролей пользователя']")
    private WebElement nameModalWindowOfEditSystemRole;

    @FindBy(xpath = "//*[@class='change-role-select']")
    private WebElement inputForEnterSystemRoleClick;

    @FindBy(css = "#SelectedRolesIds_chosen .chosen-search-input")
    private WebElement inputForEnterSystemRoleEnter;

    @FindBy(xpath = "//li[@data-option-array-index='10']")
    private WebElement systemRoleOfMainExpertInList;

    @FindBy(xpath = "")
    private WebElement systemRoleOfWatcherInList;

    @FindBy(css = "#change-role-modal .modal-header svg")
    private WebElement buttonCloseModalWindowOfChangeSystemRole;

    @FindBy(xpath = "(//button[@class='btn btn-primary js-show-user-actions'])[1]")
    private WebElement buttonManagementFirstUser;

    @FindBy(xpath = "//div[@class='user-actions_block btn btn-outline js-auth-user']")
    private WebElement buttonAuthUser;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement inputEnterAdminPasswordIdentity;

    @FindBy(xpath = "//button[@id='confirm-change']")
    private WebElement buttonConfirmIdentity;

    @Step("Открыть выпадающий список в фильтре системных ролей")
    public IdentityPage openSystemRolesFilter() {
        systemRolesFilter.click();
        webDriverWait.until(ExpectedConditions
                .visibilityOf(driver.findElement(By.xpath("//li[text()='Пользователи' and @data-offset-index='19']"))));

        return this;
    }

    @Step("Выбрать в выпадающем списоке системных ролей роль Пользователи")
    public IdentityPage selectInSystemRolesFilterUsers() {
        webDriverWait.until(ExpectedConditions
                .visibilityOf(driver.findElement(By.xpath("//li[text()='Пользователи' and @data-offset-index='19']"))));
        try {
            webDriverWait.until(ExpectedConditions
                    .elementToBeClickable(driver.findElement(By.xpath("//li[text()='Пользователи' and @data-offset-index='19']"))));
        } catch (Exception e) {
            webDriverWait.until(ExpectedConditions
                    .visibilityOf(driver.findElement(By.xpath("//li[text()='Пользователи' and @data-offset-index='19']"))));
        }
        usersInSystemRolesFilter.click();
        List<WebElement> usersList = driver
                .findElements(By.xpath("//div[@class='system-roles-block' and contains(span,'Пользователь')]"));
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(usersList));

//        webDriverWait.until(ExpectedConditions
//                .elementToBeClickable(driver.findElement(By.xpath("//li[text()='Пользователи' and @data-offset-index='19']"))));
//        usersInSystemRolesFilter.click();
//        List<WebElement> usersList = driver
//                .findElements(By.xpath("//div[@class='system-roles-block' and contains(span,'Пользователь')]"));
//        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(usersList));

        return this;
    }

    @Step("Кликнуть на кнопку редактирования системной роли первого пользователя по списку")
    public IdentityPage editSystemRoleOfFirstUserInList() {

        try {
            webDriverWait.until(ExpectedConditions
                    .invisibilityOf(driver.findElement(By.xpath("//div[@class='k-loading-mask']"))));
        } catch (Exception e) {
            webDriverWait.until(ExpectedConditions
                    .elementToBeClickable(buttonEditSystemRoleOfFirstUser));
            buttonEditSystemRoleOfFirstUser.click();
        }


        webDriverWait.until(ExpectedConditions
                .elementToBeClickable(buttonEditSystemRoleOfFirstUser));
        buttonEditSystemRoleOfFirstUser.click();
        webDriverWait.until(ExpectedConditions.visibilityOf(nameModalWindowOfEditSystemRole));

        return this;
    }

    @Step("Ввести в модальном окне редактирования системных ролей какую-либо роль")
    public IdentityPage enterNewSystemRoleInModalWindow(String systemRole) {
        inputForEnterSystemRoleClick.click();
        inputForEnterSystemRoleEnter.sendKeys(systemRole);
        List<WebElement> webElements = driver.findElements(By.xpath("//li[@data-option-array-index]"));
        webElements.stream().filter(f -> f.getText().contains(systemRole)).findFirst().get().click();
        //systemRoleOfMainExpertInList.click();

        return this;
    }

    @Step("Открыть выпадающий список в фильтре системных ролей")
    public IdentityPage closeModalWindowOfChangeSystemRole() {
        buttonCloseModalWindowOfChangeSystemRole.click();

        return this;
    }

    @Step("Кликнуть на кнопку Управление")
    public IdentityPage clickButtonManagementFirstUser() {
        List<WebElement> usersList = driver
                .findElements(By.xpath("//div[@class='system-roles-block' and contains(span,'Пользователь')]"));
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(usersList));
        webDriverWait.until(ExpectedConditions
                .elementToBeClickable(buttonEditSystemRoleOfFirstUser));
        buttonManagementFirstUser.click();

        return this;
    }

    @Step("Кликнуть на кнопку Авторизоваться за пользователя")
    public IdentityPage clickButtonAuthUser() {
        buttonAuthUser.click();

        return this;
    }

    @Step("Ввести пароль для подтверждения действия")
    public IdentityPage enterAdminPasswordIdentity() {
        inputEnterAdminPasswordIdentity.sendKeys(adminPassword);

        return this;
    }

    @Step("Кликнуть на кнопку Подтвердить")
    public UserProfilePage confirmIdentity() {
        buttonConfirmIdentity.click();
        UserProfilePage mainExpertProfilePage = new UserProfilePage(driver);
        webDriverWait.until(ExpectedConditions.urlToBe(mainExpertProfilePage.urlOfMainPageAutorization));

        return new UserProfilePage(driver);
    }













}
