package com.android.example.cookieclicker;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class CookieClickerTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void totalStartsAtZero() throws Exception {
        onView(withId(R.id.lblTotal))
                .check(matches(withText("0")));
    }

    @Test
    public void totalIncreasesWhenCookieClicked() throws Exception {
        onView(withId(R.id.imgCookie))
                .perform(click());

        onView(withId(R.id.lblTotal))
                .check(matches(withText("1")));
    }
    @Test
    public void achieveHighScore() throws Exception {
        for (int i = 0; i < 20; i++) {
            onView(withId(R.id.imgCookie))
                    .perform(click());
        }

        onView(withId(R.id.lblTotal))
                .check(matches(withText("20")));
    }

    @Test
    public void submitButton() throws Exception {
            onView(withId(R.id.submit_button))
                    .perform(click());

        onView(withId(R.id.score_board))
                .check(matches(withText("Score:  [0]")));
    }

    @Test
    public void submitScore() throws Exception {
        for (int i = 0; i < 10; i++) {
            onView(withId(R.id.imgCookie))
                    .perform(click());
        }

        onView(withId(R.id.submit_button))
                .perform(click());

        onView(withId(R.id.score_board))
                .check(matches(withText("Score:  [10]")));
    }

    @Test
    public void submitNameScore() throws Exception {
        onView(withId(R.id.usrName))
                .perform(replaceText("Bob"));

        for (int i = 0; i < 10; i++) {
            onView(withId(R.id.imgCookie))
                    .perform(click());
        }

        onView(withId(R.id.submit_button))
                .perform(click());

        onView(withId(R.id.score_board))
                .check(matches(withText("Score: Bob [10]")));
    }
}
