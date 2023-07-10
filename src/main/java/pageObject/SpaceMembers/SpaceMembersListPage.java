package pageObject.SpaceMembers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Selenide.*;

public class SpaceMembersListPage {

    private final SelenideElement addMemberButton = $(By.xpath("//span[.='Добавить пользователя']"));
    private final SelenideElement inviteButton = $(By.xpath("//span[.='Пригласить']"));

    private final SelenideElement userInput = $(By.xpath("//input[@id='collaborator_subject']"));
    private final SelenideElement roleInput = $(By.xpath("//input[@id='collaborator_role']"));
    private final SelenideElement submitButton = $(By.xpath("//button[.='OK']"));
    private final SelenideElement errorMessage = $(By.xpath("//div[@class='ant-form-item-explain-error']"));
    private final SelenideElement cancelButtonAdd = $(By.xpath("//button[.='Отмена']"));
    private final SelenideElement userInList = $(By.xpath("//div[.='Test1 Testov']"));
    private final SelenideElement roleInList = $(By.xpath("//div[@title='Test']"));
    private final SelenideElement role = $(By.xpath("//span[.='Test']"));

    private final SelenideElement invitationEmptyDescription = $(By.xpath("//div[@class='ant-empty-description']"));
    private final SelenideElement invitationEmailInput = $(By.xpath("//input[@id='invitation_email']"));
    private final SelenideElement invitationEmailInputErrorMessage
            = $(By.xpath("//div[@class='ant-form-item-explain-error']"));
    private final SelenideElement invitationRoleInput = $(By.xpath("//input[@id='invitation_role']"));
    private final SelenideElement invitationSubmitButton = $(By.xpath("(//button[.='OK'])[2]"));
    private final SelenideElement cancelButtonInvite = $(By.xpath("(//button[.='Отмена'])[2]"));
    private final SelenideElement deleteInvitationButton = $(By.xpath("(//button[.='Удалить'])[1]"));
    private final SelenideElement deleteInvitationButtonOk = $(By.xpath("(//button[.='OK'])[4]"));
    private final SelenideElement invitationLink = $(By.xpath("//code"));

    private final SelenideElement settingsTestUser = $(By.xpath("(//span[.='Настройки'])[3]"));
    private final SelenideElement settingsRoleInput = $(By.xpath("//span[@class='ant-select-selection-item']"));
    private final SelenideElement settingsSubmitButton = $(By.xpath("(//button[.='OK'])[3]"));
    private final SelenideElement settingsDeleteButton = $(By.xpath("//button[.='Удалить']"));

    private final SelenideElement invitePageTitle = $(By.xpath("//span[.='Принять приглашение?']"));
    private final SelenideElement invitePageButtonYes = $(By.xpath("//button[.='Да']"));
    private final SelenideElement invitePageButtonNo = $(By.xpath("//button[.='Нет']"));

    @Step("Проверка отображения всех элементов страницы")
    public SpaceMembersListPage checkPageMembers() {
        addMemberButton.shouldBe(Condition.enabled);
        inviteButton.shouldBe(Condition.enabled);

        addMemberButton.click();
        userInput.shouldBe(Condition.enabled);
        roleInput.shouldBe(Condition.enabled);
        submitButton.shouldBe(Condition.enabled);
        cancelButtonAdd.click();

        inviteButton.click();
        invitationEmailInput.shouldBe(Condition.enabled);
        invitationRoleInput.shouldBe(Condition.enabled);
        submitButton.shouldBe(Condition.enabled);
        cancelButtonInvite.click();

        return this;
    }

    @Step("Проверка всплывающего сообщения, если не заполнено поле Пользователь")
    public SpaceMembersListPage checkErrorMessageUserInput() throws InterruptedException {

        String expectedResult = "Пожалуйста, введите Пользователь";

        refresh();
        Thread.sleep(500);
        addMemberButton.shouldBe(Condition.enabled);
        addMemberButton.click();
        submitButton.shouldBe(Condition.enabled);
        submitButton.click();
        errorMessage.shouldBe(Condition.visible);

        Assertions.assertEquals(expectedResult, errorMessage.getText());

        return this;
    }

    @Step("Проверка успешного добавления участника")
    public SpaceMembersListPage checkAddMember() {
        userInput.click();
        userInList.click();
        submitButton.click();
        settingsTestUser.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Проверка добавления участнику роли")
    public SpaceMembersListPage checkAddRole() {
        refresh();
        settingsTestUser.click();

        try {
            settingsRoleInput.click();
        } catch (Exception E) {
            $(By.xpath("(//span[@title=''])[2]")).click();
        }

        roleInList.click();
        submitButton.click();
        role.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Проверка удаления участника из пространства")
    public SpaceMembersListPage checkDeleteMember() {
        settingsTestUser.click();
        settingsDeleteButton.click();

        settingsTestUser.shouldNotBe(Condition.exist);

        return this;
    }

    @Step("Проверка работы кнопки Пригласить")
    public SpaceMembersListPage checkInvitationButton() {
        //refresh();
        inviteButton.shouldBe(Condition.enabled);
        inviteButton.click();
        try {
            invitationEmailInput.shouldBe(Condition.enabled);
            invitationRoleInput.shouldBe(Condition.enabled);
            submitButton.shouldBe(Condition.enabled);
        } catch (Exception E) {
            refresh();
            inviteButton.click();
        }
        invitationEmailInput.shouldBe(Condition.enabled);
        invitationRoleInput.shouldBe(Condition.enabled);
        submitButton.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Проверка валидации поля Почта в окне приглашения участника")
    public SpaceMembersListPage checkValidationEmailInput(String email) {

        String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;

        invitationEmailInput.sendKeys(deleteString);
        invitationEmailInput.shouldBe(Condition.empty);
        invitationEmailInput.sendKeys(email);
        submitButton.click();

        invitationEmailInputErrorMessage.shouldBe(Condition.visible);

        return this;
    }

    @Step("Проверка формирования ссылки-приглашения")
    public SpaceMembersListPage checkCreateInvitationLink() {

        String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;
        String email = "test@test.ru";

        invitationEmailInput.sendKeys(deleteString);
        invitationEmailInput.sendKeys(email);
        submitButton.click();

        invitationEmptyDescription.shouldNotBe(Condition.exist);
        deleteInvitationButton.shouldBe(Condition.enabled);

        return this;
    }

    @Step("Проверка перехода по ссылке приглашению")
    public SpaceMembersListPage checkFollowInvitationLink() throws InterruptedException {

        String email = "test@test.ru";

        refresh();
        Thread.sleep(500);
        inviteButton.shouldBe(Condition.enabled);
        inviteButton.click();
        try {
            invitationEmailInput.shouldBe(Condition.enabled);
        } catch (Exception E) {
            inviteButton.click();
        }
        invitationEmailInput.sendKeys(email);
        submitButton.click();

        String link = "https://" + invitationLink.getText();

        Selenide.executeJavaScript("window.open(\"" + link +"\");");
        open(link);
        refresh();
        Selenide.switchTo().window(1);

        invitePageTitle.shouldBe(Condition.visible);
        invitePageButtonYes.shouldBe(Condition.enabled);
        invitePageButtonNo.shouldBe(Condition.enabled);

        invitePageButtonNo.click();
        invitationEmptyDescription.shouldBe(Condition.visible);

        return this;
    }

    @Step("Проверка удаления ссылки-приглашения")
    public SpaceMembersListPage checkDeleteInvitationLink() {

        refresh();
        deleteInvitationButton.click();
        submitButton.click();

        invitationEmptyDescription.shouldBe(Condition.visible);

        return this;
    }

}
