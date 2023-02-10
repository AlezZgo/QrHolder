package com.alezzgo.qrholder.presentation.buildQrCode

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.alezzgo.qrholder.R
import com.alezzgo.qrholder.core.ManageResources
import com.alezzgo.qrholder.data.QrCodeData
import com.alezzgo.qrholder.data.QrCodesRepository
import com.alezzgo.qrholder.di.Content
import com.alezzgo.qrholder.di.EmptyText
import com.alezzgo.qrholder.di.Title
import com.alezzgo.qrholder.domain.model.QrCode
import com.alezzgo.qrholder.presentation.core.model.QrCodeUi
import com.alezzgo.qrholder.presentation.core.validation.TextValidationResult
import com.alezzgo.qrholder.presentation.core.validation.Validate
import com.alezzgo.qrholder.presentation.core.viewmodel.AbstractViewModel
import com.alezzgo.qrholder.presentation.core.viewmodel.DispatchersList
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
    private val qrCodeToUiMapper: QrCode.Mapper<QrCodeUi>,
    private val qrCodeToDataMapper: QrCode.Mapper<QrCodeData>,
    private val manageResources: ManageResources,
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
            //todo change to coroutine scope
            //todo I have messed with these mappers :D
            //todo I 'm second-guessing a decision
            viewModelScope.launch(dispatchers.io()) {

                qrCodeBuiltCommunication.map(
                    try {
                        QrCodeBuildResult.Success(
                            createQrCodeImage.create(contentText)
                                .save(titleText, contentText, repository, qrCodeToDataMapper)
                                .map(qrCodeToUiMapper)
                        )
                    } catch (e: Exception) {
                        QrCodeBuildResult.Error(
                            e.message ?: manageResources.string(R.string.defaultErrorMessage)
                        )
                    }
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

