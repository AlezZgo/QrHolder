package com.example.qrholder.presentation.editQrCode

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.qrholder.R
import com.example.qrholder.core.ManageResources
import com.example.qrholder.data.QrCodeData
import com.example.qrholder.data.QrCodesRepository
import com.example.qrholder.di.Content
import com.example.qrholder.di.EmptyText
import com.example.qrholder.domain.model.QrCode
import com.example.qrholder.presentation.buildQrCode.*
import com.example.qrholder.presentation.core.model.QrCodeUi
import com.example.qrholder.presentation.core.validation.TextValidationResult
import com.example.qrholder.presentation.core.validation.Validate
import com.example.qrholder.presentation.core.viewmodel.AbstractViewModel
import com.example.qrholder.presentation.core.viewmodel.DispatchersList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditQrCodeVIewModel @Inject constructor(
    private val qrCodeEditCommunication: QrCodeBuiltCommunication,
    private val validateContentCommunication: ValidateContentCommunication,
    private val dispatchers: DispatchersList,
    private val repository: QrCodesRepository,
    private val manageResources: ManageResources,
    private val createQrCodeImage: CreateQrCodeImage,
    private val qrCodeToUiMapper: QrCode.Mapper<QrCodeUi>,
    private val qrCodeToDataMapper: QrCode.Mapper<QrCodeData>,
    @Content private val validateContent: Validate<String, TextValidationResult>,
    @EmptyText private var contentText: String,
) : AbstractViewModel(), ChangeContent, ObserveContent,ObserveQrCodeBuildResult, Edit {
    override fun init() {}

    override fun changeContent(content: String) {
        contentText = content
    }

    override fun observeContent(owner: LifecycleOwner, observer: Observer<TextValidationResult>) {
        validateContentCommunication.observe(owner, observer)
    }

    override fun observeBuildResultState(
        owner: LifecycleOwner,
        observer: Observer<QrCodeBuildResult>
    ) = qrCodeEditCommunication.observe(owner, observer)

    override fun edit(oldTitle : String) {
        val contentValidationResult = validateContent.validate(contentText)
        validateContentCommunication.map(contentValidationResult)
        if (contentValidationResult is TextValidationResult.Success) {
            viewModelScope.launch(dispatchers.io()) {
                qrCodeEditCommunication.map(
                    try {

                        repository.deleteImage(oldTitle)

                        QrCodeBuildResult.Success(
                            createQrCodeImage.create(contentText)
                                .save(oldTitle, contentText, repository, qrCodeToDataMapper)
                                .map(qrCodeToUiMapper)
                        )

                    } catch (e: Exception) {
                        Log.e("tagger",e.message?:"Unknown error message")
                        QrCodeBuildResult.Error(
                            e.message ?: manageResources.string(R.string.defaultErrorMessage)
                        )
                    }
                )
            }
        }
    }
}

interface Edit {
    fun edit(oldTitle: String)
}

