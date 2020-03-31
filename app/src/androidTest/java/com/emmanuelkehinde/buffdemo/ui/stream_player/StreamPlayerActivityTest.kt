package com.emmanuelkehinde.buffdemo.ui.stream_player


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.emmanuelkehinde.buffdemo.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class StreamPlayerActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(StreamPlayerActivity::class.java)

    @Test
    fun videoPlayerViewIsDisplayed() {
        val playerView = onView(withId(R.id.playerView))
        playerView.check(matches(isDisplayed()))
    }

    @Test
    fun buffViewIsAttached() {
        val buffView = onView(withId(R.id.buffView))
        buffView.check(matches(isEnabled()))
    }

}
