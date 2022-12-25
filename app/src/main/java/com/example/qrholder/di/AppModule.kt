package com.example.qrholder.di

import android.content.Context
import com.example.qrholder.core.ManageResources
import com.example.qrholder.presentation.core.viewmodel.DispatchersList
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

    @EmptyText
    @Provides
    fun provideEmptyText() = ""
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EmptyText