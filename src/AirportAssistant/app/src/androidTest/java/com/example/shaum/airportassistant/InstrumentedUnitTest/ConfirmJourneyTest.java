package com.example.shaum.airportassistant;

import android.support.test.rule.ActivityTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class ConfirmJourneyTest {

    @Rule
    public ActivityTestRule<ConfirmJourney> mActivity = new ActivityTestRule<ConfirmJourney>(ConfirmJourney.class);

    public ConfirmJourney confirmJourney;

    @Before
    public void setUp() throws Exception {
        confirmJourney = mActivity.getActivity();
    }

    @Test
    public void testStart() {
        assertNotNull(confirmJourney.btstartJourney);
        assertNotNull(confirmJourney.btCancel);
    }

    @Test
    public void startButton() {
        onView(withId(R.id.btstartJourney)).perform(click());
    }

    @Test
    public void cancelButton() {
        onView(withId(R.id.btCancel)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        confirmJourney = null;
    }
}