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

public class ARStartTest {

    @Rule
    public ActivityTestRule<ARStart> mActivity = new ActivityTestRule<ARStart>(ARStart.class);

    public ARStart arStart;

    @Before
    public void setUp() throws Exception {
        arStart = mActivity.getActivity();
    }

    @Test
    public void testStart() {
        assertNotNull(arStart.btProgressScan);
        assertNotNull(arStart.btSkipStage);
    }

    @Test
    public void progressScan() {
        onView(withId(R.id.btProgressScan)).perform(click());
    }

    @Test
    public void skipStage() {
        onView(withId(R.id.btSkipStage)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        arStart = null;
    }
}