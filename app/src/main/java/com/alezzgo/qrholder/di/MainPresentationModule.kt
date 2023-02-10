package com.alezzgo.qrholder.di

import com.alezzgo.qrholder.presentation.core.ScanQrCodeFromImage
import com.alezzgo.qrholder.presentation.main.MainFabStateCommunication
import com.alezzgo.qrholder.presentation.main.QrCodeScannedCommunication
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface MainPresentationModule {

    @Binds
    fun bindMainFabStateCommunication(communication: MainFabStateCommunication.Base): MainFabStateCommunication

    @Binds
    fun bindQrCodeScannedCommunication(communication: QrCodeScannedCommunication.Base): QrCodeScannedCommunication

    @Binds
    fun bindQrCodeScanFromImage(scan: ScanQrCodeFromImage.Base): ScanQrCodeFromImage

}