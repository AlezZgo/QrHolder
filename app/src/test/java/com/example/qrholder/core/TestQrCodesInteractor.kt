package com.example.qrholder.core

import com.example.qrholder.domain.HomeInteractor
import com.example.qrholder.domain.QrCodes

class TestQrCodesInteractor : HomeInteractor {

    private var result: QrCodes = QrCodes.Success(emptyList())

    val fetchAllCalledList = mutableListOf<QrCodes>()

    fun changeExpectedResult(qrCodes: QrCodes) {
        result = qrCodes
    }

    override suspend fun fetchAll(): QrCodes {
        fetchAllCalledList.add(result)
        return result
    }
}
