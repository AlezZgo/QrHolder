package com.alezzgo.qrholder.domain

import com.alezzgo.qrholder.core.Delete
import com.alezzgo.qrholder.data.QrCodesRepository
import com.alezzgo.qrholder.domain.model.QrCode
import com.alezzgo.qrholder.domain.model.QrCodes
import javax.inject.Inject

interface HomeInteractor : Delete<String>, FetchQrCode<QrCode> {
    suspend fun fetchAll(): QrCodes

    class Base @Inject constructor(
        private val repository: QrCodesRepository
    ) : HomeInteractor {

        override suspend fun fetchAll(): QrCodes = repository.allQrCodes()

        override suspend fun fetchQrCode(title: String): QrCode =
            repository.fetchQrCode(title = title)

        override suspend fun delete(qrCodeTitle: String) =
            repository.delete(qrCodeTitle = qrCodeTitle)
    }

}

interface FetchQrCode<T> {
    suspend fun fetchQrCode(title: String): T
}
