package com.example.qrholder.presentation.home.domain

import com.example.qrholder.data.QrCodesRepository
import com.example.qrholder.domain.QrCodes

class TestQrCodesRepository : QrCodesRepository {

    private var expectedQrCodesResult : QrCodes = QrCodes.Success(emptyList())
    var allNumbersCalledCount = 0

    fun changeExpectedResult(qrCodes : QrCodes){
        expectedQrCodesResult = qrCodes
    }

    override suspend fun allQrCodes() : QrCodes {
        allNumbersCalledCount++
        return expectedQrCodesResult
    }

}