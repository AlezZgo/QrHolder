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
    private val dispatchers : DispatchersList,
    private val communications : BuildQrCodeCommunications,
    private val manageResources : ManageResources,
    @EmptyText private var titleText: String,
    @EmptyText private var contentText: String,
    @TitleTextMapperAnnotation private val titleMapper: Mapper<String,Unit>,
    @ContentTextMapperAnnotation private val contentMapper: Mapper<String,Unit>,
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

    override fun changeTitle(title: String) { titleText = title }

    override fun changeContent(content: String) { contentText = content }

    override fun build() {
        titleMapper.map(titleText)
        contentMapper.map(contentText)
    }

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

