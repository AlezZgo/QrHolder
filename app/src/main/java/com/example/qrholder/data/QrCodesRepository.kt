package com.example.qrholder.data

import android.graphics.Bitmap
import com.example.qrholder.R
import com.example.qrholder.core.ManageResources
import com.example.qrholder.data.cache.QrCodesCacheDataSource
import com.example.qrholder.domain.ImagePath
import com.example.qrholder.domain.QrCode
import com.example.qrholder.domain.QrCodes
import javax.inject.Inject


interface QrCodesRepository {

    suspend fun allQrCodes(): QrCodes

    suspend fun save(model: Bitmap, name: String) : ImagePath

    class Base @Inject constructor(
        private val cacheDataSource: QrCodesCacheDataSource,
        private val mapper: QrCodeData.Mapper<QrCode>,
        private val manageResources: ManageResources,
        private val saveInternalStorage: SaveInternalStorage<Bitmap>
    ) : QrCodesRepository {

        override suspend fun allQrCodes(): QrCodes = try {
            QrCodes.Success(cacheDataSource.allQrCodes().map { it.map(mapper) })
        } catch (e: Exception) {
            QrCodes.Failure(e.message ?: manageResources.string(R.string.defaultErrorMessage))
        }

        override suspend fun save(model: Bitmap, name: String): ImagePath = try {
            saveInternalStorage.save(
                model = model,
                name = name
            )
        }catch (e : Exception){
            ImagePath.Error(e.message?:"Something went wrong")
        }


    }

}
