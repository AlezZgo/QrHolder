package com.example.qrholder.presentation.buildQrCode

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.qrholder.core.Mapper
import com.example.qrholder.data.QrCodesRepository
import com.example.qrholder.di.ContentTextMapperAnnotation
import com.example.qrholder.di.EmptyText
import com.example.qrholder.di.TitleTextMapperAnnotation
import com.example.qrholder.domain.model.QrCodeBuildResult
import com.example.qrholder.presentation.core.viewmodel.AbstractViewModel
import com.example.qrholder.presentation.core.viewmodel.DispatchersList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuildQrCodeViewModel @Inject constructor(
    @TitleTextMapperAnnotation private val titleMapper: Mapper<String, Unit>,
    @ContentTextMapperAnnotation private val contentMapper: Mapper<String, Unit>,
    @EmptyText private var titleText: String,
    @EmptyText private var contentText: String,
    private val communications: BuildQrCodeCommunications,
    private val createQrCode: CreateQrCodeImage,
    private val repository: QrCodesRepository,
    private val dispatchers: DispatchersList,
    ) : AbstractViewModel(), ChangeTitle, ChangeContent, Build, ObserveTitleUiState,
    ObserveContentUiState, ObserveBuildResult {

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

    override fun observeBuildResultState(
        owner: LifecycleOwner,
        observer: Observer<QrCodeBuildResult>
    ) = communications.observeBuildResultState(owner,observer)


    override fun changeTitle(title: String) { titleText = title }

    override fun changeContent(content: String) { contentText = content }

    override fun build(){
        titleMapper.map(titleText)
        contentMapper.map(contentText)
        if (communications.isError())
            return
        else {
            viewModelScope.launch(dispatchers.io()) {
                try {
                    val qrCodeImage = createQrCode.create(input = contentText)
                    val imagePath = repository.save(model = qrCodeImage, name = titleText)
                    val result = imagePath.map(titleText,contentText)
                    communications.showBuildResultState(result)

                }catch (e : Exception){
                    Log.e("App logger",e.message?:"Unknown error")
                }

            }
        }
    }

}

interface ChangeTitle {
    fun changeTitle(title: String)
}

interface ChangeContent {
    fun changeContent(content: String)
}

interface Create<I,O> {
    fun create(input : I) : O
}

interface Build{
    fun build()
}

