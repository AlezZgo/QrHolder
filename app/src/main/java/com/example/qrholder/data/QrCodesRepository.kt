package com.example.qrholder.data

import com.example.qrholder.R
import com.example.qrholder.core.ManageResources
import com.example.qrholder.data.cache.QrCodesCacheDataSource
import com.example.qrholder.domain.model.ImagePath
import com.example.qrholder.domain.model.QrCode
import com.example.qrholder.domain.model.QrCodes
import com.example.qrholder.presentation.buildQrCode.BitmapWrapper
import javax.inject.Inject


interface QrCodesRepository : SaveQrCode, DeleteQrCodeImage {

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


