package com.alezzgo.qrholder.core

import com.alezzgo.qrholder.domain.HomeInteractor
import com.alezzgo.qrholder.domain.model.QrCode
import com.alezzgo.qrholder.domain.model.QrCodes

class TestQrCodesInteractor : HomeInteractor {

    private var result: QrCodes = QrCodes.Success(emptyList())

    val fetchAllCalledList = mutableListOf<QrCodes>()
    val expectedQrCode = QrCode("", "", "")

    fun changeExpectedResult(qrCodes: QrCodes) {
        result = qrCodes
    }

    override suspend fun fetchAll(): QrCodes {
        fetchAllCalledList.add(result)
        return result
    }

    override suspend fun delete(model: String) = Unit

    override suspend fun fetchQrCode(title: String): QrCode {
        return expectedQrCode
    }
}
