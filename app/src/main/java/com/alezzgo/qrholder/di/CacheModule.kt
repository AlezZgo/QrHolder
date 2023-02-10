package com.alezzgo.qrholder.di

import android.content.Context
import androidx.room.Room
import com.alezzgo.qrholder.data.cache.SharedPrefs
import com.alezzgo.qrholder.data.cache.db.QrCodesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object CacheModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): QrCodesDatabase =
        Room.databaseBuilder(
            context,
            QrCodesDatabase::class.java,
            "qr_codes_database"
        ).build()

    @Provides
    fun provideDao(qrCodesDb: QrCodesDatabase) = qrCodesDb.qrCodesDao()

    @Provides
    fun provideSharedPrefs(@ApplicationContext context: Context): SharedPrefs = SharedPrefs.Base(
        context
    )


}