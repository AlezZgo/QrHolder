package com.example.qrholder.data.cache.db

import androidx.room.*

@Dao
interface QrCodesDao {

    @Query("SELECT * FROM qr_codes_table ORDER BY date DESC")
    suspend fun selectAll(): List<QrCodeCache>

    @Query("SELECT * FROM qr_codes_table WHERE title=:qrCodeTitle")
    suspend fun select(qrCodeTitle: String): QrCodeCache?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(qrCode: QrCodeCache)

    @Query("DELETE FROM qr_codes_table WHERE title=:qrCodeTitle")
    fun delete(qrCodeTitle: String)

    @Query("DELETE FROM qr_codes_table")
    fun clearAll()

}