package ru.gloomyana.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.gloomyana.pages.SteamMainPage;


public class TestBase {
    SteamMainPage steamMainPage = new SteamMainPage();
    static ChromeOptions options = new ChromeOptions();
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        options.addArguments("--lang=en_US");
        Configuration.browserCapabilities = options;
    }
}
