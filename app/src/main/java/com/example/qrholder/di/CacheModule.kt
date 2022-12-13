package com.example.qrholder.di

import android.content.Context
import androidx.room.Room
import com.example.qrholder.core.Provide
import com.example.qrholder.home.data.cache.db.QrCodesDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
object CacheModule  {

    @Provides
    fun provideDatabase(database: Base): QrCodesDatabase = database.provide()

    @Provides
    fun provideDao(qrCodesDb : QrCodesDatabase) = qrCodesDb.qrCodesDao()

    class Base @Inject constructor(
        @ApplicationContext context: Context
    ) : Provide<QrCodesDatabase> {

        private val db by lazy {
            Room.databaseBuilder(
                context,
                QrCodesDatabase::class.java,
                "qr_codes_database"
            ).build()
        }

        override fun provide(): QrCodesDatabase = db

    }

}