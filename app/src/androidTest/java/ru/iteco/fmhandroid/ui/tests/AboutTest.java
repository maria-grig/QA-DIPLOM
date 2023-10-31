package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.ScreenshotTestWatcher;
import ru.iteco.fmhandroid.ui.pages.AboutPage;
import ru.iteco.fmhandroid.ui.pages.Logged;
import ru.iteco.fmhandroid.ui.pages.LoginPage;

@RunWith(AllureAndroidJUnit4.class)
public class AboutTest extends AboutPage {
    Logged logged = new Logged();
    LoginPage loginPage = new LoginPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Rule
    public TestRule watcher = new ScreenshotTestWatcher();

    @Before
    public void setUp() {
        try {
            logged.loggedIn();
        }
        catch (AssertionError e) {
            loginPage.login();
        }
        tapHamburgerAbout();
    }

    @Test
    @DisplayName("#53 Отображение версии приложения")
    public void displayVersion() {
        version();
    }

    @Test
    @DisplayName("#54 Редирект на политику конфиденциальности")
    public void openPageWithPrivacyPolicy() {
        privacyPolicy();
    }

    @Test
    @DisplayName("#55 Редирект на пользовательское соглашение")
    public void openPageWithTermsOfUse() {
        termsOfUse();
    }

    @Test
    @DisplayName("#56 Отображение держателя авторских прав")
    public void displayCopyright() {
        copyright();
    }
}
