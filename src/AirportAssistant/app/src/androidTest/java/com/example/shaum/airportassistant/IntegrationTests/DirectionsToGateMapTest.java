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

public class DirectionsToGateMapTest {

    @Rule
    public ActivityTestRule<DirectionsToGateMap> mActivity = new ActivityTestRule<DirectionsToGateMap>(DirectionsToGateMap.class);

    public DirectionsToGateMap directionsToGateMap;

    @Before
    public void setUp() throws Exception {
        directionsToGateMap = mActivity.getActivity();
    }

    @Test
    public void testStart() {
        assertNotNull(directionsToGateMap.btProgress);
    }

    @Test
    public void progressButtonClick() {
        onView(withId(R.id.btProgress)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        directionsToGateMap = null;
    }
}