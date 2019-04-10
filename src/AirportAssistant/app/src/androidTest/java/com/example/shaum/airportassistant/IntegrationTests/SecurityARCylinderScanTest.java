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

public class SecurityARCylinderScanTest {

    @Rule
    public ActivityTestRule<SecurityARCylinderScan> mActivity = new ActivityTestRule<SecurityARCylinderScan>(SecurityARCylinderScan.class);

    public SecurityARCylinderScan securityARCylinderScan;

    @Before
    public void setUp() throws Exception {
        securityARCylinderScan = mActivity.getActivity();
    }

    @Test
    public void testStart() {
        assertNotNull(securityARCylinderScan.btComplete);
        assertNotNull(securityARCylinderScan.btQuit);
        assertNotNull(securityARCylinderScan.btClearPoints);
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
        securityARCylinderScan = null;
    }
}