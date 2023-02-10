package com.alezzgo.qrholder.di

import com.alezzgo.qrholder.domain.HomeInteractor
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