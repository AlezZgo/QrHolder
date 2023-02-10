package com.alezzgo.qrholder.presentation.main.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.alezzgo.qrholder.presentation.main.MainFabStateCommunication
import com.alezzgo.qrholder.presentation.main.MainFabUiState

class TestFabStateCommunication : MainFabStateCommunication {

    var fabStateCalledList = mutableListOf<MainFabUiState>()

    override fun observe(owner: LifecycleOwner, observer: Observer<MainFabUiState>) = Unit

    override fun map(source: MainFabUiState) {
        fabStateCalledList.add(source)
    }

}