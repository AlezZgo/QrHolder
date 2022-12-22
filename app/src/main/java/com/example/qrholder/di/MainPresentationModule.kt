package com.example.qrholder.di

import com.example.qrholder.presentation.main.MainFabStateCommunication
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface MainPresentationModule {

    @Binds
    fun bindMainFabStateCommunication(communication: MainFabStateCommunication.Base): MainFabStateCommunication

}