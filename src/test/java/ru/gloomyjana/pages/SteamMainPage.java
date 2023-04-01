package ru.gloomyjana.pages;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SteamMainPage {

    public void openMainPage() {
        open("https://store.steampowered.com");
    }

    public void verifyMenuItemExistence(String menuItem) {
        $(".supernav_container").shouldHave(text(menuItem));
    }

    public void verifyMenuItemClickable(String menuItem) {
        $(".supernav_container").$(byText(menuItem))
                .shouldBe(and("Clickable", visible, enabled));
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

    public void verifyMainMenuItemsExistence(List<String> expectedButtons) {
        $$(".supernav_container").filter(visible).shouldHave(texts(expectedButtons));
    }
}