package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.FilterClaimPage;
import ru.iteco.fmhandroid.ui.pages.Logged;
import ru.iteco.fmhandroid.ui.pages.LoginPage;
import ru.iteco.fmhandroid.ui.utils.EspressoBaseTest;

@RunWith(AllureAndroidJUnit4.class)
public class FilterClaimTest extends EspressoBaseTest {
    Logged logged = new Logged();
    LoginPage loginPage = new LoginPage();
    FilterClaimPage filterClaimPage = new FilterClaimPage();

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
        tapHamburger("Claims");
    }

    @Test
    public void changeOpenToInProgressStatus() {
        String text = "Open";
        String oldStatus = "take to work";
        String newStatus = "In progress";

        filterClaimPage.changeOpen(text, oldStatus, newStatus);
    }
    @Test
    public void changeOpenToCanceledStatus() {
        String text = "Open";
        String oldStatus = "Cancel";
        String newStatus = "Canceled";

        filterClaimPage.changeOpen(text, oldStatus, newStatus);
    }
    @Test
    public void changeInProgressToOpenStatus() {
        String oldStatus = "Throw off";
        String newStatus = "Open";

        filterClaimPage.changeInProgress(oldStatus, newStatus);
    }

    @Test
    public void changeInProgressToExecutedStatus() {
        String oldStatus = "To execute";
        String newStatus = "Executed";

        filterClaimPage.changeInProgress(oldStatus, newStatus);
    }
}
