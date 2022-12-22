package com.example.qrholder.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.qrholder.presentation.home.HomeUiStateCommunication

abstract class BaseMainViewModelTest {

    class TestFabStateCommunication : HomeUiStateCommunication {

        var fabStateCalledList = mutableListOf<MainFabState>()

        override fun observe(owner: LifecycleOwner, observer: Observer<MainFabState>) = Unit

        override fun map(source: MainFabState) {
            fabStateCalledList.add(source)
        }

    }

}