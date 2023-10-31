package ru.iteco.fmhandroid.ui.tests;


import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.ClaimPage;
import ru.iteco.fmhandroid.ui.pages.DetailedClaimPage;
import ru.iteco.fmhandroid.ui.pages.Logged;
import ru.iteco.fmhandroid.ui.pages.LoginPage;
import ru.iteco.fmhandroid.ui.utils.EspressoBaseTest;

@RunWith(AllureAndroidJUnit4.class)
public class ClaimsTest extends EspressoBaseTest {
    Logged logged = new Logged();
    LoginPage loginPage = new LoginPage();
    ClaimPage claimPage = new ClaimPage();
    DetailedClaimPage detailedClaimPage = new DetailedClaimPage();



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

    String menu_item = "Claims";

    @Test
    public void shouldMinimizeClaimsList() {
        claimPage.minimize();
    }

    @Test
    public void shouldOpenDetailedClaim() {
        claimPage.openDetailedClaim();
    }

    @Test
    public void shouldCreateClaimOnMainScreen() {
        String title = "title";
        String date = getCurrentDate();
        claimPage.createClaim(title, date);
        claimPage.created(title);
    }

    @Test
    public void shouldNotCreateTooLongClaimTitle() {
        String tooLongTitle = "Этот заголовок длинее 50 символов должен быть обрезан";
        String title = tooLongTitle.substring(0, 50);
        String date = getCurrentDate();
        claimPage.createClaim(title, date);
        claimPage.created(title);
    }

    @Test
    public void shouldNotCreateClaimWithoutDate() {
        String title = "title";
        String date = "";
        claimPage.createClaim(title, date);
        claimPage.displayToastFillEmpty();
    }

    @Test
    public void shouldAddComment() {
        claimPage.addComment();
    }

    @Test
    public void shouldEditComment() {
        claimPage.editComment();

    }
    @Test
    public void shouldNotAddEmptyComment() {
        tapHamburger(menu_item);
        detailedClaimPage.detailedClaim();
        claimPage.clickAddCommentInputText("");
        claimPage.displayToastForEmptyField();
    }
}
