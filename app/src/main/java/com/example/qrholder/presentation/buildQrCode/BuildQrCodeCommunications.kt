package com.example.qrholder.presentation.buildQrCode

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.qrholder.presentation.core.ObserveUiState
import com.example.qrholder.presentation.core.viewmodel.Communication
import javax.inject.Inject

interface BuildQrCodeCommunications : ShowTitleUiState, ShowContentUiState, ObserveTitleUiState, ObserveContentUiState {

    class Base@Inject constructor(
        private val titleStateCommunication: TitleUiStateCommunication,
        private val contentStateCommunication: ContentUiStateCommunication,
    ) : BuildQrCodeCommunications {

        override fun showTitleState(titleState: InputEditTextUiState) =
            titleStateCommunication.map(titleState)

        override fun showContentState(contentState: InputEditTextUiState) =
            contentStateCommunication.map(contentState)

        override fun observeTitleUiState(
            owner: LifecycleOwner,
            observer: Observer<InputEditTextUiState>
        ) = titleStateCommunication.observe(owner, observer)

        override fun observeContentUiState(
            owner: LifecycleOwner,
            observer: Observer<InputEditTextUiState>
        ) = contentStateCommunication.observe(owner, observer)
    }

}

interface ShowTitleUiState {
    fun showTitleState(titleState: InputEditTextUiState)
}

interface ShowContentUiState {
    fun showContentState(contentState: InputEditTextUiState)
}

interface ObserveTitleUiState {
    fun observeTitleUiState(owner: LifecycleOwner, observer: Observer<InputEditTextUiState>)
}

interface ObserveContentUiState {
    fun observeContentUiState(owner: LifecycleOwner, observer: Observer<InputEditTextUiState>)
}

interface TitleUiStateCommunication : Communication.Mutable<InputEditTextUiState> {
    class Base @Inject constructor() : Communication.Post<InputEditTextUiState>(),
        TitleUiStateCommunication
}

interface ContentUiStateCommunication : Communication.Mutable<InputEditTextUiState> {
    class Base @Inject constructor() : Communication.Post<InputEditTextUiState>(),
        ContentUiStateCommunication
}
