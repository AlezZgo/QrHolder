package com.example.qrholder.di

import com.example.qrholder.home.domain.HomeInteractor
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