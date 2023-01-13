package com.example.qrholder.domain

import com.example.qrholder.data.QrCodesRepository
import com.example.qrholder.domain.model.QrCodes
import javax.inject.Inject

interface HomeInteractor {
    suspend fun fetchAll(): QrCodes

    class Base @Inject constructor(
        private val repository: QrCodesRepository
    ) : HomeInteractor {

        override suspend fun fetchAll(): QrCodes = repository.allQrCodes()
    }
}
