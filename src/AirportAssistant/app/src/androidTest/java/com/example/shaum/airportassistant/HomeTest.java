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

public class HomeTest {

    @Rule
    public ActivityTestRule<Home> mActivity = new ActivityTestRule<Home>(Home.class);

    public Home home;

    @Before
    public void setUp() throws Exception {
        home = mActivity.getActivity();
    }

    @Test
    public void testStart() {
        assertNotNull(home.btUploadJourney);
    }

    @Test
    public void uploadJourneyButtonClick() {
        onView(withId(R.id.btUploadJourney)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        home = null;
    }
}