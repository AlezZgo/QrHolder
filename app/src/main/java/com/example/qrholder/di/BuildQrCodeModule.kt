package com.example.qrholder.di

import com.example.qrholder.presentation.buildQrCode.*
import com.example.qrholder.presentation.core.validation.TextValidationResult
import com.example.qrholder.presentation.core.validation.Validate
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@InstallIn(ViewModelComponent::class)
@Module
interface BuildQrCodeModule {


    @TitleValidator
    @Binds
    fun bindValidateTitle(validateTitle: ValidateBuildQrCodeTitleText): Validate<String,TextValidationResult>

    @ContentValidator
    @Binds
    fun bindValidateContent(validateTitle: ValidateBuildQrCodeContentText): Validate<String,TextValidationResult>

    @Binds
    fun bindQrCodeBuilt(communication: QrCodeBuiltCommunication.Base): QrCodeBuiltCommunication


    @Binds
    fun bindContentUiStateCommunication(communication: CreateQrCodeImage.Base): CreateQrCodeImage

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TitleValidator

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ContentValidator
