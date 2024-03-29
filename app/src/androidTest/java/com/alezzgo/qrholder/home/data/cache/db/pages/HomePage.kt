package com.alezzgo.qrholder.home.data.cache.db.pages

import com.alezzgo.qrholder.R
import com.alezzgo.qrholder.home.data.cache.db.core.Page

class HomePage : Page() {

    val appBarSearch = R.id.appBarSearch.view()

    val recycler = R.id.rvQrList.view()

    val ivNothingWasFound = R.id.ivNothingWasFound.view()
    val fabParent = R.id.fabParent.view()
    val fabBuild = R.id.fabBuild.view()
    val fabGallery = R.id.fabGallery.view()
    val fabScan = R.id.fabScan.view()

    val recyclerId = R.id.rvQrList
    val recyclerTitleId = R.id.tvTitle
    val recyclerContentId = R.id.tvContent
}