package com.example.qrholder.home.data.cache

import com.example.qrholder.home.data.QrCodeData
import com.example.qrholder.home.data.cache.db.QrCodesDao
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface QrCodesCacheDataSource {

    suspend fun allQrCodes(): List<QrCodeData>

    class Base(
        private val dao: QrCodesDao,
    ) : QrCodesCacheDataSource {

        private val mutex = Mutex()

        override suspend fun allQrCodes(): List<QrCodeData> = mutex.withLock {
            dao.allQrCodes().map { qrCodeCache ->
                QrCodeData(
                    title = qrCodeCache.title,
                    content = qrCodeCache.content,
                    path = qrCodeCache.path
                )
            }
        }

    }
}
