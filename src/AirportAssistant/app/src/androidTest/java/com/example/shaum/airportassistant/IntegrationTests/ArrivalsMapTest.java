package com.example.shaum.airportassistant.IntegrationTests;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import com.example.shaum.airportassistant.ArrivalsMap;
import com.example.shaum.airportassistant.R;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static org.junit.Assert.assertNotNull;

public class ArrivalsMapTest {

    @Rule
    public ActivityTestRule<ArrivalsMap> mActivity = new ActivityTestRule<ArrivalsMap>(ArrivalsMap.class);

    public ArrivalsMap arrivalsMap;

    @Before
    public void setUp() throws Exception {
        arrivalsMap = mActivity.getActivity();
    }

    @Test
    public void testStart() {
        assertNotNull(arrivalsMap.btProgress);
    }

    @Test
    public void progressButtonClick() {
        onView(ViewMatchers.withId(R.id.btProgress)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        arrivalsMap = null;
    }
}