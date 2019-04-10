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

public class TransportToAirport1Test {

    @Rule
    public ActivityTestRule<TransportToAirport1> mActivity = new ActivityTestRule<TransportToAirport1>(TransportToAirport1.class);

    public TransportToAirport1 transportToAirport1;

    @Before
    public void setUp() throws Exception {
        transportToAirport1 = mActivity.getActivity();
    }

    @Test
    public void testStart() {
        assertNotNull(transportToAirport1.btProgress);
    }

    @Test
    public void progressButtonClick() {
        onView(withId(R.id.btProgress)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        transportToAirport1 = null;
    }
}