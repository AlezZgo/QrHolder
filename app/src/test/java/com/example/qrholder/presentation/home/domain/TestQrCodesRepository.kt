package com.example.qrholder.presentation.home.domain

import android.graphics.Bitmap
import com.example.qrholder.data.QrCodesRepository
import com.example.qrholder.domain.model.ImagePath
import com.example.qrholder.domain.model.QrCodes

class TestQrCodesRepository : QrCodesRepository {

    var saveImageCalledList = mutableListOf<ImagePath>()
    private var expectedQrCodeImageSaveResult : ImagePath = ImagePath.Error("SomeThing went wrong")

    private var expectedQrCodesResult: QrCodes = QrCodes.Success(emptyList())
    var allNumbersCalledCount = 0

    fun changeExpectedSaveImageResult(qrCodeImagePath: ImagePath) {
        expectedQrCodeImageSaveResult = qrCodeImagePath
    }

    fun changeExpectedResult(qrCodes: QrCodes) {
        expectedQrCodesResult = qrCodes
    }

    override suspend fun allQrCodes(): QrCodes {
        allNumbersCalledCount++
        return expectedQrCodesResult
    }

    override suspend fun saveQrCodeImage(model: Bitmap, name: String): ImagePath {

        saveImageCalledList.add(expectedQrCodeImageSaveResult)

        return expectedQrCodeImageSaveResult
    }


}