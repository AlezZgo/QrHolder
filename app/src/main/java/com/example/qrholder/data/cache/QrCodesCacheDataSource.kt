package com.example.qrholder.data.cache

import com.example.qrholder.data.QrCodeData
import com.example.qrholder.data.cache.db.QrCodesDao
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

interface QrCodesCacheDataSource {

    suspend fun allQrCodes(): List<QrCodeData>

    class Base @Inject constructor(
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