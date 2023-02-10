package com.alezzgo.qrholder.data.cache

import com.alezzgo.qrholder.data.QrCodeData
import com.alezzgo.qrholder.data.SystemSettingsNeverShow
import com.alezzgo.qrholder.data.cache.db.QrCodeCache
import com.alezzgo.qrholder.data.cache.db.QrCodesDao
import com.alezzgo.qrholder.domain.FetchQrCode
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

interface QrCodesCacheDataSource : SystemSettingsNeverShow, FetchQrCode<QrCodeCache> {

    suspend fun allQrCodes(): List<QrCodeData>

    suspend fun save(qrCode: QrCodeData)

    suspend fun delete(qrCodeTitle: String)

    class Base @Inject constructor(
        private val dao: QrCodesDao,
        private val sharedPrefs: SharedPrefs,
        private val mapper: QrCodeData.Mapper<QrCodeCache>,
    ) : QrCodesCacheDataSource {

        //todo is it necessary?
        private val mutex = Mutex()

        override suspend fun allQrCodes(): List<QrCodeData> = mutex.withLock {
            dao.selectAll().map { qrCodeCache ->
                QrCodeData(
                    title = qrCodeCache.title,
                    content = qrCodeCache.content,
                    path = qrCodeCache.path
                )
            }
        }

        override suspend fun save(qrCode: QrCodeData) = mutex.withLock {
            dao.insert(qrCode.map(mapper))
        }

        override suspend fun delete(qrCodeTitle: String) {
            dao.delete(qrCodeTitle = qrCodeTitle)
        }

        override fun fetchSystemSettingsNeverShow() = sharedPrefs.fetchPermissionNeverShow()


        override fun saveSystemSettingsNeverShow(neverShow: Boolean) =
            sharedPrefs.savePermissionNeverShow(neverShow = neverShow)

        override suspend fun fetchQrCode(title: String): QrCodeCache {
            return dao.select(title)
                ?: throw IllegalStateException("There is no qr code with such title")
        }


    }
}
