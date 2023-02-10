package com.alezzgo.qrholder.home.data.cache.db

import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alezzgo.qrholder.R
import com.alezzgo.qrholder.home.data.cache.db.pages.BuildQrCodePage
import com.alezzgo.qrholder.home.data.cache.db.pages.HomePage
import com.alezzgo.qrholder.home.data.cache.db.pages.SuccessfullyBuildPage
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BuildQrCodeTest : com.alezzgo.qrholder.home.data.cache.db.BaseAndroidTest() {

    val homePage = HomePage()
    val buildQrCodePage = BuildQrCodePage()
    val successfullyBuildPage = SuccessfullyBuildPage()

    val title1 = "New Qr Code Title" + System.currentTimeMillis().toString()
    val content1 = "New Qr Code Content" + System.currentTimeMillis()

    val title2 = "New Qr Code Title2" + System.currentTimeMillis().toString()
    val content2 = "New Qr Code Content2" + System.currentTimeMillis().toString()

    val title3 = "New Qr Code Title3" + System.currentTimeMillis().toString()
    val content3 = "New Qr Code Content3" + System.currentTimeMillis().toString()

    @Test
    fun buildQrCodesThenFilterThem() {

        homePage.run {

            createQrCode(title1, content1)

            recycler.swipeDown()

            checkRvItem(0, title1, content1)

            createQrCode(title2, content2)
            createQrCode(title3, content3)

            recycler.swipeDown()

            checkRvItem(2, title1, content1)
            checkRvItem(1, title2, content2)
            checkRvItem(0, title3, content3)

            appBarSearch.typeText(title1)

            checkRvItem(0, title1, content1)

            recycler.checkRecyclerItemsIsEquals(1)

            appBarSearch.typeText("h")

            ivNothingWasFound.isDisplayed()

            recycler.checkRecyclerItemsIsEquals(0)
        }

    }


    private fun createQrCode(title: String, content: String) {
        //home fragment
        homePage.run {
            fabParent.click()
            fabBuild.click()
        }

        buildQrCodePage.run {
            inputTitle.typeText(title)
            inputContent.typeText(content)
            btnBuild.click()
        }

        successfullyBuildPage.run {
            tvTitle.checkText(title)
            tvContent.checkText(content)
            btnOk.click()
        }

    }

    private fun checkRvItem(position: Int, title: String, content: String) {
        homePage.recyclerId.run {
            viewInRecycler(position, R.id.tvTitle).checkText(title)
            viewInRecycler(position, R.id.tvContent).checkText(content)
        }
    }


}