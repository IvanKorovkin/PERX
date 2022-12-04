package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasicPage{
    public MainPage(WebDriver driver) {
        super(driver);
    }

    private String basicName = "i.korovkin";
    private String basicPassword = "zu_e8Oyah_p0oh";
    private String demoFPG = "new.xn--80afcdbalict6afooklqi5o.xn--p1ai/";
    protected String urlOfMainPageAutorization = "https://" + basicName + ":" + basicPassword + "@" + demoFPG;

    @FindBy(xpath = "//*[@class=\"login-button\"]")
    private WebElement entranceButton;

    @Step("Пройти базовую авторизацию и войти на главную страницу")
    public MainPage entranceToMainPage() {
        driver.get(urlOfMainPageAutorization);

        return this;
    }

    @Step("Кликнуть на кнопку Войти")
    public LoginPage entranceButtonClick() {
        entranceButton.click();

        return new LoginPage(driver);
    }

}
