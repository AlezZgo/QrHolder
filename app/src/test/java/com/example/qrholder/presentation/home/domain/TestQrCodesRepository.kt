package com.example.qrholder.presentation.home.domain

import com.example.qrholder.data.QrCodeData
import com.example.qrholder.data.QrCodesRepository
import com.example.qrholder.data.mapper.QrCodeDataToDomainMapper
import com.example.qrholder.domain.model.ImagePath
import com.example.qrholder.domain.model.QrCode
import com.example.qrholder.domain.model.QrCodes
import com.example.qrholder.presentation.buildQrCode.BitmapWrapper

class TestQrCodesRepository : QrCodesRepository {

    var saveImageCalledList = mutableListOf<ImagePath>()
    var systemSettingsNeverShow = false

    var expectedQrCode = QrCode("","","")

    private var expectedQrCodeImageSaveResult : ImagePath = ImagePath("SomeThing went wrong")

    private var expectedQrCodesList = mutableListOf<QrCode>()
    private var expectedError: String = ""
    var allQrCodesCalledCount = 0

    val mapper = QrCodeDataToDomainMapper()

    fun changeExpectedSaveImageResult(qrCodeImagePath: ImagePath) {
        expectedQrCodeImageSaveResult = qrCodeImagePath
    }

    fun changeExpectedResult(error : String,list: List<QrCode>) {
        if(error.isEmpty())
            expectedQrCodesList = list.toMutableList()
        else
            expectedError = error
    }

    override suspend fun allQrCodes(): QrCodes {
        allQrCodesCalledCount++
        return if(expectedError.isEmpty())
            QrCodes.Success(expectedQrCodesList)
        else
            QrCodes.Failure(expectedError)

    }

    override suspend fun delete(qrCodeTitle: String) = Unit

    override suspend fun saveImage(model: BitmapWrapper, name: String): ImagePath {

        saveImageCalledList.add(expectedQrCodeImageSaveResult)

        return expectedQrCodeImageSaveResult
    }

    override suspend fun save(qrCode: QrCodeData) {
        expectedQrCodesList.add(qrCode.map(mapper))
    }

    override fun deleteImage(path: String) = Unit

    override fun fetchSystemSettingsNeverShow(): Boolean {
        return systemSettingsNeverShow
    }

    override fun saveSystemSettingsNeverShow(neverShow: Boolean) {
        systemSettingsNeverShow = neverShow
    }

    override suspend fun fetchQrCode(title: String): QrCode {
        return expectedQrCode
    }


}