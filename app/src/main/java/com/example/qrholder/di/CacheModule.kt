package com.example.qrholder.di

import android.content.Context
import androidx.room.Room
import com.example.qrholder.core.Provide
import com.example.qrholder.data.cache.db.QrCodesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
object CacheModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): QrCodesDatabase = Room.databaseBuilder(
        context,
        QrCodesDatabase::class.java,
        "qr_codes_database"
    ).build()

    @Provides
    fun provideDao(qrCodesDb: QrCodesDatabase) = qrCodesDb.qrCodesDao()


}