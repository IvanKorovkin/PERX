package helpers;

import com.codeborne.selenide.SelenideElement;

public class Helpers {

    public static boolean isElementPresent(SelenideElement element) {

        return element.exists();
    }

}
