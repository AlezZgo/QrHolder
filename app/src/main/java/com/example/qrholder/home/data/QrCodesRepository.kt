package com.example.qrholder.home.data

import com.example.qrholder.home.domain.QrCodes

interface QrCodesRepository {

    suspend fun allQrCodes() : QrCodes
}
