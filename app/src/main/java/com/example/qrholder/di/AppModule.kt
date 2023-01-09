package com.example.qrholder.di

import android.content.Context
import android.graphics.Bitmap
import com.example.qrholder.core.ManageResources
import com.example.qrholder.data.SaveInternalStorage
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
        @ApplicationContext context: Context
    ) : SaveInternalStorage<Bitmap> = SaveInternalStorage.ImageSave(context)

    @EmptyText
    @Provides
    fun provideEmptyText() = ""

    @QrCodeStandardSize
    @Provides
    fun provideStandardQrCodeSize() = 150
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EmptyText

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class QrCodeStandardSize