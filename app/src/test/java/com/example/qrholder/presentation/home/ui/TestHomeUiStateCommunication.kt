package com.example.qrholder.presentation.home.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.qrholder.presentation.home.HomeUiStateCommunication
import com.example.qrholder.presentation.home.model.HomeUi

class TestHomeUiStateCommunication : HomeUiStateCommunication {

    var homeUiCalledList = mutableListOf<HomeUi>()

    override fun observe(owner: LifecycleOwner, observer: Observer<HomeUi>) = Unit

    override fun map(source: HomeUi) {
        homeUiCalledList.add(source)
    }

}