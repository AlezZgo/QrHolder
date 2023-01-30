package com.example.qrholder.di

import com.example.qrholder.presentation.scanFromGallery.GalleryImagesCommunication
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
}