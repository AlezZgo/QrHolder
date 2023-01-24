package com.example.qrholder.core

import com.example.qrholder.domain.HomeInteractor
import com.example.qrholder.domain.model.QrCodes

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

    override suspend fun delete(model: String) = Unit
}
