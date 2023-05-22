package ru.gloomyana.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

public class SteamWebParameterizedTest extends TestBase {

    @BeforeEach
    void setup() {
        steamMainPage.openMainPage();
    }

    @ValueSource(strings = {"STORE", "COMMUNITY", "ABOUT", "SUPPORT"})
    @Tags({
            @Tag("BLOCKER"),
            @Tag("WEB")
    })
    @ParameterizedTest(name = "Menu item {0} exist and clickable")
    void menuItemShouldBeClickable(String menuItem) {
        steamMainPage.verifyMenuItemClickable(menuItem);
    }

    @CsvFileSource(resources = "/searchResultsShouldContainExpectedText.csv")
    @ParameterizedTest(name = "Search by {0} should contain top item {1} in popup menu")
    @Tags({
            @Tag("MAJOR"),
            @Tag("WEB")
    })
    void searchShouldReturnExpectedText(String searchItem, String expectedTitle) {
        steamMainPage.searchBoxInput(searchItem);
        steamMainPage.verifySearchPopupTopItem(expectedTitle);
    }

    static Stream<Arguments> ButtonsForChosenLanguage() {
        return Stream.of(
                Arguments.of("Русский (Russian)",
                        List.of("МАГАЗИН", "СООБЩЕСТВО", "О STEAM", "ПОДДЕРЖКА")),
                Arguments.of("English (английский)",
                        List.of("STORE", "COMMUNITY", "ABOUT", "SUPPORT")),
                Arguments.of("Français (French)",
                        List.of("MAGASIN", "COMMUNAUTÉ", "À PROPOS", "SUPPORT"))
        );
    }

    @MethodSource("ButtonsForChosenLanguage")
    @ParameterizedTest(name = "Menu should contain all buttons: {1} for language {0}")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("WEB")
    })
    void menuShouldContainAllButtonsForChosenLanguage(String language, List<String> expectedButtons) {
        steamMainPage.changeLanguage(language);
        steamMainPage.verifyMenuItemListExistence(expectedButtons);
    }
}
