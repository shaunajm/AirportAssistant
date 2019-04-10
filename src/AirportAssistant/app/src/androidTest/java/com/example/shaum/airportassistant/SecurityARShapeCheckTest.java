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

public class SecurityARShapeCheckTest {

    @Rule
    public ActivityTestRule<SecurityARShapeCheck> mActivity = new ActivityTestRule<SecurityARShapeCheck>(SecurityARShapeCheck.class);

    public SecurityARShapeCheck securityARShapeCheck;

    @Before
    public void setUp() throws Exception {
        securityARShapeCheck = mActivity.getActivity();
    }

    @Test
    public void testStart() {
        assertNotNull(securityARShapeCheck.btCyclinder);
        assertNotNull(securityARShapeCheck.btCuboid);
    }

    @Test
    public void cylinderClick() {
        onView(withId(R.id.btCyclinder)).perform(click());
    }

    @Test
    public void cuboidClick() {
        onView(withId(R.id.btCuboid)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        securityARShapeCheck = null;
    }
}