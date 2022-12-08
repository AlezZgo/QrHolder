package com.example.qrholder.home.domain

import com.example.qrholder.home.data.QrCodesRepository

interface HomeInteractor {
    suspend fun fetchAll() : QrCodes

    class Base(
        private val repository : QrCodesRepository
    ) : HomeInteractor {

        override suspend fun fetchAll(): QrCodes = repository.allQrCodes()
    }
}
