package com.alezzgo.qrholder.data

import com.alezzgo.qrholder.domain.model.ImagePath
import com.alezzgo.qrholder.presentation.buildQrCode.BitmapWrapper

class TestSaveInternalStorage : SaveInternalStorage<BitmapWrapper> {

    private var expectedResult: ImagePath = ImagePath("")

    fun changeExpectedResult(imagePath: ImagePath) {
        expectedResult = imagePath
    }

    override fun save(model: BitmapWrapper, name: String): ImagePath = expectedResult
}