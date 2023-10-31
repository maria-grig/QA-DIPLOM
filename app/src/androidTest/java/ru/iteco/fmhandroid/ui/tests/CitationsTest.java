package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.CitationPage;
import ru.iteco.fmhandroid.ui.pages.Logged;
import ru.iteco.fmhandroid.ui.pages.LoginPage;

@RunWith(AllureAndroidJUnit4.class)
public class CitationsTest extends CitationPage {
    Logged logged = new Logged();
    LoginPage loginPage = new LoginPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Before
    public void setUp() {
        try {
            logged.loggedIn();
        }
        catch (AssertionError e) {
            loginPage.login();
        }
    }

    @Test
    @DisplayName("#51 Просмотр карточки цитаты")
    public void openFullCitation() {
        openCitations();
        fullCitations();
    }
}
