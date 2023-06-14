package pageObject.Spaces;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;

public class CreateSpacePage extends SpacesListPage {

    private final String spaceName = "Пространство для avtotestov 123456 7890 !\"№;%:?*()_+";
    private final String spaceDescription = "Римский император Константин I Великий по достоинству оценил выгодное " +
            "местоположение приморского Византия, расположенного на стыке Европы и Азии. Кроме того, на решение " +
            "Константина повлияла неспокойная обстановка в самом Риме: недовольство знати и постоянные распри в борьбе " +
            "за трон. Император хотел увенчать свою реформаторскую деятельность созданием нового административного " +
            "центра огромной державы. Закладка гин лично решил обозначить его границы.";

    private final SelenideElement submitButton = $(By.xpath("//button[@type='submit']"));
    private final SelenideElement deleteButton = $(By.xpath("//button[.='Удалить пространство']"));
    private final SelenideElement confirmDeleteButton = $(By.xpath("//div[@class='ant-popconfirm-buttons']/button[.='OK']"));

    private final SelenideElement spaceNameInput = $(By.xpath("//input[@id='space_space_name']"));
    private final SelenideElement spaceNameMessage = $(By.xpath("//div[@class='ant-form-item-explain-error']"));
    private final SelenideElement spaceDescriptionInput = $(By.xpath("//input[@id='space_space_description']"));

    private final SelenideElement spaceNameTitle = $(By.xpath("//label[@title='Название пространства']"));
    private final SelenideElement spaceDescriptionTitle = $(By.xpath("//label[@title='Описание пространства']"));

    private final SelenideElement createSpaceMessage = $(By.xpath("//div[.='Пространство успешно создано']"));
    private final SelenideElement repeatSpaceNameError = $(By.xpath("//div[.='already exists']"));
    private final SelenideElement deleteSpaceMessage = $(By.xpath("//div[.='Пространство успешно удалено']"));

    private final SelenideElement quitWithoutSaveButton = $(By.xpath("//button[.='Продолжить без сохранения']"));

    public String getSpaceName() {
        return spaceName;
    }

    public String getSpaceDescription() {
        return spaceDescription;
    }

    @Step("Проверка отображения всех элементов страницы")
    public CreateSpacePage checkPageElements() {
        spaceNameTitle.shouldBe(Condition.visible);
        spaceDescriptionTitle.shouldBe(Condition.visible);
        spaceNameInput.shouldBe(Condition.enabled);
        spaceDescriptionInput.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Проверка, что кнопка ОК неактивна, если не заполнено название пространства")
    public CreateSpacePage checkDisabledButtonOK() {
        submitButton.shouldNotBe(Condition.enabled);

        return this;
    }

    @Step("Проверка всплывающего сообщения, если не заполнено название организации")
    public CreateSpacePage checkSpaceNameMessage() {

        String expectedResult = "Пожалуйста, введите Название пространства";

        spaceNameInput.click();
        spaceDescriptionInput.click();
        spaceNameMessage.shouldBe(Condition.visible);

        Assertions.assertEquals(expectedResult, spaceNameMessage.getText());

        return this;
    }

//    @Step("Проверка появления ошибки на странице, если название пространства пустое (состоит из пробелов)")
//    public CreateSpacePage checkEmptySpaceNameError() {
//        spaceNameInput.sendKeys("    ");
//        submitButton.click();
//        emptySpaceNameError.shouldBe(Condition.enabled);
//
//        return this;
//    }

    @Step("Проверка успешного создания пространства")
    public CreateSpacePage checkSpaceCreate() {
        refresh();
        spaceNameInput.sendKeys(getSpaceName());
        spaceDescriptionInput.sendKeys(getSpaceDescription());
        submitButton.click();

        createSpaceMessage.shouldBe(Condition.enabled);
        deleteButton.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Проверка неуспешного создания пространства с уже существующим названием")
    public SpacesListPage checkSpaceCreateRepeatName() {
        spaceNameInput.sendKeys(getSpaceName());
        submitButton.click();
        repeatSpaceNameError.shouldBe(Condition.enabled);
        Selenide.back();
        quitWithoutSaveButton.click();

        return page(SpacesListPage.class);
    }

    @Step("Удалить пространство")
    public SpacesListPage deleteSpace() {
        deleteButton.click();
        confirmDeleteButton.click();
        deleteSpaceMessage.shouldBe(Condition.enabled);

        return page(SpacesListPage.class);
    }

    @Step("Проверка появления окна с предложением сохранить данные в случае ухода со страницы")
    public CreateSpacePage checkSaveWindow() {
        spaceNameInput.sendKeys(" ");
        getMenuContent().click();
        getContinueWithoutSaveButton().shouldBe(Condition.enabled);
        getCloseModalWindowButton().click();
        refresh();

        return this;
    }


}
