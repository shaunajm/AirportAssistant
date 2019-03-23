package com.example.shaum.airportassistant;

import android.support.test.rule.ActivityTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivity = new ActivityTestRule<MainActivity>(MainActivity.class);

    public MainActivity mainActivity;

    @Before
    public void setUp() throws Exception {
        mainActivity = mActivity.getActivity();
    }

    @Test
    public void testStart() {
        assertNotNull(mainActivity.mGoogleSignInClient);
    }


    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}