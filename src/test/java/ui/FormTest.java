package ui;

import core.BaseTest;
import core.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.FormPage;

import static com.codeborne.selenide.Condition.*;

public class FormTest extends BaseTest {

    private final User TEST_USER = new User.Builder()
            .firstName("John")
            .lastName("Sena")
            .email("abracadabra@tr.com")
            .gender("Male")
            .mobileNumber("1234567890")
            .dateOfBirth(12, 5, 2001)
            .subjects(new String[]{"English", "Computer Science"})
            .hobbies(new String[]{"Sports"})
            .currentAddress("Lorem ipsum")
            .state("NCR")
            .city("Delhi")
            .build();

    @Test
    public void enterValidRequiredParamsThenShowModalWindowWithEnteredDataAndNoError() {
        FormPage formPage = new FormPage();
        formPage.enterRequiredParamsAndClickSubmit(
                TEST_USER.getFirstName(),
                TEST_USER.getLastName(),
                TEST_USER.getEmail(),
                TEST_USER.getGender(),
                TEST_USER.getMobileNumber(),
                TEST_USER.getDateOfBirth()
        );
        int a = 5;
        Assertions.assertEquals(TEST_USER.getFirstName() + " " + TEST_USER.getLastName(), formPage.getStudentNameInModalWindow().getText());
        Assertions.assertEquals(TEST_USER.getEmail(), formPage.getStudentEmailInModalWindow().getText());
        Assertions.assertEquals("", formPage.getHobbiesInModalWindow().getText());

    }

    @Test
    public void whenEnterValidAllParamsNoPictureThenShowModalWindowWithAllParams() {
        FormPage formPage = new FormPage();
        formPage.enterAllParamsAndClickSubmit(TEST_USER);
        int a = 5;
        Assertions.assertEquals(TEST_USER.getFirstName() + " " + TEST_USER.getLastName(), formPage.getStudentNameInModalWindow().getText());
        Assertions.assertEquals(TEST_USER.getEmail(), formPage.getStudentEmailInModalWindow().getText());
        String hobbiesAsString = String.join(", ", TEST_USER.getHobbies());
        Assertions.assertEquals(hobbiesAsString, formPage.getHobbiesInModalWindow().getText());
    }

    @Test
    public void whenPressSubmitButtonWithEmptyFieldsThenRequiredFieldsBorderColorRed() {
        FormPage formPage = new FormPage();
        formPage.pressSubmitButton();
        int a = 5;

        //Red border
        formPage.getFirstNameInput().shouldNotHave(cssValue("background-image", "none"));
        Assertions.assertTrue(formPage.getFirstNameInput().getCssValue("background-image").contains("stroke"));
        formPage.getFirstNameInput().shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));

        formPage.getLastNameInput().shouldNotHave(cssValue("background-image", "none"));
        formPage.getLastNameInput().shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));

        formPage.getMobileNumberInput().shouldNotHave(cssValue("background-image", "none"));
        formPage.getMobileNumberInput().shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));

        formPage.getGenderRadioButtons().first().shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));

        //Green border
        formPage.getEmailInput().shouldNotHave(cssValue("background-image", "none"));
        Assertions.assertTrue(formPage.getEmailInput().getCssValue("background-image").contains("23198754"));
        formPage.getEmailInput().shouldHave(cssValue("border-color", "rgb(25, 135, 84)"));

        formPage.getDateOfBirthInput().shouldHave(cssValue("border-color", "rgb(25, 135, 84)"));
        formPage.getCurrentAddressInput().shouldHave(cssValue("border-color", "rgb(25, 135, 84)"));
        formPage.getHobbiesCheckBoxes().first().shouldHave(cssValue("border-color", "rgb(25, 135, 84)"));

    }

    @Test
    public void selectCityEnabledOnlyAfterStateChosen() {
        FormPage formPage = new FormPage();
        formPage.getCityDropdownContainer().shouldHave(attribute("aria-disabled", "true"));
        formPage.selectState();
        formPage.getCityDropdownContainer().shouldNotHave(cssClass("aria-disabled"));
    }

    @Test
    public void whenEnterInvalidNumberAndSubmitThenNumberInputShowError() {
        FormPage formPage = new FormPage();
        formPage.setPhoneNumber("153feog");
        formPage.pressSubmitButton();
        formPage.getMobileNumberInput().shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }
}
