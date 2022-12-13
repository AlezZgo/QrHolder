package com.example.qrholder.home.domain

import com.example.qrholder.home.data.QrCodesRepository
import javax.inject.Inject

interface HomeInteractor {
    suspend fun fetchAll() : QrCodes

    class Base @Inject constructor(
        private val repository : QrCodesRepository
    ) : HomeInteractor {

        override suspend fun fetchAll(): QrCodes = repository.allQrCodes()
    }
}
