package com.example.qrholder.presentation.buildQrCode

import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.qrholder.data.QrCodesRepository
import com.example.qrholder.di.ContentValidator
import com.example.qrholder.di.EmptyText
import com.example.qrholder.di.TitleValidator
import com.example.qrholder.presentation.core.validation.TextValidationResult
import com.example.qrholder.presentation.core.validation.Validate
import com.example.qrholder.presentation.core.viewmodel.AbstractViewModel
import com.example.qrholder.presentation.core.viewmodel.DispatchersList
import com.example.qrholder.presentation.home.model.QrCodeUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuildQrCodeViewModel @Inject constructor(
    private val communication: QrCodeBuiltCommunication,
    private val dispatchers: DispatchersList,
    private val repository: QrCodesRepository,
    private val createQrCodeImage: CreateQrCodeImage,
    @TitleValidator private val validateTitle: Validate<String, TextValidationResult>,
    @ContentValidator private val validateContent: Validate<String, TextValidationResult>,
    @EmptyText private var titleText: String,
    @EmptyText private var contentText: String,
) : AbstractViewModel(), ChangeTitle, ChangeContent, Build, ValidateTitle, ValidateContent,
    ObserveQrCodeBuildResult {

    override fun init() {}

    override fun observeBuildResultState(
        owner: LifecycleOwner,
        observer: Observer<QrCodeBuildResult>
    ) = communication.observe(owner, observer)

    override fun changeTitle(title: String) {
        titleText = title
    }

    override fun changeContent(content: String) {
        contentText = content
    }

    override fun build() {

        val titleValidationResult = validateTitle.validate(titleText)
        val contentValidationResult = validateContent.validate(contentText)

        if(titleValidationResult is TextValidationResult.Success &&
            contentValidationResult is TextValidationResult.Success){
            viewModelScope.launch(dispatchers.io()) {
                val image = createQrCodeImage.create(contentText)
                val imagePath = repository.saveQrCodeImage(image,titleText)

                    communication.map(
                        imagePath.map(titleText,contentText)
                    )



            }

        }


    }

    override fun validateTitle(title: String) = validateTitle.validate(title)

    override fun validateContent(content: String) =  validateContent.validate(content)

}

interface ValidateTitle {

    fun validateTitle(title: String): TextValidationResult
}

interface ValidateContent {

    fun validateContent(content: String): TextValidationResult
}

interface ChangeTitle {
    fun changeTitle(title: String)
}

interface ChangeContent {
    fun changeContent(content: String)
}

interface Create<I, O> {
    fun create(input: I): O
}

interface Build {
    fun build()
}

interface ObserveQrCodeBuildResult {
    fun observeBuildResultState(owner: LifecycleOwner, observer: Observer<QrCodeBuildResult>)
}

