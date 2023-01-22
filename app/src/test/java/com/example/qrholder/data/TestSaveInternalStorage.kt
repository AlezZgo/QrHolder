package com.example.qrholder.data

import com.example.qrholder.domain.model.ImagePath
import com.example.qrholder.presentation.buildQrCode.BitmapWrapper

class TestSaveInternalStorage : SaveInternalStorage<BitmapWrapper> {

    private var expectedResult: ImagePath = ImagePath("")

    fun changeExpectedResult(imagePath: ImagePath) {
        expectedResult = imagePath
    }

    override fun save(model: BitmapWrapper, name: String): ImagePath = expectedResult
}