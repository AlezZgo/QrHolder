package com.example.qrholder.di

import android.content.Context
import android.graphics.Bitmap
import com.example.qrholder.core.HideKeyBoard
import com.example.qrholder.core.ManageResources
import com.example.qrholder.data.SaveInternalStorage
import com.example.qrholder.presentation.buildQrCode.BitmapWrapper
import com.example.qrholder.presentation.core.viewmodel.DispatchersList
import com.google.zxing.qrcode.QRCodeWriter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideManageResources(
        @ApplicationContext context: Context
    ): ManageResources = ManageResources.Base(context)

    @Provides
    fun provideDispatcherList(): DispatchersList = DispatchersList.Base()

    @Provides
    fun bindSaveBitmapToInternalStorage(
        @ApplicationContext context: Context,
        manageResources: ManageResources
    ) : SaveInternalStorage<BitmapWrapper> = SaveInternalStorage.ImageSave(context,manageResources)

    @Provides
    fun bindHideKeyBoard() : HideKeyBoard = HideKeyBoard.Base()

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