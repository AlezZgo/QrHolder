package com.example.qrholder.presentation.buildQrCode

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.qrholder.data.QrCodesRepository
import com.example.qrholder.di.Content
import com.example.qrholder.di.EmptyText
import com.example.qrholder.di.Title
import com.example.qrholder.presentation.core.validation.TextValidationResult
import com.example.qrholder.presentation.core.validation.Validate
import com.example.qrholder.presentation.core.viewmodel.AbstractViewModel
import com.example.qrholder.presentation.core.viewmodel.DispatchersList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuildQrCodeViewModel @Inject constructor(
    private val qrCodeBuiltCommunication: QrCodeBuiltCommunication,
    private val validateTitleCommunication: ValidateTitleCommunication,
    private val validateContentCommunication: ValidateContentCommunication,
    private val dispatchers: DispatchersList,
    private val repository: QrCodesRepository,
    private val createQrCodeImage: CreateQrCodeImage,
    @Title private val validateTitle: Validate<String, TextValidationResult>,
    @Content private val validateContent: Validate<String, TextValidationResult>,
    @EmptyText private var titleText: String,
    @EmptyText private var contentText: String,
) : AbstractViewModel(), ChangeTitle, ChangeContent, Build, ValidateTitle, ValidateContent,
    ObserveQrCodeBuildResult, ObserveTitle, ObserveContent {

    override fun init() {}

    override fun observeBuildResultState(
        owner: LifecycleOwner,
        observer: Observer<QrCodeBuildResult>
    ) = qrCodeBuiltCommunication.observe(owner, observer)

    override fun changeTitle(title: String) {
        titleText = title
    }

    override fun changeContent(content: String) {
        contentText = content
    }

    override fun build() {

        val titleValidationResult = validateTitle.validate(titleText)
        val contentValidationResult = validateContent.validate(contentText)

        validateTitleCommunication.map(titleValidationResult)
        validateContentCommunication.map(contentValidationResult)

        if (titleValidationResult is TextValidationResult.Success &&
            contentValidationResult is TextValidationResult.Success
        ) {
            viewModelScope.launch(dispatchers.io()) {
                qrCodeBuiltCommunication.map(
                    createQrCodeImage.create(contentText).save(titleText, repository)
                        .map(titleText, contentText)
                )
            }

        }


    }

    override fun validateTitle(title: String) = validateTitle.validate(title)

    override fun validateContent(content: String) = validateContent.validate(content)

    override fun observeTitle(owner: LifecycleOwner, observer: Observer<TextValidationResult>) {
        validateTitleCommunication.observe(owner, observer)
    }

    override fun observeContent(owner: LifecycleOwner, observer: Observer<TextValidationResult>) {
        validateContentCommunication.observe(owner, observer)
    }

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

interface ObserveTitle {
    fun observeTitle(owner: LifecycleOwner, observer: Observer<TextValidationResult>)
}

interface ObserveContent {
    fun observeContent(owner: LifecycleOwner, observer: Observer<TextValidationResult>)
}

