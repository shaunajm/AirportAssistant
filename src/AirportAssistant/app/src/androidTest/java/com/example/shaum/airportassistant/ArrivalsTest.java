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

public class ArrivalsTest {

    @Rule
    public ActivityTestRule<Arrivals> mActivity = new ActivityTestRule<Arrivals>(Arrivals.class);

    public Arrivals arrivals;

    @Before
    public void setUp() throws Exception {
        arrivals = mActivity.getActivity();
    }

    @Test
    public void testStart() {
        assertNotNull(arrivals.btProgress);
    }

    @Test
    public void progressButtonClick() {
        onView(withId(R.id.btProgress)).perform(click());
    }

    @Test
    public void enterDestinationInfo() {
        onView(withId(R.id.address)).perform(typeText("Big Ben"));
        closeSoftKeyboard();
        onView(withId(R.id.btProgress)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        arrivals = null;
    }
}