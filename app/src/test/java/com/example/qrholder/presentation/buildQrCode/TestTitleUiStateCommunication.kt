package com.example.qrholder.presentation.buildQrCode

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class TestTitleUiStateCommunication : TitleUiStateCommunication {

    var inputEditTextUiStateCalledList = mutableListOf<InputEditTextUiState>()

    override fun map(source: InputEditTextUiState) {
        inputEditTextUiStateCalledList.add(source)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<InputEditTextUiState>) = Unit


}