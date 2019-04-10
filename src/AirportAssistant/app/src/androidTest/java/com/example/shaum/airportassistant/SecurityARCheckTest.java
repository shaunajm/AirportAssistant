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

public class SecurityARCheckTest {

    @Rule
    public ActivityTestRule<SecurityARCheck> mActivity = new ActivityTestRule<SecurityARCheck>(SecurityARCheck.class);

    public SecurityARCheck securityARCheck;

    @Before
    public void setUp() throws Exception {
        securityARCheck = mActivity.getActivity();
    }

    @Test
    public void testStart() {
        assertNotNull(securityARCheck.btProgressScan);
        assertNotNull(securityARCheck.btSkipStage);
    }

    @Test
    public void progressClick() {
        onView(withId(R.id.btProgressScan)).perform(click());
    }

    @Test
    public void skipClick() {
        onView(withId(R.id.btSkipStage)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        securityARCheck = null;
    }
}