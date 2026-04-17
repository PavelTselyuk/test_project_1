package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

abstract public class BaseTest {

    public void setup() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.browserSize = "1440x900";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }



    @BeforeEach
    public void init() {
        setup();
    }



    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
