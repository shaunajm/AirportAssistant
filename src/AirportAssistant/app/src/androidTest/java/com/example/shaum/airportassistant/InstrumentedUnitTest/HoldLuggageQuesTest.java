package com.example.shaum.airportassistant;

import android.support.test.rule.ActivityTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;

public class HoldLuggageQuesTest {

    @Rule
    public ActivityTestRule<HoldLuggageQues> mActivity = new ActivityTestRule<HoldLuggageQues>(HoldLuggageQues.class);

    public HoldLuggageQues holdLuggageQues;

    @Before
    public void setUp() throws Exception {
        holdLuggageQues = mActivity.getActivity();
    }

    @Test
    public void testStart() {
        assertNotNull(holdLuggageQues.btBeginJourney);
    }

    @Test
    public void enterJourneyInfo() {
        onView(withId(R.id.bookedWeightNumber)).perform(typeText("20"));
        onView(withId(R.id.actualWeightNumber)).perform(typeText("18"));
        onView(withId(R.id.btBeginJourney)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        holdLuggageQues = null;
    }
}