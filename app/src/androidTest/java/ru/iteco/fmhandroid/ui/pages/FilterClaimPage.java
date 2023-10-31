package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.EspressoBaseTest;

public class FilterClaimPage extends EspressoBaseTest {

    public void changeOpen(String text, String oldStatus, String newStatus) {
        filterScreen(R.id.item_filter_in_progress);
        checkStatusClaimRecycler(text);
        changeOpenStatus(oldStatus, newStatus);
    }

    public void changeInProgress(String oldStatus, String newStatus) {
        filterScreen(R.id.item_filter_open);
        changeInProgressStatus(oldStatus, newStatus);
    }

    public void filterScreen(Integer resourceId) {
        elementWaiting(withId(R.id.filters_material_button), 5000);
        clickFilterButton();
        elementWaiting(withId(resourceId), 3000);
        scrollAndClickButton(resourceId);
        scrollAndClickButton(R.id.claim_list_filter_ok_material_button);
    }

    public void checkStatusClaimRecycler(String text) {
        elementWaiting(withId(R.id.claim_list_recycler_view), 5000);
        clickRecyclerView(R.id.claim_list_recycler_view, 0);
        checkByIdWithText(R.id.status_label_text_view, text);
    }

    public void changeOpenStatus(String oldStatus, String newStatus) {
        elementWaiting(withId(R.id.claim_comments_list_recycler_view), 5000);
        nestedScrollAndClickButton(R.id.status_processing_image_button);
        onView(withText(oldStatus)).perform(click());
        checkByIdWithText(R.id.status_label_text_view, newStatus);
    }

    public void changeInProgressStatus(String oldStatus, String newStatus) {
        elementWaiting(withId(R.id.claim_list_recycler_view), 3000);
        ViewInteraction recyclerView = onView(allOf(withId(R.id.claim_list_recycler_view)));
        recyclerView.check(matches(isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        ViewInteraction textView = onView(allOf(withId(R.id.status_label_text_view), withText("In progress")));
        elementWaiting(withId(R.id.status_label_text_view), 3000);
        textView.check(matches(withText("In progress")));

        nestedScrollAndClickButton(R.id.status_processing_image_button);

        onView(withText(oldStatus)).perform(click());
        ViewInteraction textInputEditText = onView(allOf(withId(R.id.editText)));
        textInputEditText.check(matches(isDisplayed()));
        textInputEditText.perform(replaceText("статус сброшен"), closeSoftKeyboard());
        ViewInteraction textView_ = onView(allOf(withId(R.id.status_label_text_view), withText(newStatus)));
        ViewInteraction materialButton = onView(allOf(withId(android.R.id.button1), withText("OK")));
        materialButton.perform(scrollTo(), click());
        elementWaiting(withId(R.id.status_label_text_view), 1000);
        textView_.check(matches(withText(newStatus)));
    }
}
