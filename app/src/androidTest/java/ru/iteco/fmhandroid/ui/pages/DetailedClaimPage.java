package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.matcher.ViewMatchers.withId;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.EspressoBaseTest;

public class DetailedClaimPage extends EspressoBaseTest {

    public void detailedClaim(){
        elementWaiting(withId(R.id.claim_list_recycler_view), 10000);
        clickRecyclerView(R.id.claim_list_recycler_view, 3);
        checkById(R.id.status_label_text_view);
    }
}
