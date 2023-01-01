package com.example.qrholder.presentation.buildQrCode

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.qrholder.presentation.core.IsError
import com.example.qrholder.presentation.core.SingleLiveEvent
import com.example.qrholder.presentation.core.SinglePost
import com.example.qrholder.presentation.core.viewmodel.Communication
import com.example.qrholder.presentation.home.model.QrCodeUi
import javax.inject.Inject

interface BuildQrCodeCommunications : ShowTitleUiState, ShowContentUiState, ObserveTitleUiState,
    ObserveContentUiState, IsError, ShowQrCodeCreatedUiState {

    class Base @Inject constructor(
        private val titleStateCommunication: TitleUiStateCommunication,
        private val contentStateCommunication: ContentUiStateCommunication,
        private val successStateCommunication: BuildQrCodeSuccessUiStateCommunication,
    ) : BuildQrCodeCommunications {

        override fun showTitleState(titleState: InputEditTextUiState) =
            titleStateCommunication.map(titleState)

        override fun showContentState(contentState: InputEditTextUiState) =
            contentStateCommunication.map(contentState)

        override fun showQrCodeCreatedUiState(qrCode: QrCodeUi) =
            successStateCommunication.map(qrCode)

        override fun observeTitleUiState(
            owner: LifecycleOwner,
            observer: Observer<InputEditTextUiState>
        ) = titleStateCommunication.observe(owner, observer)

        override fun observeContentUiState(
            owner: LifecycleOwner,
            observer: Observer<InputEditTextUiState>
        ) = contentStateCommunication.observe(owner, observer)

        override fun isError() =
            titleStateCommunication.isError() or contentStateCommunication.isError()
    }

}

interface ShowTitleUiState {
    fun showTitleState(titleState: InputEditTextUiState)
}

interface ShowContentUiState {
    fun showContentState(contentState: InputEditTextUiState)
}

interface ShowQrCodeCreatedUiState{
    fun showQrCodeCreatedUiState(qrCode: QrCodeUi)
}

interface ObserveTitleUiState {
    fun observeTitleUiState(owner: LifecycleOwner, observer: Observer<InputEditTextUiState>)
}

interface ObserveContentUiState {
    fun observeContentUiState(owner: LifecycleOwner, observer: Observer<InputEditTextUiState>)
}

interface TitleUiStateCommunication : Communication.Mutable<InputEditTextUiState>, IsError {
    class Base @Inject constructor() : Communication.Post<InputEditTextUiState>(),
        TitleUiStateCommunication {
        override fun isError() = liveData.value is InputEditTextUiState.Error
    }
}

interface ContentUiStateCommunication : Communication.Mutable<InputEditTextUiState>, IsError {
    class Base @Inject constructor() : Communication.Post<InputEditTextUiState>(),
        ContentUiStateCommunication {
        override fun isError() = liveData.value is InputEditTextUiState.Error
    }
}

class BuildQrCodeSuccessUiStateCommunication : SinglePost<QrCodeUi>()

