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

public class SecurityARCubeScanTest {

    @Rule
    public ActivityTestRule<SecurityARCubeScan> mActivity = new ActivityTestRule<SecurityARCubeScan>(SecurityARCubeScan.class);

    public SecurityARCubeScan securityARCubeScan;

    @Before
    public void setUp() throws Exception {
        securityARCubeScan = mActivity.getActivity();
    }

    @Test
    public void testStart() {
        assertNotNull(securityARCubeScan.btComplete);
        assertNotNull(securityARCubeScan.btQuit);
        assertNotNull(securityARCubeScan.btClearPoints);
    }

    @Test
    public void completeClick() {
        onView(withId(R.id.btCompleteScan)).perform(click());
    }

    @Test
    public void quitClick() {
        onView(withId(R.id.btQuitScan)).perform(click());
    }

    @Test
    public void clearPointsClick() {
        onView(withId(R.id.btClearPoints)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        securityARCubeScan = null;
    }
}