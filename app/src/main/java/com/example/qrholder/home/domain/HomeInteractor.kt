package com.example.qrholder.home.domain

interface HomeInteractor {
    suspend fun fetchAll() : QrCodes
}
