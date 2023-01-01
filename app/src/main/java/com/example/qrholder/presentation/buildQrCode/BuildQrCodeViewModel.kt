package com.example.qrholder.presentation.buildQrCode

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.qrholder.R
import com.example.qrholder.core.ManageResources
import com.example.qrholder.core.Mapper
import com.example.qrholder.di.ContentTextMapperAnnotation
import com.example.qrholder.di.EmptyText
import com.example.qrholder.di.TitleTextMapperAnnotation
import com.example.qrholder.presentation.core.viewmodel.AbstractViewModel
import com.example.qrholder.presentation.core.viewmodel.DispatchersList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BuildQrCodeViewModel @Inject constructor(
    private val communications : BuildQrCodeCommunications,
    private val qrCodeBuilder: QrCodeBuilder
) : AbstractViewModel(),ChangeTitle,ChangeContent,Build, ObserveTitleUiState, ObserveContentUiState {

    override fun init() {
        communications.showTitleState(InputEditTextUiState.NoError)
        communications.showContentState(InputEditTextUiState.NoError)
    }

    override fun observeTitleUiState(
        owner: LifecycleOwner,
        observer: Observer<InputEditTextUiState>
    ) = communications.observeTitleUiState(owner, observer)

    override fun observeContentUiState(
        owner: LifecycleOwner,
        observer: Observer<InputEditTextUiState>
    ) = communications.observeContentUiState(owner, observer)

    override fun changeTitle(title: String) =  qrCodeBuilder.changeTitle(title)

    override fun changeContent(content: String) = qrCodeBuilder.changeContent(content)

    override fun build() = qrCodeBuilder.build()

}

interface ChangeTitle{
    fun changeTitle(title: String)
}

interface ChangeContent{
    fun changeContent(content: String)
}

interface Build{
    fun build()
}

