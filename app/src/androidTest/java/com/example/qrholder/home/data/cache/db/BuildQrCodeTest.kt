package com.example.qrholder.home.data.cache.db


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.qrholder.R
import com.example.qrholder.presentation.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BuildQrCodeTest : DatabaseForTest() {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun buildQrCodesThenFilterThem() {

        val title1 =
            "New Qr Code Title" + System.currentTimeMillis().toString() + System.currentTimeMillis()
                .toString()
        val content1 = "New Qr Code Content" + System.currentTimeMillis()
            .toString() + System.currentTimeMillis().toString()

        val title2 = "New Qr Code Title2" + System.currentTimeMillis()
            .toString() + System.currentTimeMillis().toString()
        val content2 = "New Qr Code Content2" + System.currentTimeMillis()
            .toString() + System.currentTimeMillis().toString()

        val title3 = "New Qr Code Title3" + System.currentTimeMillis()
            .toString() + System.currentTimeMillis().toString()
        val content3 = "New Qr Code Content3" + System.currentTimeMillis()
            .toString() + System.currentTimeMillis().toString()

        createQrCode(title1, content1)

        onView(withId(R.id.rvQrList)).perform(swipeDown())

        //home fragment
        checkRvItem(0, title1, content1)

        createQrCode(title2, content2)
        createQrCode(title3, content3)

        onView(withId(R.id.rvQrList)).perform(swipeDown())

        //home fragment
        checkRvItem(2, title1, content1)
        checkRvItem(1, title2, content2)
        checkRvItem(0, title3, content3)

        onView(withId(R.id.app_bar_search)).perform(typeText(title1))

        checkRvItem(0, title1, content1)

        onView(withId(R.id.rvQrList)).check { view, noViewFoundException ->
            noViewFoundException?.apply {
                throw this
            }
            view is RecyclerView && view.adapter != null && (view.adapter?.itemCount ?: -1) > 0
        }

        onView(withId(R.id.app_bar_search)).perform(typeText("h"))

        onView(withId(R.id.ivNothingWasFound)).check(matches(isDisplayed()))

    }


    private fun createQrCode(title: String, content: String) {
        //home fragment
        onView(withId(R.id.fabParent)).perform(click())
        onView(withId(R.id.fabBuild)).perform(click())

        //build qr code fragment
        onView(withId(R.id.tielTitle)).perform(typeText(title))
        onView(withId(R.id.tielContent)).perform(typeText(content))

        onView(withId(R.id.btnBuild)).perform(click())

        //successfully build fragment
        onView(withId(R.id.tvTitle)).check(matches(withText(title)))
        onView(withId(R.id.tvContent)).check(matches(withText(content)))

        onView(withId(R.id.btnOk)).perform(click())
    }

    private fun checkRvItem(position: Int, title: String, content: String) {
        onView(RecyclerViewMatcher(R.id.rvQrList).atPosition(position, R.id.tvTitle)).check(
            matches(
                withText(title)
            )
        )
        onView(RecyclerViewMatcher(R.id.rvQrList).atPosition(position, R.id.tvContent)).check(
            matches(withText(content))
        )
    }


}