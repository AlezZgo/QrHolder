package com.example.qrholder.data.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QrCodesDao {

    @Query("SELECT * FROM qr_codes_table ORDER BY title ASC")
    fun allQrCodes(): List<QrCodeCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(qrCode: QrCodeCache)


}