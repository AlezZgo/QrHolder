package com.example.qrholder.home.data

import androidx.annotation.StringRes
import com.example.qrholder.R
import com.example.qrholder.core.ManageResources
import com.example.qrholder.home.domain.QrCode
import com.example.qrholder.home.domain.QrCodes

interface QrCodesRepository {

    suspend fun allQrCodes(): QrCodes

    class Base(
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
