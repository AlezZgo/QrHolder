package com.example.qrholder.data.cache.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "qr_codes_table")
data class QrCodeCache(
    @PrimaryKey @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "path") val path: String
)