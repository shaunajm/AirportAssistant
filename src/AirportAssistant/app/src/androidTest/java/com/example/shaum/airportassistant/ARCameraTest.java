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

public class ARCameraTest {

    @Rule
    public ActivityTestRule<ARCamera> mActivity = new ActivityTestRule<ARCamera>(ARCamera.class);

    public ARCamera arCamera;

    @Before
    public void setUp() throws Exception {
        arCamera = mActivity.getActivity();
    }

    @Test
    public void testStart() {
        assertNotNull(arCamera.btComplete);
        assertNotNull(arCamera.btQuit);
    }

    @Test
    public void completeButtonClick() {
        onView(withId(R.id.btCompleteScan)).perform(click());
    }

    @Test
    public void quitButton() {
        onView(withId(R.id.btQuitScan)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        arCamera = null;
    }
}