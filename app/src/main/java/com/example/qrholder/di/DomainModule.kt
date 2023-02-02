package com.example.qrholder.di

import com.example.qrholder.domain.HomeInteractor
import com.example.qrholder.domain.model.QrCode
import com.example.qrholder.presentation.core.model.QrCodeUi
import com.example.qrholder.presentation.home.mapper.QrCodeToUiMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface DomainModule {

    @Binds
    fun bindHomeInteractor(interactor: HomeInteractor.Base): HomeInteractor

}