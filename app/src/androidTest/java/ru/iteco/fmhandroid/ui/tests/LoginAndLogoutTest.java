package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import ru.iteco.fmhandroid.ui.pages.Logged;
import ru.iteco.fmhandroid.ui.pages.LoginPage;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)

public class LoginAndLogoutTest extends LoginPage {
    LoginPage loginPage = new LoginPage();
    Logged logged = new Logged();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void checkLogout() {
        try {
            logged.loggedOut();
        } catch (Exception e) {
            loginPage.logout();
            logged.loggedOut();
        }
    }

    @Test
    public void validLoginAndPassword() {
        login();
        checkById(R.id.authorization_image_button);
    }

    @Test
    public void emptyLoginAndValidPassword() {
        emptyLogin();
        checkByString(R.string.empty_login_or_password, "Login and password cannot be empty");
    }

    @Test
    public void validLoginAndEmptyPassword() {
        emptyPassword();
        checkByString(R.string.empty_login_or_password, "Login and password cannot be empty");
    }

    @Test
    public void validLoginAndInvalidPassword() {
        invalidPassword();
        checkByString(R.string.wrong_login_or_password, "Wrong login or password");
    }

    @Test
    public void invalidLoginAndValidPassword() {
        invalidLogin();
        checkByString(R.string.wrong_login_or_password, "Wrong login or password");
    }

    @Test
    public void shouldLogout() {
        login();
        logout();
    }
}
