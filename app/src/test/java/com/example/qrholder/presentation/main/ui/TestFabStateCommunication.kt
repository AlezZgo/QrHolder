package com.example.qrholder.presentation.main.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.qrholder.presentation.main.MainFabStateCommunication
import com.example.qrholder.presentation.main.MainFabUiState

class TestFabStateCommunication : MainFabStateCommunication {

    var fabStateCalledList = mutableListOf<MainFabUiState>()

    override fun observe(owner: LifecycleOwner, observer: Observer<MainFabUiState>) = Unit

    override fun map(source: MainFabUiState) {
        fabStateCalledList.add(source)
    }

}