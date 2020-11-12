package com.bignerdranch.beatbox

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.anything
import org.junit.Rule
import org.junit.Test


class BeatBoxActivityTest {
    @Rule @JvmField
    val mActivityRule = ActivityScenarioRule(BeatBoxActivity::class.java)

    @Test
    fun checkLabelText() {
        onView(withText("65_cjipie"))
            .check(matches(anything()))
    }
}