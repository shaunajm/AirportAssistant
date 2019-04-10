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

public class SecurityARCylinderResultsTest {

    @Rule
    public ActivityTestRule<SecurityARCylinderResults> mActivity = new ActivityTestRule<SecurityARCylinderResults>(SecurityARCylinderResults.class);

    public SecurityARCylinderResults securityARCylinderResults;

    @Before
    public void setUp() throws Exception {
        securityARCylinderResults = mActivity.getActivity();
    }

    @Test
    public void testStart() {
        assertNotNull(securityARCylinderResults.btProgress);
        assertNotNull(securityARCylinderResults.btRepeatScan);
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
        securityARCylinderResults = mActivity.getActivity();
    }
}