package com.example.qrholder.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.qrholder.presentation.main.MainFabStateCommunication
import com.example.qrholder.presentation.main.MainFabUiState

abstract class BaseMainViewModelTest {

    class TestFabStateCommunication : MainFabStateCommunication {

        var fabStateCalledList = mutableListOf<MainFabUiState>()

        override fun observe(owner: LifecycleOwner, observer: Observer<MainFabUiState>) = Unit

        override fun map(source: MainFabUiState) {
            fabStateCalledList.add(source)
        }

    }

}