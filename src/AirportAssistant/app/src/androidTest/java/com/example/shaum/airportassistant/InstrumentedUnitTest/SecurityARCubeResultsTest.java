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

public class SecurityARCubeResultsTest {

    @Rule
    public ActivityTestRule<SecurityARCubeResults> mActivity = new ActivityTestRule<SecurityARCubeResults>(SecurityARCubeResults.class);

    public SecurityARCubeResults securityARCubeResults;

    @Before
    public void setUp() throws Exception {
        securityARCubeResults = mActivity.getActivity();
    }

    @Test
    public void testStart() {
        assertNotNull(securityARCubeResults.btProgress);
        assertNotNull(securityARCubeResults.btRepeatScan);
    }

    @Test
    public void progressClick() {
        onView(withId(R.id.btProgress)).perform(click());
    }

    @Test
    public void repeatClick() {
        onView(withId(R.id.btRepeatScan)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        securityARCubeResults = null;
    }
}