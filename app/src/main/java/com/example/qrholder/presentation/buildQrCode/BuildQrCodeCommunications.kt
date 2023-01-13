package com.example.qrholder.presentation.buildQrCode

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.qrholder.domain.model.QrCodeBuildResult
import com.example.qrholder.presentation.core.IsError
import com.example.qrholder.presentation.core.viewmodel.Communication
import javax.inject.Inject

interface BuildQrCodeCommunications : ShowTitleUiState, ShowContentUiState, ObserveTitleUiState,
    ObserveContentUiState, IsError, ShowQrCodeBuildResultState,  ObserveBuildResult {

    class Base @Inject constructor(
        private val titleStateCommunication: TitleUiStateCommunication,
        private val contentStateCommunication: ContentUiStateCommunication,
        private val buildResultCommunication: BuildResultCommunication,
    ) : BuildQrCodeCommunications {

        override fun showTitleState(titleState: InputEditTextUiState) =
            titleStateCommunication.map(titleState)

        override fun showContentState(contentState: InputEditTextUiState) =
            contentStateCommunication.map(contentState)

        override fun showBuildResultState(qrCode: QrCodeBuildResult) =
            buildResultCommunication.map(qrCode)

        override fun observeBuildResultState(owner: LifecycleOwner, observer: Observer<QrCodeBuildResult>) =
            buildResultCommunication.observe(owner,observer)

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

interface ShowQrCodeBuildResultState{
    fun showBuildResultState(qrCode: QrCodeBuildResult)
}

interface ObserveTitleUiState {
    fun observeTitleUiState(owner: LifecycleOwner, observer: Observer<InputEditTextUiState>)
}

interface ObserveContentUiState {
    fun observeContentUiState(owner: LifecycleOwner, observer: Observer<InputEditTextUiState>)
}

interface ObserveBuildResult{
    fun observeBuildResultState(owner: LifecycleOwner, observer: Observer<QrCodeBuildResult>)
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

interface BuildResultCommunication : Communication.Mutable<QrCodeBuildResult>{
    class Base @Inject constructor() : Communication.Post<QrCodeBuildResult>(),BuildResultCommunication
}

