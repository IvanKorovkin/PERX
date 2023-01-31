package pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AuthPage {

    private final String adminName = "adminminfin@mail.ru";
    private final String adminPassword = "Vamg8P06#JF6kC2w";

    private final SelenideElement inputName = $(By.xpath("//input[@id='Username']"));
    private final SelenideElement inputPassword = $(By.xpath("//input[@id='Password']"));
    private final SelenideElement entranceButton = $(By.xpath("//button[@id='loginBtn']"));

    public String getAdminName() {
        return adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public AuthPage enterAdminName() {
        inputName.sendKeys(getAdminName());

        return this;
    }

    public AuthPage enterAdminPassword() {
        inputPassword.sendKeys(getAdminPassword());

        return this;
    }

    public void clickEntranceButton() {
        entranceButton.click();
    }

    @Step("Залогиниться под админом")
    public void loginAdmin() {
        enterAdminName();
        enterAdminPassword();
        clickEntranceButton();
    }



}
