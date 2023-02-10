package com.alezzgo.qrholder.di

import android.content.Context
import com.alezzgo.qrholder.core.HideKeyBoard
import com.alezzgo.qrholder.core.ManageResources
import com.alezzgo.qrholder.data.SaveInternalStorage
import com.alezzgo.qrholder.presentation.buildQrCode.BitmapWrapper
import com.alezzgo.qrholder.presentation.core.ManageBrightness
import com.alezzgo.qrholder.presentation.core.ManageExternalStorageImages
import com.alezzgo.qrholder.presentation.core.viewmodel.DispatchersList
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.mintrocket.lib.mintpermissions.MintPermissions
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
    fun providePermissionsController() = MintPermissions.controller

    @Provides
    fun provideManageExternalStorageImages(@ApplicationContext context: Context): ManageExternalStorageImages =
        ManageExternalStorageImages.Base(context)

    @Provides
    fun providePermissionsManager() = MintPermissions.createManager()

    @Provides
    fun provideSaveBitmapToInternalStorage(
        @ApplicationContext context: Context,
        manageResources: ManageResources
    ): SaveInternalStorage<BitmapWrapper> = SaveInternalStorage.ImageSave(context, manageResources)

    @Provides
    fun provideQrCodeScanner(
    ): BarcodeScanner = BarcodeScanning.getClient(
        BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
            .build()
    )

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