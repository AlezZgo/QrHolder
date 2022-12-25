package com.example.qrholder.di

import com.example.qrholder.core.Mapper
import com.example.qrholder.presentation.buildQrCode.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface BuildQrCodePresentationModule {

    @Singleton
    @Binds
    fun bindBuildQrCodeCommunications(communications: BuildQrCodeCommunications.Base): BuildQrCodeCommunications

    @Singleton
    @Binds
    fun bindTitleUiStateCommunication(communication: TitleUiStateCommunication.Base): TitleUiStateCommunication

    @Singleton
    @Binds
    fun bindContentUiStateCommunication(communication: ContentUiStateCommunication.Base): ContentUiStateCommunication

    @TitleTextMapperAnnotation
    @Binds
    fun bindTitleTextMapper(mapper: TitleTextMapper): Mapper<String, Unit>

    @ContentTextMapperAnnotation
    @Binds
    fun bindContentTextMapper(mapper: ContentTextMapper): Mapper<String, Unit>

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ContentTextMapperAnnotation

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TitleTextMapperAnnotation
