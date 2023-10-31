package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotFocusable;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.utils.EspressoBaseTest;
import ru.iteco.fmhandroid.ui.utils.EspressoHelper;

public class ClaimPage extends EspressoBaseTest {

    String time = getCurrentTime();

    public void minimize() {
        elementWaiting(withId(R.id.main_menu_image_button), 1000);
        ViewInteraction materialButton = onView(allOf(withId(R.id.expand_material_button),
                childAtPosition(childAtPosition(
                        withId(R.id.container_list_claim_include_on_fragment_main), 0), 3)));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());
        onView(allOf(withId(R.id.claim_list_recycler_view ), isNotFocusable()));
        materialButton.perform(click());
        checkByIdNoWait(R.id.claim_list_recycler_view);
    }

    public void openDetailedClaim() {
        elementWaiting(withId(R.id.all_claims_text_view), 1000);

        clickButton(R.id.all_claims_text_view);
        clickRecyclerView(R.id.claim_list_recycler_view, 0);

        checkById(R.id.title_label_text_view);
        checkByIdNoWait(R.id.executor_name_label_text_view);
        checkByIdNoWait(R.id.plane_date_label_text_view);
        checkByIdNoWait(R.id.status_label_text_view);
        checkByIdNoWait(R.id.author_label_text_view);
        checkByIdNoWait(R.id.create_data_text_view);
        checkByIdNoWait(R.id.description_material_card_view);
    }

    public void createClaim(String title, String date) {
        String description = "description";
        elementWaiting(withId(R.id.main_menu_image_button), 5000);
        checkById(R.id.add_new_claim_material_button);
        clickButton(R.id.add_new_claim_material_button);
        elementWaiting(withId(R.id.title_edit_text), 10000);
        inputText(R.id.title_edit_text, title);
        inputText(R.id.date_in_plan_text_input_edit_text, date);
        inputText(R.id.time_in_plan_text_input_edit_text, time);
        inputText(R.id.description_edit_text, description);
        scrollAndClickButton(R.id.save_button);
    }

    public void created(String title) {
        elementWaiting(withId(R.id.claim_list_recycler_view), 5000);
        findRecyclerView(R.id.claim_list_recycler_view, title);
    }

    public void addComment() {
        String generatedString = DataHelper.generateString();
        elementWaiting(withId(R.id.claim_list_recycler_view), 3000);
/*        clickRecyclerView(R.id.claim_list_recycler_view, 0);
        scrollAndClickButton(R.id.add_comment_image_button);*/
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.claim_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_claims_cards_block_constraint_layout),
                                4)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.add_comment_image_button), withContentDescription("button add comment"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.comments_material_card_view),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction textInputEditText3 = onView(
                allOf(childAtPosition(
                                childAtPosition(
                                        withId(R.id.comment_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText3.perform(replaceText(generatedString), closeSoftKeyboard());
        scrollAndClickButton(R.id.save_button);
        checkText(R.id.comment_description_text_view, R.id.claim_comments_list_recycler_view, generatedString);
    }

    public void displayToastForEmptyField () {
        onView(withText(R.string.toast_empty_field)).inRoot(new EspressoHelper.ToastMatcher())
                .check(matches(withText("The field cannot be empty.")));
    }

    public void displayToastFillEmpty() {
        onView(withText(R.string.empty_fields)).check(matches(withText("Fill empty fields")));
    }

    public void clickAddCommentInputText(String text) {
        elementWaiting(withId(R.id.claim_comments_list_recycler_view), 8000);
        clickButton(R.id.add_comment_image_button);
        ViewInteraction textInputEditText = onView(allOf(childAtPosition(childAtPosition(
                withId(R.id.comment_text_input_layout), 0), 1)));
        textInputEditText.perform(replaceText(text), closeSoftKeyboard());
        scrollAndClickButton(R.id.save_button);
    }

    public void editComment() {
        String comment = DataHelper.generateString();
        clickAllOfChild(R.id.claim_list_recycler_view, R.id.all_claims_cards_block_constraint_layout, 4, 0);

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.edit_comment_image_button), withContentDescription("button edit comment"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.claim_comments_list_recycler_view),
                                        0),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction textInputEditText5 = onView(
                allOf(childAtPosition(
                                childAtPosition(
                                        withId(R.id.comment_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText5.perform(replaceText(comment));

        ViewInteraction textInputEditText6 = onView(
                allOf(withText(comment),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.comment_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText6.perform(closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.save_button), withText("Save"), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                1)));
        materialButton4.perform(scrollTo(), click());
        elementWaiting(withId(R.id.add_comment_image_button), 3000);
        onView(withId(R.id.claim_comments_list_recycler_view))
                .check(matches(hasDescendant(allOf(
                        withId(R.id.comment_description_text_view),
                        withText(comment)))));
    }
}
