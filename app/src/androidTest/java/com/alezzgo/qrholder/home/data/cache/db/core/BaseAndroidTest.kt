package com.alezzgo.qrholder.home.data.cache.db.core


import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alezzgo.qrholder.presentation.main.MainActivity
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class BaseAndroidTest : DatabaseForTest() {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    protected fun ViewInteraction.typeText(value: String) {
        perform(ViewActions.typeText(value))
        closeSoftKeyboard()
    }

    protected fun ViewInteraction.checkText(value: String) {
        check(matches(withText(value)))
    }

    protected fun ViewInteraction.checkIsDisplayed() {
        check(matches(ViewMatchers.isDisplayed()))
    }

    protected fun ViewInteraction.click() {
        perform(ViewActions.click())
    }

    protected fun Int.viewInRecycler(position: Int, viewId: Int): ViewInteraction =
        onView(RecyclerViewMatcher(this).atPosition(position, viewId))

    protected fun changeOrientation(screenOrientation: Int) {
        activityScenarioRule.scenario.onActivity {
            it.requestedOrientation = screenOrientation
        }
    }
}