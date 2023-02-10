package com.alezzgo.qrholder.home.data.cache.db

import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alezzgo.qrholder.R
import com.alezzgo.qrholder.home.data.cache.db.pages.HomePage
import org.hamcrest.Matchers.not
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BottomNavViewVisibilityTest : BaseAndroidTest() {

    val homePage = HomePage()

    @Test
    fun test_bottom_nav_bar_visibility() {
        onView(withId(R.id.navigation_menu)).click()
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()))

        changeOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        onView(withId(R.id.nav_view)).check(matches(isDisplayed()))

        onView(withId(R.id.navigation_home)).click()

        homePage.run {
            fabParent.click()
            fabBuild.click()
        }

        onView(withId(R.id.nav_view)).check(matches(not(isDisplayed())))

        changeOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        onView(withId(R.id.nav_view)).check(matches(not(isDisplayed())))
    }


}