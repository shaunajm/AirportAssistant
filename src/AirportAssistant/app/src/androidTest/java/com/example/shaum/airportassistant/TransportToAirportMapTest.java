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

public class TransportToAirportMapTest {

    @Rule
    public ActivityTestRule<TransportToAirportMap> mActivity = new ActivityTestRule<TransportToAirportMap>(TransportToAirportMap.class);

    public TransportToAirportMap transportToAirportMap;

    @Before
    public void setUp() throws Exception {
        transportToAirportMap = mActivity.getActivity();
    }

    @Test
    public void testStart() {
        assertNotNull(transportToAirportMap.btProgress);
    }

    @Test
    public void progressButtonClick() {
        onView(withId(R.id.btProgress)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        transportToAirportMap = null;
    }
}