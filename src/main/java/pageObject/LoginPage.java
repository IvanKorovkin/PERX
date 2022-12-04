package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasicPage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final String adminName = "lerique@me.com";
    private final String adminPassword = "Vamg8P06#JF6kC2w";

    @FindBy(xpath = "//*[@id=\"Username\"]")
    private WebElement usernameField;

    @FindBy(xpath = "//*[@id=\"Password\"]")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@value=\"login\"]")
    private WebElement entranceButton;

    @Step("Ввести логин Админа")
    public LoginPage enterAdminName() {
        usernameField.sendKeys(adminName);

        return new LoginPage(driver);
    }

    @Step("Ввести пароль Админа")
    public LoginPage enterAdminPassword() {
        passwordField.sendKeys(adminPassword);

        return new LoginPage(driver);
    }

    @Step("Кликнуть на кнопку Войти")
    public AdminProfilePage clickEntranceButton() {
        entranceButton.click();

        return new AdminProfilePage(driver);
    }



}
