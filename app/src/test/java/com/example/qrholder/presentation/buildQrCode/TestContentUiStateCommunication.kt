package com.example.qrholder.presentation.buildQrCode

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class TestContentUiStateCommunication : ContentUiStateCommunication {

    var inputEditTextUiStateCalledList = mutableListOf<InputEditTextUiState>()

    override fun map(source: InputEditTextUiState) {
        inputEditTextUiStateCalledList.add(source)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<InputEditTextUiState>) = Unit


}