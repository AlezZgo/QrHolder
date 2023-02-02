package com.example.qrholder.domain

import com.example.qrholder.core.Delete
import com.example.qrholder.data.QrCodesRepository
import com.example.qrholder.domain.model.QrCode
import com.example.qrholder.domain.model.QrCodes
import com.example.qrholder.presentation.core.model.QrCodeUi
import javax.inject.Inject

interface HomeInteractor : Delete<String>, FetchQrCode<QrCode> {
    suspend fun fetchAll(): QrCodes

    class Base @Inject constructor(
        private val repository: QrCodesRepository
    ) : HomeInteractor {

        override suspend fun fetchAll(): QrCodes = repository.allQrCodes()

        override suspend fun fetchQrCode(title : String): QrCode = repository.fetchQrCode(title = title)

        override suspend fun delete(qrCodeTitle: String) = repository.delete(qrCodeTitle = qrCodeTitle)
    }

}

interface FetchQrCode<T>{
    suspend fun fetchQrCode(title : String) : T
}
