package com.example.qrholder.home.data

import com.example.qrholder.R
import com.example.qrholder.core.ManageResources
import com.example.qrholder.home.data.cache.QrCodesCacheDataSource
import com.example.qrholder.home.domain.QrCode
import com.example.qrholder.home.domain.QrCodes
import javax.inject.Inject

interface QrCodesRepository {

    suspend fun allQrCodes(): QrCodes

    class Base @Inject constructor(
        private val cacheDataSource: QrCodesCacheDataSource,
        private val mapper: QrCodeData.Mapper<QrCode>,
        private val manageResources : ManageResources
    ) : QrCodesRepository {

        override suspend fun allQrCodes(): QrCodes = try {
            QrCodes.Success(cacheDataSource.allQrCodes().map { it.map(mapper) })
        } catch (e: Exception) {
            QrCodes.Failure(e.message ?: manageResources.string(R.string.defaultErrorMessage))
        }

    }

}
