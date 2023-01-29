package com.example.qrholder.di

import android.content.Context
import com.example.qrholder.core.HideKeyBoard
import com.example.qrholder.core.ManageResources
import com.example.qrholder.data.SaveInternalStorage
import com.example.qrholder.presentation.buildQrCode.BitmapWrapper
import com.example.qrholder.presentation.core.ManageBrightness
import com.example.qrholder.presentation.core.viewmodel.DispatchersList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideManageResources(
        @ApplicationContext context: Context
    ): ManageResources = ManageResources.Base(context)

    @Provides
    fun provideManageBrightness(
        @ApplicationContext context: Context
    ): ManageBrightness = ManageBrightness.Base(context)

    @Provides
    fun provideDispatcherList(): DispatchersList = DispatchersList.Base()

    @Provides
    fun provideSaveBitmapToInternalStorage(
        @ApplicationContext context: Context,
        manageResources: ManageResources
    ): SaveInternalStorage<BitmapWrapper> = SaveInternalStorage.ImageSave(context, manageResources)


    @Provides
    fun bindHideKeyBoard(): HideKeyBoard = HideKeyBoard.Base()

    @EmptyText
    @Provides
    fun provideEmptyText() = ""

    @QrCodeStandardSize
    @Provides
    fun provideStandardQrCodeSize() = 450

    @IntZero
    @Provides
    fun provideIntZero() = 0

    @IntMax
    @Provides
    fun provideIntMax() = Int.MAX_VALUE
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EmptyText

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class QrCodeStandardSize

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IntZero

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IntMax