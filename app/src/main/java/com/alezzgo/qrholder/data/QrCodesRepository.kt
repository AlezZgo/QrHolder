package com.alezzgo.qrholder.data

import com.alezzgo.qrholder.R
import com.alezzgo.qrholder.core.ManageResources
import com.alezzgo.qrholder.data.cache.QrCodesCacheDataSource
import com.alezzgo.qrholder.domain.FetchQrCode
import com.alezzgo.qrholder.domain.model.ImagePath
import com.alezzgo.qrholder.domain.model.QrCode
import com.alezzgo.qrholder.domain.model.QrCodes
import com.alezzgo.qrholder.presentation.buildQrCode.BitmapWrapper
import javax.inject.Inject


interface QrCodesRepository : SaveQrCode, DeleteQrCodeImage, SystemSettingsNeverShow,
    FetchQrCode<QrCode> {

    suspend fun allQrCodes(): QrCodes

    suspend fun delete(qrCodeTitle: String)

    class Base @Inject constructor(
        private val cacheDataSource: QrCodesCacheDataSource,
        private val mapper: QrCodeData.Mapper<QrCode>,
        private val manageResources: ManageResources,
        private val saveInternalStorage: SaveInternalStorage<BitmapWrapper>,
        private val deleteInternalStorage: DeleteInternalStorage
    ) : QrCodesRepository {

        override suspend fun allQrCodes(): QrCodes = try {
            QrCodes.Success(cacheDataSource.allQrCodes().map { it.map(mapper) })
        } catch (e: Exception) {
            QrCodes.Failure(e.message ?: manageResources.string(R.string.defaultErrorMessage))
        }


        override suspend fun delete(qrCodeTitle: String) {
            cacheDataSource.delete(qrCodeTitle = qrCodeTitle)
        }

        override fun deleteImage(path: String) {
            deleteInternalStorage.deleteImage(path)
        }

        override fun fetchSystemSettingsNeverShow() = cacheDataSource.fetchSystemSettingsNeverShow()

        override fun saveSystemSettingsNeverShow(neverShow: Boolean) =
            cacheDataSource.saveSystemSettingsNeverShow(neverShow = neverShow)

        override suspend fun fetchQrCode(title: String): QrCode {
            val qrCodeCache = cacheDataSource.fetchQrCode(title = title)
            return QrCode(qrCodeCache.title, qrCodeCache.content, qrCodeCache.path)
        }

        override suspend fun saveImage(model: BitmapWrapper, name: String) =
            saveInternalStorage.save(
                model = model,
                name = name,
            )

        override suspend fun save(qrCode: QrCodeData) = cacheDataSource.save(qrCode)

    }

}

interface SaveQrCode {
    suspend fun saveImage(model: BitmapWrapper, name: String): ImagePath

    suspend fun save(qrCode: QrCodeData)
}

interface SystemSettingsNeverShow {
    fun fetchSystemSettingsNeverShow(): Boolean
    fun saveSystemSettingsNeverShow(neverShow: Boolean)
}


