package com.example.qrholder.home.data.cache

import com.example.qrholder.home.data.QrCodeData

interface QrCodesCacheDataSource {

    suspend fun allQrCodes(): List<QrCodeData>

    class Base(
        private val dao: QrCodesDao,
    ) : QrCodesCacheDataSource {
        override suspend fun allQrCodes(): List<QrCodeData> = dao.allQrCodes().map { qrCodeCache ->
            QrCodeData(
                title = qrCodeCache.title,
                content = qrCodeCache.content,
                path = qrCodeCache.path
            )
        }

    }
}
