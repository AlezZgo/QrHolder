package com.example.qrholder.di

import android.content.Context
import androidx.room.Room
import com.example.qrholder.home.data.cache.db.QrCodesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object CacheModule{

    @Provides
    fun provideDatabase(
        context: Context
    ) : QrCodesDatabase = Room.databaseBuilder(
        context,
        QrCodesDatabase::class.java,
        "qr_codes_database"
    ).build()

}