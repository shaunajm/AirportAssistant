package com.example.shaum.airportassistant;

import android.support.test.rule.ActivityTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;

public class JourneyUploadTest {

    @Rule
    public ActivityTestRule<JourneyUpload> mActivity = new ActivityTestRule<JourneyUpload>(JourneyUpload.class);

    public JourneyUpload journeyUpload;

    @Before
    public void setUp() throws Exception {
        journeyUpload = mActivity.getActivity();
    }

    @Test
    public void testStart() {
        assertNotNull(journeyUpload.btUploadJourney);
    }

    @Test
    public void enterJourneyInfo(){
        onView(withId(R.id.flightNumber)).perform(typeText("EI3394"));
        closeSoftKeyboard();
        onView(withId(R.id.btUploadJourney)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        journeyUpload= null;
    }
}