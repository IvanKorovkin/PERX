package pageObject.otherPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AccessesDeniedPage {

    private final SelenideElement accessesDenied = $(By
            .xpath("//div[contains(text(),'Доступ на запрашиваемую страницу запрещен')]"));

    @Step("Проверить недоступность реестра для данной роли")
    public void checkInaccessibilityRegistryForThisRole() {
        String accessesDeniedText = "Доступ на запрашиваемую страницу запрещен";
        Assertions.assertEquals(accessesDeniedText, accessesDenied.getText());
    }

}
