package com.example.qrholder.home.data.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QrCodeCache::class], version = 1, exportSchema = false)
abstract class QrCodesDatabase : RoomDatabase() {

    abstract fun qrCodesDao(): QrCodesDao

}