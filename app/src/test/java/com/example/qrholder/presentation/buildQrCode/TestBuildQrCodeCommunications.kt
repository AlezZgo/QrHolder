package com.example.qrholder.presentation.buildQrCode

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class TestBuildQrCodeCommunication : BuildQrCodeCommunications {

    val buildResultCommunication : BuildResult

    override fun showTitleState(titleState: InputEditTextUiState) {
        titleCommunication.inputEditTextUiStateCalledList.add(titleState)
    }

    override fun showContentState(contentState: InputEditTextUiState) {
        contentCommunication.inputEditTextUiStateCalledList.add(contentState)
    }

    override fun observeTitleUiState(
        owner: LifecycleOwner,
        observer: Observer<InputEditTextUiState>
    ) = Unit

    override fun observeContentUiState(
        owner: LifecycleOwner,
        observer: Observer<InputEditTextUiState>
    ) = Unit
}