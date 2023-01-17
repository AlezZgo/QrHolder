package com.example.qrholder.data

import android.graphics.Bitmap
import com.example.qrholder.domain.model.ImagePath

class TestSaveInternalStorage : SaveInternalStorage<Bitmap> {

    private var expectedResult : ImagePath= ImagePath.Error("Nothing to save")

    fun changeExpectedResult(imagePath : ImagePath) {
        expectedResult = imagePath
    }

    override fun save(model: Bitmap, name: String): ImagePath = expectedResult
}