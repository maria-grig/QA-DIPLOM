package ru.iteco.fmhandroid.ui.tests;


import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
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
    @DisplayName("#9 Просмотр/Скрытие заявок")
    public void shouldMinimizeClaimsList() {
        claimPage.minimize();
    }

    @Test
    @DisplayName("#10 Просмотр заявки из списка Все заявки")
    public void shouldOpenDetailedClaim() {
        claimPage.openDetailedClaim();
    }

    @Test
    @DisplayName("#11 Создание заявки на главном экране")
    public void shouldCreateClaimOnMainScreen() {
        String title = "title";
        String date = getCurrentDate();
        claimPage.createClaim(title, date);
        claimPage.created(title);
    }

    @Test
    @DisplayName("#12 Ограничение по символам поля Тема на странице Заявки")
    public void shouldNotCreateTooLongClaimTitle() {
        String tooLongTitle = "Этот заголовок длинее 50 символов должен быть обрезан";
        String title = tooLongTitle.substring(0, 50);
        String date = getCurrentDate();
        claimPage.createClaim(title, date);
        claimPage.created(title);
    }

    @Test
    @DisplayName("#13 Создание заявки без даты на странице Заявки")
    public void shouldNotCreateClaimWithoutDate() {
        String title = "title";
        String date = "";
        claimPage.createClaim(title, date);
        claimPage.displayToastFillEmpty();
    }

    @Test
    @DisplayName("#19 Добавление комментария к заявке")
    public void shouldAddComment() {
        claimPage.addComment();
    }

    @Test
    @DisplayName("#20 Изменение комментария к заявке")
    public void shouldEditComment() {
        claimPage.editComment();

    }
    @Test
    @DisplayName("#21 Недоступность сохранения пустого комментария")
    public void shouldNotAddEmptyComment() {
        tapHamburger(menu_item);
        detailedClaimPage.detailedClaim();
        claimPage.clickAddCommentInputText("");
        claimPage.displayToastForEmptyField();
    }
}
