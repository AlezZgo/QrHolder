package com.example.qrholder.domain

import com.example.qrholder.core.Delete
import com.example.qrholder.data.QrCodesRepository
import com.example.qrholder.domain.model.QrCode
import com.example.qrholder.domain.model.QrCodes
import javax.inject.Inject

interface HomeInteractor : Delete<String> {
    suspend fun fetchAll(): QrCodes

    class Base @Inject constructor(
        private val repository: QrCodesRepository
    ) : HomeInteractor {

        override suspend fun fetchAll(): QrCodes = repository.allQrCodes()

        override suspend fun delete(qrCodeTitle: String) = repository.delete(qrCodeTitle = qrCodeTitle)
    }
}
