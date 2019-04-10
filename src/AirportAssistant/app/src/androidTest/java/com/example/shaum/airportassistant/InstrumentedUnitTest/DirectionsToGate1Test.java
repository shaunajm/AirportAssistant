package com.example.shaum.airportassistant;

import android.support.test.rule.ActivityTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class DirectionsToGate1Test {

    @Rule
    public ActivityTestRule<DirectionsToGate1> mActivity = new ActivityTestRule<DirectionsToGate1>(DirectionsToGate1.class);

    public DirectionsToGate1 directionsToGate1;

    @Before
    public void setUp() throws Exception {
        directionsToGate1 = mActivity.getActivity();
    }

    @Test
    public void testStart() {
        assertNotNull(directionsToGate1.btProgress);
    }

    @Test
    public void enterGateInfo() {
        onView(withId(R.id.gateNumberEnter)).perform(typeText("408"));
        closeSoftKeyboard();
        onView(withId(R.id.btProgress)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        directionsToGate1 = null;
    }
}