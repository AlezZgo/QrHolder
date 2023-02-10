package com.alezzgo.qrholder.di

import com.alezzgo.qrholder.presentation.buildQrCode.*
import com.alezzgo.qrholder.presentation.core.validation.TextValidationResult
import com.alezzgo.qrholder.presentation.core.validation.Validate
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Qualifier

@InstallIn(ViewModelComponent::class)
@Module
interface BuildQrCodeModule {


    @Title
    @Binds
    fun bindValidateTitle(validateTitle: ValidateBuildQrCodeTitleText): Validate<String, TextValidationResult>

    @Content
    @Binds
    fun bindValidateContent(validateTitle: ValidateBuildQrCodeContentText): Validate<String, TextValidationResult>

    @Binds
    fun bindQrCodeBuilt(communication: QrCodeBuiltCommunication.Base): QrCodeBuiltCommunication


    @Binds
    fun bindValidateTitleCommunication(communication: ValidateTitleCommunication.Base): ValidateTitleCommunication


    @Binds
    fun bindValidateContentCommunication(communication: ValidateContentCommunication.Base): ValidateContentCommunication

    @Binds
    fun bindContentUiStateCommunication(communication: CreateQrCodeImage.Base): CreateQrCodeImage

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Title

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Content


