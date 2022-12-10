package com.example.qrholder.home.data

interface QrCodesCacheDataSource {

    suspend fun allQrCodes(): List<QrCodeData>
}
