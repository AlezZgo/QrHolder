package com.example.qrholder.presentation.home.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.qrholder.presentation.home.HomeUiStateCommunication
import com.example.qrholder.presentation.home.model.HomeUiState

class TestHomeUiStateCommunication : HomeUiStateCommunication {

    var homeUiStateCalledList = mutableListOf<HomeUiState>()

    override fun observe(owner: LifecycleOwner, observer: Observer<HomeUiState>) = Unit

    override fun map(source: HomeUiState) {
        homeUiStateCalledList.add(source)
    }

}