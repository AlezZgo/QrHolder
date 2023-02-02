package com.example.qrholder.di

import com.example.qrholder.presentation.scanFromGallery.GalleryImagesCommunication
import com.example.qrholder.presentation.scanFromGallery.ScanResultCommunication
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
interface GalleryImagesModule {

    @Binds
    fun bindGalleryImagesCommunication(
        communication: GalleryImagesCommunication.Base
    ): GalleryImagesCommunication

    @Binds
    fun bindScanGalleryImageResultCommunication(
        communication: ScanResultCommunication.Base
    ) : ScanResultCommunication
}