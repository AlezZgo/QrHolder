package com.example.qrholder.presentation.buildQrCode

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
//    @TitleTextMapperAnnotation private val titleMapper: Mapper<String,Unit>,
//    @ContentTextMapperAnnotation private val contentMapper: Mapper<String,Unit>,
) : AbstractViewModel(),ChangeTitle,ChangeContent,Build {

    override fun init() {
        communications.showTitleState(InputEditTextUiState.NoError)
        communications.showContentState(InputEditTextUiState.NoError)
    }

    override fun changeTitle(title: String) {
        titleText = title
    }

    override fun changeContent(content: String) {
        contentText = content
    }

    override fun build() {
        TODO("Not yet implemented")
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

