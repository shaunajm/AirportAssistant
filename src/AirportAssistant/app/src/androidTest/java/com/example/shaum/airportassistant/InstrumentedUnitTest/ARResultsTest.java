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

public class ARResultsTest {



    @Rule
    public ActivityTestRule<ARResults> mActivity = new ActivityTestRule<ARResults>(ARResults.class);

    public ARResults arResults;

    @Before
    public void setUp() throws Exception {
        arResults = mActivity.getActivity();
    }

    @Test
    public void testStart() {
        assertNotNull(arResults.btProgress);
        assertNotNull(arResults.btRepeatScan);
    }

    @Test
    public void progressButtonClick() {
        onView(withId(R.id.btProgress)).perform(click());
    }

    @Test
    public void repeatButton() {
        onView(withId(R.id.btRepeatScan)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        arResults = null;
    }
}