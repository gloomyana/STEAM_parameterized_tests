package ru.gloomyana.pages;

import com.codeborne.selenide.ElementsCollection;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SteamMainPage {

    public void openMainPage() {
        open("https://store.steampowered.com");
    }

    public void verifyMenuItemClickable(String menuItem) {
        $(".supernav_container").$(byText(menuItem)).shouldBe(and("Clickable", visible, enabled));
    }

    public void searchBoxInput(String searchItem) {
        $("#store_nav_search_term").setValue(searchItem);
    }

    public void verifySearchPopupTopItem(String expectedTitle) {
        $(".match_category_top").$(".match_name").shouldHave(text(expectedTitle));
    }

    public void changeLanguage(String language) {
        $("#language_pulldown").click();
        $("#language_dropdown").$(byText(language)).click();
    }

    public void verifyMenuItemListExistence(List<String> expectedButtons) {
        ElementsCollection foundCollection = $$(".supernav_container .menuitem")
                .shouldHave(sizeGreaterThanOrEqual(4));
        ElementsCollection filteredCollection = foundCollection.filter(visible);
        filteredCollection.shouldHave(texts(expectedButtons));
    }
}