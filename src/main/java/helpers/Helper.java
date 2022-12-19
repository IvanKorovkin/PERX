package helpers;

import com.codeborne.selenide.SelenideElement;

public class Helper {

    public static boolean isElementPresent(SelenideElement element) {

        return element.exists();
    }

}
