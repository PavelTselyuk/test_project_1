package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import core.User;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Главная страница сайта appleinsider.ru
 */
public class FormPage {

    private final static String BASE_URL = "https://demoqa.com/automation-practice-form";

    private final SelenideElement firstNameInput = $x("//input[@id='firstName']");
    private final SelenideElement lastNameInput = $x("//input[@id='lastName']");
    private final SelenideElement emailInput = $x("//input[@id='userEmail']");
    private final ElementsCollection genderRadioButtons = $$x("//input[@name='gender']");
    private final SelenideElement mobileNumberInput = $x("//input[@id='userNumber']");
    private final SelenideElement dateOfBirthInput = $x("//input[@id='dateOfBirthInput']");
    private final SelenideElement subjectsInput = $x("//input[@id='subjectsInput']");

    public SelenideElement getSubjectByTitle(String title) {
        return $x(String.format("//div[contains(@class, 'subjects-auto-complete__option')][text()='%s']", title));
    }

    private final ElementsCollection hobbiesCheckBoxes = $$x("//input[@type='checkbox']");
    private final SelenideElement currentAddressInput = $x("//textarea[@id='currentAddress']");

    private final SelenideElement selectStateDropdownSvg = $x("//div[@id='state']//div[@class='css-1xc3v61-indicatorContainer']/*[1]");
    private final SelenideElement selectCityDropdownSvg = $x("//div[@id='city']//div[@class='css-1xc3v61-indicatorContainer']/*[1]");
    private final SelenideElement cityDropdownContainer = $x("//div[@id='city']//div[@class='css-1xc3v61-indicatorContainer']/../..");

    private final ElementsCollection stateDropdownOptions = $$x("//div[@id='react-select-3-listbox']/div");
    private final ElementsCollection cityDropdownOptions = $$x("//div[@id='react-select-4-listbox']/div");

    private final SelenideElement submitButton = $x("//button[@id='submit']");

    private final SelenideElement studentNameInModalWindow = $x("//td[text()='Student Name']/following-sibling::td[1]");
    private final SelenideElement studentEmailInModalWindow = $x("//td[text()='Student Email']/following-sibling::td[1]");
    private final SelenideElement genderInModalWindow = $x("//td[text()='Gender']/following-sibling::td[1]");
    private final SelenideElement mobileInModalWindow = $x("//td[text()='Mobile']/following-sibling::td[1]");
    private final SelenideElement dateOfBirthInModalWindow = $x("//td[text()='Date of Birth']/following-sibling::td[1]");

    private final SelenideElement subjectsInModalWindow = $x("//td[text()='Subjects']/following-sibling::td[1]");
    private final SelenideElement hobbiesInModalWindow = $x("//td[text()='Hobbies']/following-sibling::td[1]");
    private final SelenideElement pictureInModalWindow = $x("//td[text()='Picture']/following-sibling::td[1]");
    private final SelenideElement addressInModalWindow = $x("//td[text()='Address']/following-sibling::td[1]");
    private final SelenideElement stateAndCityInModalWindow = $x("//td[text()='State and City']/following-sibling::td[1]");

    public SelenideElement getFirstNameInput() {
        return firstNameInput;
    }

    public SelenideElement getLastNameInput() {
        return lastNameInput;
    }

    public SelenideElement getEmailInput() {
        return emailInput;
    }

    public ElementsCollection getGenderRadioButtons() {
        return genderRadioButtons;
    }

    public SelenideElement getMobileNumberInput() {
        return mobileNumberInput;
    }

    public SelenideElement getDateOfBirthInput() {
        return dateOfBirthInput;
    }

    public SelenideElement getSubjectsInput() {
        return subjectsInput;
    }

    public ElementsCollection getHobbiesCheckBoxes() {
        return hobbiesCheckBoxes;
    }

    public SelenideElement getCurrentAddressInput() {
        return currentAddressInput;
    }

    public SelenideElement getSelectStateDropdownSvg() {
        return selectStateDropdownSvg;
    }

    public SelenideElement getSelectCityDropdownSvg() {
        return selectCityDropdownSvg;
    }

    public SelenideElement getCityDropdownContainer() {
        return cityDropdownContainer;
    }

    public SelenideElement getStudentNameInModalWindow() {
        return studentNameInModalWindow;
    }

    public SelenideElement getStudentEmailInModalWindow() {
        return studentEmailInModalWindow;
    }

    public SelenideElement getGenderInModalWindow() {
        return genderInModalWindow;
    }

    public SelenideElement getMobileInModalWindow() {
        return mobileInModalWindow;
    }

    public SelenideElement getDateOfBirthInModalWindow() {
        return dateOfBirthInModalWindow;
    }

    public SelenideElement getSubjectsInModalWindow() {
        return subjectsInModalWindow;
    }

    public SelenideElement getHobbiesInModalWindow() {
        return hobbiesInModalWindow;
    }

    public SelenideElement getPictureInModalWindow() {
        return pictureInModalWindow;
    }

    public SelenideElement getAddressInModalWindow() {
        return addressInModalWindow;
    }

    public SelenideElement getStateAndCityInModalWindow() {
        return stateAndCityInModalWindow;
    }

    public FormPage() {
        Selenide.open(BASE_URL);
    }

    public void enterRequiredParamsAndClickSubmit(
            String name,
            String lastName,
            String email,
            String gender,
            String mobile,
            String dateOfBirth
    ) {
        firstNameInput.setValue(name);
        lastNameInput.setValue(lastName);
        emailInput.setValue(email);
        if (gender.equals("Male")) {
            genderRadioButtons.first().click();
        } else {
            genderRadioButtons.get(1).click();
        }
        mobileNumberInput.setValue(mobile);
        dateOfBirthInput.setValue(dateOfBirth);
        submitButton.scrollIntoView(true).click();
//        textBoxInput.setValue(searchString);
//        textBoxInput.pressEnter(); //sendKeys(Keys.ENTER);
    }


    public void enterAllParamsAndClickSubmit(User user) {
        firstNameInput.setValue(user.getFirstName());
        lastNameInput.setValue(user.getLastName());
        emailInput.setValue(user.getEmail());
        if (user.getGender().equals("Male")) {
            genderRadioButtons.first().click();
        } else {
            genderRadioButtons.get(1).click();
        }
        mobileNumberInput.setValue(user.getMobileNumber());
        dateOfBirthInput.setValue(user.getDateOfBirth());
        for (String subj : user.getSubjects()) {
            subjectsInput.setValue(subj);
            getSubjectByTitle(subj).click();
        }
        for (String hobby : user.getHobbies()) {
            switch (hobby) {
                case "Sports":
                    hobbiesCheckBoxes.first().setSelected(true);
                    break;
                case "Reading":
                    hobbiesCheckBoxes.get(1).setSelected(true);
                    break;
                case "Music":
                    hobbiesCheckBoxes.get(2).setSelected(true);
                    break;
            }
        }
        currentAddressInput.setValue(user.getCurrentAddress());
        selectStateDropdownSvg.scrollIntoView(true).click();
        stateDropdownOptions.first().click();
        selectCityDropdownSvg.click();
        cityDropdownOptions.first().click();

        if (user.getGender().equals("Male")) {
            genderRadioButtons.first().click();
        } else {
            genderRadioButtons.get(1).click();
        }
        mobileNumberInput.setValue(user.getMobileNumber());
        dateOfBirthInput.setValue(user.getDateOfBirth());
        submitButton.scrollIntoView(true).click();
    }

    public void pressSubmitButton() {
        submitButton.scrollIntoView(true).click();
    }

    public void selectState() {
        selectStateDropdownSvg.scrollIntoView(true).click();
        stateDropdownOptions.first().click();
    }
    public void setPhoneNumber(String phoneNumber) {
        mobileNumberInput.setValue(phoneNumber);
    }

}
