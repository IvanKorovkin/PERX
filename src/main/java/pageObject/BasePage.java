package pageObject;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class BasePage {

    private String name;

    private final SelenideElement registryName = $(By.xpath("//*[@class='information-bar__item_name']"));

    private final SelenideElement avatarOfUser = $(By.xpath("//a[@class='header__top-expert']"));

    public BasePage(String name) {
        this.name = name;
    }

    public BasePage() {
    }

    public String getUrl() {
        String basicName = "i.korovkin";
        String basicPassword = "zu_e8Oyah_p0oh";
        String demoFPG = "new.xn--80afcdbalict6afooklqi5o.xn--p1ai/";
        return "https://" + basicName + ":" + basicPassword + "@" + demoFPG;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void checkNameOfRegistry() {
        Assertions.assertEquals(name, registryName.getText());
    }

    public void moveCursorToAvatar() {
        avatarOfUser.hover();
    }

}
