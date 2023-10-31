package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;


import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.ScreenshotTestWatcher;
import ru.iteco.fmhandroid.ui.pages.AboutPage;
import ru.iteco.fmhandroid.ui.pages.Logged;
import ru.iteco.fmhandroid.ui.pages.LoginPage;

@RunWith(AndroidJUnit4.class)
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
    public void displayVersion() {
        version();
    }

    @Test
    public void openPageWithPrivacyPolicy() {
        privacyPolicy();
    }

    @Test
    public void openPageWithTermsOfUse() {
        termsOfUse();
    }

    @Test
    public void displayCopyright() {
        copyright();
    }
}
